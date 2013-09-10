package com.hifreshday.android.pge.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public abstract class AButtonSprite extends Sprite {

	protected int normalKey;
	protected int clickKey;
	private boolean allowClick = true;
	
	private Touch pressed = new Touch();
	
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
			if(pressed.pressed) {
				canvas.drawBitmap(bitmapRes.getBitmap(clickKey), null, getRect(), null);
			}else {
				canvas.drawBitmap(bitmapRes.getBitmap(normalKey), null, getRect(), null);
			}
		}else {
			canvas.drawBitmap(bitmapRes.getBitmap(clickKey), null, getRect(), null);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(isVisible() && isAllowClick()){
			
			boolean result = click(pressed, getRect(), event);
			if(pressed.pressed || result) {
				if(result) {
					onClick();
				}
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * when onclick will do this method.
	 */
	abstract public void onClick();

	public class Touch {
		public boolean pressed = false;
	}
	
	private boolean click(Touch touch, Rect rect, MotionEvent event) {
		boolean result = false;
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			if(rect.contains((int)(event.getX()), (int)(event.getY()))){
				touch.pressed = true;
			}
		}else if(event.getAction() == MotionEvent.ACTION_MOVE) {
			if(touch.pressed && rect.contains((int)(event.getX()), (int)(event.getY())) ) {
				touch.pressed = true;
			}else {
				touch.pressed = false;
			}
		}else if((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)){
			if(touch.pressed && rect.contains((int)(event.getX()), (int)(event.getY()))) {
				result = true;
			}
			touch.pressed = false;
		}
		return result;
	}
}
