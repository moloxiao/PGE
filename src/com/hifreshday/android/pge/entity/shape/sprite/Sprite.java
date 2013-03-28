package com.hifreshday.android.pge.entity.shape.sprite;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.hifreshday.android.pge.entity.shape.Shape;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class Sprite extends Shape {
	protected IBitmapRes bitmapRes;
	
	public Sprite(IBitmapRes bitmapRes, int pX, int pY, int width, int height) {
		super(pX, pY, width, height);
		this.bitmapRes = bitmapRes;
	}

	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bitmapRes.getDefalutBitmap(), null, 
				new Rect(getX(), getY(), getX() + getWidth(), getY() + getHeight()), null);
	}

	@Override
	protected boolean doOnTouchEvent(MotionEvent event) {
		return false;
	}
}
