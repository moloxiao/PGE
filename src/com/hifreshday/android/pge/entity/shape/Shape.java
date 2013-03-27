package com.hifreshday.android.pge.entity.shape;

import com.hifreshday.android.pge.entity.Entity;

public class Shape extends Entity implements IShape {
	
	private int width ;
	private int height ;
	
	public Shape(int pX, int pY, int width, int height) {
		this.width = width;
		this.height = height;
		setPosition(pX, pY);
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
}
