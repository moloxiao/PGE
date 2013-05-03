package com.hifreshday.android.pge.engine;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.hifreshday.android.pge.engine.handler.RunnableHandler;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.engine.options.EngineUtil;
import com.hifreshday.android.pge.entity.scene.Scene;

public class Engine {
	
	private static final String TAG = "Engine";
	
	private EngineOptions options;
	private long lastTick = -1;
	private boolean isRunning = false;
	private EngineThread engineThread;

	private Scene scene;
	private SurfaceHolder surfaceHolder;
	
	private final RunnableHandler updateThreadRunnableHandler = new RunnableHandler();
	
	private List<MotionEvent> touchEvents = new ArrayList<MotionEvent>();

	public Engine(final EngineOptions options){
		this.options = options;
		engineThread = new EngineThread();
		engineThread.start();
	}
	
	public void onTickUpdate() throws InterruptedException{
		
		if(isRunning){
			final long pNanosecondsElapsed = EngineUtil.getNanosecondsElapsed(lastTick);
			logicUpdate(pNanosecondsElapsed);
		}else{
			Thread.sleep(16);
		}
	}
	
	private void logicUpdate(long nanosecondsElapsed){
		final float secondsElapsed = (float)nanosecondsElapsed / 1000000000;
		this.lastTick += nanosecondsElapsed;
		
		onTouchUpdate();						// touch update
		updateUpdateHandler(secondsElapsed);	// handler update
		onUpdateScene(secondsElapsed);			// view update
		
		draw(surfaceHolder, secondsElapsed);
	}
	
	protected void onUpdateScene(float secondsElapsed) {
		if(scene != null) {
			scene.onUpdate(secondsElapsed);
		}
	}
	
	protected void updateUpdateHandler(float secondsElapsed) {
		updateThreadRunnableHandler.onUpdate(secondsElapsed);
	}

	private void draw(SurfaceHolder surfaceHolder, float secondsElapsed){
		Canvas c = null;
		try {
			c = surfaceHolder.lockCanvas();
			synchronized (surfaceHolder) {
				if (c != null) {
					draw(c, secondsElapsed);
				}
			}
		} finally {
			if (c != null) {
				surfaceHolder.unlockCanvasAndPost(c);
			}
		}
	}
	
	private void draw(Canvas canvas, float pSecondsElapsed){
		if(scene != null){
			scene.onDraw(canvas);
		}
	}
	
	
	public void onResume() {
	}
	
	public void onPause() {
	}
	
	public void onDestory() { 
		stop();
		interruptUpdateThread();
		Log.i(TAG, "engine onDestory ... ");
	}
	
	public synchronized void start() {
		Log.i(TAG, "engine start ... ");
		if(!this.isRunning) {
			this.lastTick = System.nanoTime();
			this.isRunning = true;
		}
	}

	public synchronized void stop() {
		Log.i(TAG, "engine stop ... ");
		if(this.isRunning) {
			this.isRunning = false;
		}
	}
	
	private void interruptUpdateThread() {
		if(this.engineThread != null) {
			this.engineThread.interrupt();
		}
	}
	
	private class EngineThread extends Thread {
		
		private boolean interrupted = false;
		private volatile boolean cancel = false;
		
		public EngineThread() {
			super("EngineThread");
		}
		
		@Override
		public void run() {
			Log.i(TAG, "engine thread start ... ");
			while(!cancel){
				try {
					Engine.this.onTickUpdate();
				} catch (final InterruptedException e) {
					interrupted = true;
					e.printStackTrace();
				} finally {
					if (interrupted) {
						cancel = true;
			            Thread.currentThread().interrupt();
					}
				}
			}
		}
	}
	
	
	private void onTouchUpdate() {
		List<MotionEvent> bufferEvent;
		synchronized(touchEvents){
			bufferEvent = new ArrayList<MotionEvent>();
			
			for(int i=0;i<touchEvents.size();i++) {		
				
				bufferEvent.add(MotionEvent.obtain(
						touchEvents.get(i).getDownTime(),
						touchEvents.get(i).getEventTime(),
						touchEvents.get(i).getAction(),
						touchEvents.get(i).getX(),
						touchEvents.get(i).getY(),
						touchEvents.get(i).getMetaState()));
			}
			touchEvents.clear();
		}
		
		if(bufferEvent != null) {
			for(MotionEvent motionevent : bufferEvent) {
				scene.onTouchEvent(motionevent);
			}
			bufferEvent.clear();
			bufferEvent = null;
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		synchronized(touchEvents){
			 touchEvents.add(MotionEvent.obtain(
					event.getDownTime(),
					event.getEventTime(),
					event.getAction(),
					event.getX(),
					event.getY(),
					event.getMetaState()));
		}
		return true;
	}

	public Scene getScene() {
		return scene;
	}

	public EngineOptions getOptions() {
		return options;
	}

	public void setOptions(EngineOptions options) {
		this.options = options;
	}

	public void onLoadInit(SurfaceHolder surfaceHolder, Scene scene) {
		Log.i("MOLO_DEBUG", "surfaceholder create");
		this.scene = scene;
		this.surfaceHolder = surfaceHolder;
	}
	
	public void runOnUpdateThread(final Runnable runnable) {
		updateThreadRunnableHandler.postRunnable(runnable);
	}
}
