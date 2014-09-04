package com.hifreshday.android.pge.engine;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.hifreshday.android.pge.engine.handler.IFixUpdateHandler;
import com.hifreshday.android.pge.engine.handler.RunnableHandler;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.engine.options.EngineUtil;
import com.hifreshday.android.pge.entity.Entity;
import com.hifreshday.android.pge.entity.IEntity;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.physics.IPhysicsManager;

public class Engine implements IPhysicsManager{
	
	public static final PaintFlagsDrawFilter FILTER = new PaintFlagsDrawFilter(0,
			Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG); 
	
	private static final String TAG = "PGE";
	
	private boolean showFps = false;
	private boolean alive = false;
	
	private EngineOptions options;
	private long lastTick = -1;
	private boolean isRunning = false;
	private EngineThread engineThread;

	private Scene scene;
	private SurfaceHolder surfaceHolder;
	
	private final RunnableHandler updateThreadRunnableHandler = new RunnableHandler();
	
	private List<MotionEvent> touchEvents = new ArrayList<MotionEvent>();
	
	public static boolean isRecycle = false;

	public Engine(final EngineOptions options){
		this.options = options;
		engineThread = new EngineThread();
		alive = true;
		Entity.physicsManager = this;
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
		updateFixUpdate(secondsElapsed);
		onUpdateScene(secondsElapsed);			// view update
		
		doDraw(surfaceHolder, secondsElapsed);
	}
	
	private float bufferFixTime = 0.0f;
	
	private void updateFixUpdate(float secondsElapsed) {
		if (true) {
			bufferFixTime += secondsElapsed;
			while (bufferFixTime >= 1 / IFixUpdateHandler.FIX_STEP) {
				bufferFixTime -= 1 / IFixUpdateHandler.FIX_STEP;
				onManagerFixUpdate();
			}
		}
	}
	
	protected void onManagerFixUpdate() {
		if (fixUpdateChildren != null) {
			for (IFixUpdateHandler fixUpdateEntity : this.fixUpdateChildren) {
				fixUpdateEntity.onFixUpdate();
			}
		}
		detachChilds();
	}
	
	protected ArrayList<IEntity> fixUpdateChildren;
	
	@Override
	public boolean attachChild(final IEntity fixUpdateEntity){
		if (fixUpdateEntity == null) {
			return false;
		}
		if (fixUpdateChildren == null) {
			fixUpdateChildren = new ArrayList<IEntity>();
		}
		fixUpdateChildren.add(fixUpdateEntity);
	
		return true;
	}

	public boolean detachChilds(){
		ArrayList<IEntity> buffer = new ArrayList<IEntity>();
		boolean needDo = false;
		for (IEntity entity : fixUpdateChildren) {
			if (entity.isNeedRemove()) {
				buffer.add(entity);
				needDo = true;
			}
		}
		if (needDo) {
			for (IEntity entity : buffer) {
				this.fixUpdateChildren.remove(entity);
			}
			return true;
		}
		return false;
	}

	protected void onUpdateScene(float secondsElapsed) {
		if(scene != null) {
			scene.onUpdate(secondsElapsed);
		}
	}
	
	protected void updateUpdateHandler(float secondsElapsed) {
		updateThreadRunnableHandler.onUpdate(secondsElapsed);
	}

	private void doDraw(SurfaceHolder surfaceHolder, float secondsElapsed){
		Canvas c = null;
		try {
			surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
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
	
	private static final int LEVEL_FPS = 12;
	private int warningCount = 0;
	private Paint paint ;
	private void draw(Canvas canvas, float secondsElapsed){
		if(scene != null && !isRecycle){
			if(options.isOpenHighQuality()) {
				canvas.setDrawFilter(FILTER);
			}
			scene.onDraw(canvas);
			
			float fps = 1/secondsElapsed;
			if(fps < LEVEL_FPS) {
				warningCount++;
			}else {
				warningCount = 0;
			}
			
			if(warningCount > 20) { 
				options.closeOpenHightQuality();
				Log.e(TAG, "close high quality render");
			}
			
			if(isShowFps()) {
				if(paint == null) {
					paint = new Paint();
					paint.setColor(Color.WHITE);
					paint.setStyle(Paint.Style.FILL);
					paint.setAntiAlias(true);
					paint.setTextSize(16);
				}
				if(secondsElapsed != 0) {
					DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
					df.setMaximumFractionDigits(2);
					canvas.drawText("fps=" + df.format(fps), 5, 20, paint);
				}
			}
		}
	}
	
	public void onResume() {
	}
	
	public void onPause() {
	}
	
	public void onDestory() { 
		if(alive) {
			stop();
			interruptUpdateThread();
			if(scene != null) {
				scene.recycle();
			}
			alive = false;
			Log.i(TAG, "engine onDestory ... ");
		}
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
		synchronized(touchEvents){
			 touchEvents.add(MotionEvent.obtain(
					event.getDownTime(),
					event.getEventTime(),
					event.getAction(),
					event.getX() ,//- EngineOptions.getOffsetX()
					event.getY(),// - EngineOptions.getOffsetY()
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

	public boolean isShowFps() {
		return showFps;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}
}
