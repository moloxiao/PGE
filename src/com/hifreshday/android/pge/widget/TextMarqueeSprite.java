package com.hifreshday.android.pge.widget;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class TextMarqueeSprite extends Sprite {

	private static final int RECT_WIDTH = 320;
	private static final int RECT_HEIGHT = 40;

	private static final int SPEED = 50;
	private int currentTextPosition = RECT_WIDTH;
	private float buffersecond = 0.0f;
	private String info;
	private float stringsize = 0.0f;
	private String temp = null;
	
	private Paint paint;
	private int startX;
	private int startY;
	
	public TextMarqueeSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
		setVisible(false);
	}

	/**
	 * 显示跑马灯效果
	 * @param text  要显示的文字
	 * @param startX  起始位置x坐标
	 * @param startY  起始位置y坐标
	 * @param paint   设置文字效果的paint
	 */
	public void showInfo(String text,int startX,int startY,Paint paint) {
		this.paint = paint;
		this.startX = startX;
		this.startY = startY;
		if (!isVisible()) {
			info = text;
			stringsize = paint.measureText(info);
			currentTextPosition = RECT_WIDTH;
			setVisible(true);
		} else {
			temp = text;
		}
	}

	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		buffersecond += secondsElapsed;
		if ((buffersecond * SPEED) > 1) {
			int move = (int) (buffersecond * SPEED);
			currentTextPosition -= move;
			if (currentTextPosition + stringsize < 0) {
				currentTextPosition = RECT_WIDTH;
				setVisible(false);
				buffersecond = 0;
			}
			buffersecond = (buffersecond * SPEED - move) / SPEED;
		}
		if (null != temp && !isVisible()) {
			setVisible(true);
			info = temp;
			buffersecond += secondsElapsed;
			if ((buffersecond * SPEED) > 1) {
				int move = (int) (buffersecond * SPEED);
				currentTextPosition -= move;
				if (currentTextPosition + stringsize < 0) {
					currentTextPosition = RECT_WIDTH;
					setVisible(false);
					temp = null;
				}
				buffersecond = (buffersecond * SPEED - move) / SPEED;
			}
		}
	}

	@Override
	public void onDrawSelf(Canvas canvas) {
		canvas.save();
		canvas.translate(
				EngineOptions.getOffsetX() + startX
						* EngineOptions.getScreenScaleX(),
				EngineOptions.getOffsetY() + startY
						* EngineOptions.getScreenScaleY());
		canvas.clipRect(0, 0, RECT_WIDTH * EngineOptions.getScreenScaleX(),
				RECT_HEIGHT * EngineOptions.getScreenScaleY());
		canvas.drawText(info,
				currentTextPosition * EngineOptions.getScreenScaleX(),
				20 * EngineOptions.getScreenScaleY(), paint);
		canvas.restore();
	}
}
