package com.hifreshday.android.pge.ui.activity;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.ui.IGameInterface;
import com.hifreshday.android.pge.view.GameView;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseGameActivity extends Activity implements IGameInterface{

	protected Engine engine;
	protected boolean gameLoaded = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.engine = onEngineLoaded();
		setContentView(getLayoutResId());
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
			final Scene scene = onLoadScene();
			GameView view = (GameView)findViewById(getGameViewId());
			view.initEngine(engine);
			engine.onLoadInit(view.getHolder(), scene);
			onLoadComplete();
			gameLoaded = true;
		}
		
		this.engine.onResume();
		onResumeGame();
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
	
	
	
	
	
}
