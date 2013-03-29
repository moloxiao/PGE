package com.hifreshday.android.pge.view;

import com.hifreshday.android.pge.engine.Engine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback {

	private final String TAG = "GameView";
	private SurfaceHolder surfaceHolder;
	private Engine engine;
	
	public GameView(Context context) {
		super(context);
		initView(context);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	

	private void initView(Context context) {
		Log.i(TAG, "GameView init");
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		setLongClickable(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setKeepScreenOn(true);
		requestFocus();
	}
	
	public void initEngine(Engine engine) {
		this.engine = engine;
	}
	

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i(TAG, "surfaceChanged format= " + format + ", width = " + width + " , height = " + height);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated(SurfaceHolder)");
		if(engine != null)engine.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "surfaceDestroyed(SurfaceHolder)");
		if(engine != null)engine.stop();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (engine != null && engine.onTouchEvent(event)) {
			return true;
		}
		return super.onTouchEvent(event);
	}
	
}