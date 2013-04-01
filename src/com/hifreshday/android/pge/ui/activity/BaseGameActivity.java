package com.hifreshday.android.pge.ui.activity;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.ui.IGameInterface;
import com.hifreshday.android.pge.view.GameView;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public abstract class BaseGameActivity extends Activity implements IGameInterface{

	protected Engine engine;
	protected boolean gameLoaded = false;
	private int screenWidth ;
	private int screenHeight ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		DisplayMetrics dm = getResources().getDisplayMetrics();
		this.screenWidth = dm.widthPixels;
		this.screenHeight = dm.heightPixels;
		this.engine = onEngineLoaded();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		engine.onDestory();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		doResume();
	}
	
	private void doResume() {
		if(!gameLoaded) {
			doGameLoad();
			onLoadComplete();
			gameLoaded = true;
		}
		
		this.engine.onResume();
		onResumeGame();
	}
	
	protected void doGameLoad() {
		GameView view = (GameView)findViewById(getGameViewResId());
		view.initEngine(engine);
		final Scene scene = onLoadScene();
		engine.onLoadInit(view.getHolder(), scene);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		doPause();
		
	}
	
	private void doPause() {
		this.engine.onPause();
		onPauseGame();
	}
	
	public EngineOptions getEngineOptions() {
		return engine.getOptions();
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public Engine getEngine() {
		return engine;
	}
}
