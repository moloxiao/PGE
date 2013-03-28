package com.hifreshday.android.test.sprite;

import android.view.MotionEvent;

import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class TestSprite extends Sprite {

	public TestSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
	}

	@Override
	public boolean doOnTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			setPosition(getX() + 10, getY() + 10);
			setVisible(!isVisible());
		}
		return true;
	}
	
	
}
