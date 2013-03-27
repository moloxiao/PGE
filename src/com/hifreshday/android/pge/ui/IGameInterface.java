package com.hifreshday.android.pge.ui;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.entity.scene.Scene;

public interface IGameInterface {

	public Engine onEngineLoaded();
	public Scene onLoadScene();
	public void onLoadComplete();
	public void onPauseGame();
	public void onResumeGame();
	
	public int getLayoutResId();
	public int getGameViewId();
;	
}
