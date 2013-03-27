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
		return new Engine(new EngineOptions());
	}

	@Override
	public Scene onLoadScene() {
		return new TestScene(getResources());
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
	public int getGameViewId() {
		return R.id.gameview;
	}

}
