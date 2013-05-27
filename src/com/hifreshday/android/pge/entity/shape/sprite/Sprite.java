package com.hifreshday.android.pge.entity.shape.sprite;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.hifreshday.android.pge.entity.shape.Shape;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class Sprite extends Shape {
	
	private boolean needRecyle = false;
	protected IBitmapRes bitmapRes;
	
	public Sprite(IBitmapRes bitmapRes, int pX, int pY, int width, int height) {
		super(pX, pY, width, height);
		this.bitmapRes = bitmapRes;
	}
	

	public boolean isNeedRecyle() {
		return needRecyle;
	}


	public void setNeedRecyle(boolean needRecyle) {
		this.needRecyle = needRecyle;
	}


	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bitmapRes.getDefalutBitmap(), null, rect, null);
	}

	@Override
	protected boolean doOnTouchEvent(MotionEvent event) {
		return false;
	}
	
	public void recycle(){
	}
}
