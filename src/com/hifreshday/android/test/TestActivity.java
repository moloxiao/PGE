package com.hifreshday.android.test;

import com.hifreshday.android.pge.R;
import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.GameView;
import com.hifreshday.android.test.res.PicResDemo;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {

	private Engine engine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		GameView view = (GameView)findViewById(R.id.gameview);
		
		Scene scene = new Scene();
		PicResDemo picresDemo = new PicResDemo(getResources());
		Sprite sprite = new Sprite(picresDemo, 50, 50, picresDemo.getDefalutBitmap().getWidth(), picresDemo.getDefalutBitmap().getHeight());
		scene.attachChild(sprite);
		
		
		engine = new Engine(new EngineOptions(), view.getHolder(), scene);
		view.initEngine(engine);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		engine.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		engine.onPause();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		engine.onDestory();
	}

}
