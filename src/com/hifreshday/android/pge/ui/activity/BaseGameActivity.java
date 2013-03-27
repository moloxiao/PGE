package com.hifreshday.android.pge.ui.activity;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.ui.IGameInterface;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseGameActivity extends Activity implements IGameInterface{

	protected Engine engine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.engine = onEngineLoaded();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.engine.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.engine.onPause();
	}
}
