package com.hifreshday.android.pge.entity.shape;

import android.graphics.Rect;
import android.view.MotionEvent;
import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.Entity;

public abstract class Shape extends Entity implements IShape {
	
	private int initX;
	private int initY;
	private int initWidth;
	private int initHeight;
	
	private int width ;
	private int height ;
	private Rect rect;
	
	public Shape(int pX, int pY, int width, int height) {
		this.initX = pX;
		this.initY = pY;
		this.initWidth = width;
		this.initHeight = height;
		this.rect = new Rect();
		this.width = (int)(width*EngineOptions.getScreenScaleX());
		this.height = (int)(height*EngineOptions.getScreenScaleY());
		setPosition((int)(pX*EngineOptions.getScreenScaleX()), (int)(pY*EngineOptions.getScreenScaleY()));
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

	public int getInitX() {
		return initX;
	}

	public int getInitY() {
		return initY;
	}

	public int getInitWidth() {
		return initWidth;
	}

	public int getInitHeight() {
		return initHeight;
	}
}
