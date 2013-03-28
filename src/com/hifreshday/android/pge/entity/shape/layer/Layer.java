package com.hifreshday.android.pge.entity.shape.layer;

import android.view.MotionEvent;

import com.hifreshday.android.pge.entity.shape.Shape;

public class Layer extends Shape {

	public Layer(int pX, int pY, int width, int height) {
		super(pX, pY, width, height);
	}

	@Override
	protected boolean doOnTouchEvent(MotionEvent event) {
		return false;
	}

}
