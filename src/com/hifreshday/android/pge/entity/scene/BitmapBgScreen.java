package com.hifreshday.android.pge.entity.scene;

import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.view.res.GameBitmapUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public abstract class BitmapBgScreen extends Scene {

	private Bitmap bg;
	private Rect bgRect;
	
	public void setBgResId(Resources res, int id) {
		bgRect = new Rect(0, 0, 
				EngineOptions.getRealScreenWidth(), 
				EngineOptions.getRealScreenHeight());
		bg = Bitmap.createScaledBitmap(GameBitmapUtil.loadBitmap(res, id),
				EngineOptions.getRealScreenWidth(), 
				EngineOptions.getRealScreenHeight(), false);
	}
	
	@Override
	protected void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bg, null, bgRect, null);
	}
	
	@Override
	public void recycle() {
		super.recycle();
		GameBitmapUtil.recycleBitmap(bg);
	}

}
