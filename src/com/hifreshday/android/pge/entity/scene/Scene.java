package com.hifreshday.android.pge.entity.scene;

import android.content.res.Resources;
import android.view.MotionEvent;
import com.hifreshday.android.pge.entity.Entity;
import com.hifreshday.android.pge.input.touch.ITouch;
import com.hifreshday.android.pge.input.touch.controler.ITouchControler;
import com.hifreshday.android.pge.input.touch.controler.TouchControler;

public abstract class Scene extends Entity implements ITouch{
	
	private ITouchControler touchControler = new TouchControler();
	
	private int width ;
	private int height ;
	
	public void setScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public abstract void onLoadResources(Resources res);
	public abstract void onUnloadResources();
	
	public void registerTouch(ITouch touch) {
		touchControler.registerTouch(touch);
	}
	
	public void unregisterTouch(ITouch touch) {
		touchControler.unregisterTouch(touch);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return touchControler.onTouchEvent(event);
	}
	
	public abstract void recycle();
}
