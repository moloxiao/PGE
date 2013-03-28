package com.hifreshday.android.pge.entity.shape;

import android.graphics.Rect;
import android.view.MotionEvent;
import com.hifreshday.android.pge.entity.Entity;

public abstract class Shape extends Entity implements IShape {
	
	private int width ;
	private int height ;
	private Rect rect;
	
	public Shape(int pX, int pY, int width, int height) {
		this.rect = new Rect();
		this.width = width;
		this.height = height;
		setPosition(pX, pY);
	}
	
	@Override
	public void setPosition(int pX, int pY) {
		super.setPosition(pX, pY);
		rect.set(getX(), getY(), getX() + getWidth(), getY() + getHeight());
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(contains((int)event.getX(), (int)event.getY())) {
			return doOnTouchEvent(event);
		}
		return false;
	}
	
	protected abstract boolean doOnTouchEvent(MotionEvent event);
	
	protected boolean contains(int px, int py) {
		return rect.contains(px, py);
	}
}
