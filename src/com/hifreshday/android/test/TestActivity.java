package com.hifreshday.android.test;

import com.hifreshday.android.pge.R;
import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.ui.activity.BaseGameActivity;
import com.hifreshday.android.test.scene.TestScene;

public class TestActivity extends BaseGameActivity {

	@Override
	public Engine onEngineLoaded() {
		return new Engine(new EngineOptions(getScreenWidth(), getScreenHeight()));
	}

	@Override
	public Scene onLoadScene() {
		TestScene scene = new TestScene(getResources());
		scene.setScreenSize(EngineOptions.getScreenWidth(), EngineOptions.getScreenHeight());
		scene.setBgResId(getResources(), getSceneBgResId());
		return scene;
	}

	@Override
	public void onLoadComplete() {
	}

	@Override
	public void onPauseGame() {
	}

	@Override
	public void onResumeGame() {
	}

	@Override
	public int getLayoutResId() {
		return R.layout.activity_game;
	}

	@Override
	public int getGameViewResId() {
		return R.id.gameview;
	}

	@Override
	public int getSceneBgResId() {
		return R.drawable.info;
	}

}
