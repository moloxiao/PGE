package com.hifreshday.android.pge.widget;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public abstract class AButtonSprite extends Sprite {

	private int normalKey;
	private int clickKey;
	private boolean allowClick = true;
	
	public AButtonSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
	}
	
	/**
	 * 是否允许点击。
	 * @return true,允许点击。false,不允许点击。
	 */
	public boolean isAllowClick() {
		return allowClick;
	}

	public void setAllowClick(boolean allowClick) {
		this.allowClick = allowClick;
	}

	public void setBgView(int normalKey, int clickKey) {
		this.normalKey = normalKey;
		this.clickKey = clickKey;
	}
	
	
	@Override
	public void onDrawSelf(Canvas canvas) {
		if(isAllowClick()) {
			canvas.drawBitmap(bitmapRes.getBitmap(normalKey), null, getRect(), null);
		}else {
			canvas.drawBitmap(bitmapRes.getBitmap(clickKey), null, getRect(), null);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(isVisible() && 
				isAllowClick() && 
				event.getAction() == MotionEvent.ACTION_DOWN && 
				getRect().contains((int)event.getX() , (int)event.getY()) ){
			onClick();
			return true;
		}
		return false;
	}
	
	abstract public void onClick();

}
