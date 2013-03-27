package com.hifreshday.android.pge.engine;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

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

	public Engine(final EngineOptions options){
		this.options = options;
		engineThread = new EngineThread();
		engineThread.start();
	}
	
	public void onTickUpdate() throws InterruptedException{
		
		if(isRunning){
			Log.i(TAG, "onTickUpdate isRunning ... ");
			final long pNanosecondsElapsed = EngineUtil.getNanosecondsElapsed(lastTick);
			logicUpdate(pNanosecondsElapsed);
		}else{
			Thread.sleep(16);
		}
	}
	
	private void logicUpdate(long pNanosecondsElapsed){
		final float pSecondsElapsed = (float)pNanosecondsElapsed / 1000000000;
		this.lastTick += pNanosecondsElapsed;
		
		// TODO : touch update
		// TODO : handler update
		scene.onUpdate(pSecondsElapsed);		// view update
		
		draw(surfaceHolder, pSecondsElapsed);
	}
	
	private void draw(SurfaceHolder surfaceHolder, float pSecondsElapsed){
		Canvas c = null;
		try {
			c = surfaceHolder.lockCanvas();
			synchronized (surfaceHolder) {
				if (c != null) {
					draw(c, pSecondsElapsed);
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
		start();
	}
	
	public void onPause() {
		stop();
	}
	
	public void onDestory() { 
		interruptUpdateThread();
	}
	
	private synchronized void start() {
		Log.i(TAG, "engine start ... ");
		if(!this.isRunning) {
			this.lastTick = System.nanoTime();
			this.isRunning = true;
		}
	}

	private synchronized void stop() {
		Log.i(TAG, "engine stop ... ");
		if(this.isRunning) {
			this.isRunning = false;
		}
	}
	
	private void interruptUpdateThread() {
		this.engineThread.interrupt();
	}
	
	private class EngineThread extends Thread {
		
		public EngineThread() {
			super("THREADNAME");
		}
		
		@Override
		public void run() {
			Log.i(TAG, "engine thread start ... ");
			while(true){
				try {
					Engine.this.onTickUpdate();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		// TODO : touch实现
		return false;
	}

	public Scene getScene() {
		return scene;
	}

	public void onLoadInit(SurfaceHolder surfaceHolder, Scene scene) {
		this.scene = scene;
		this.surfaceHolder = surfaceHolder;
	}
	
	
}
