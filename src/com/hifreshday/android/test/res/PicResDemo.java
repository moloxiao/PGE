package com.hifreshday.android.test.res;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.hifreshday.android.pge.R;
import com.hifreshday.android.pge.view.res.GameBitmapUtil;
import com.hifreshday.android.pge.view.res.IBitmapRes;

public class PicResDemo implements IBitmapRes {
	
	private Bitmap bitmap;
	
	public PicResDemo(Resources res) {
		bitmap = GameBitmapUtil.loadBitmap(res, R.drawable.ic_launcher);
	}

	@Override
	public Bitmap getDefalutBitmap() {
		return bitmap;
	}

	@Override
	public Bitmap getBitmap(int key) {
		switch(key){
		case DEFAULT_BITMAP_KEY:
			return bitmap;
		}
		return null;
	}

	@Override
	public String getResName() {
		return "PicResDemo";
	}

}
