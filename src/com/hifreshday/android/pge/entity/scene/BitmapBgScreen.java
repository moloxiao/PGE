package com.hifreshday.android.pge.entity.scene;

import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.view.res.GameBitmapUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public abstract class BitmapBgScreen extends Scene {

	private Bitmap bg;
	
	public void setBgResId(Resources res, int id) {
		bg = GameBitmapUtil.loadBitmap(res, id);
	}
	
	@Override
	protected void onDrawSelf(Canvas canvas) {
		canvas.drawBitmap(bg, null, new Rect(0, 0, 
				EngineOptions.getRealScreenWidth(), 
				EngineOptions.getRealScreenHeight()), null);
	}

}
