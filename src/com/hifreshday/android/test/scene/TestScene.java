package com.hifreshday.android.test.scene;

import android.content.res.Resources;

import com.hifreshday.android.pge.entity.scene.Scene;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.test.res.PicResDemo;

public class TestScene extends Scene {
	
	private PicResDemo picresDemo;
	private Sprite sprite;
	
	public TestScene(Resources res) {
		
		onLoadResources(res);
	}

	@Override
	public void onLoadResources(Resources res) {
		picresDemo = new PicResDemo(res);
		sprite = new Sprite(picresDemo, 50, 50, picresDemo.getDefalutBitmap().getWidth(), picresDemo.getDefalutBitmap().getHeight());
		attachChild(sprite);
	}

	@Override
	public void onUnloadResources() {
		picresDemo = null;
		sprite = null;
	}

}
