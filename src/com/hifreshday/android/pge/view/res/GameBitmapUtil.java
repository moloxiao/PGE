package com.hifreshday.android.pge.view.res;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameBitmapUtil {
	
	/**
	 * 加载图片资源
	 * 
	 * @param res
	 * @param resId
	 * @return
	 */
	public static Bitmap loadBitmap(Resources res, int resId) {
		Bitmap bmp = BitmapFactory.decodeResource(res, resId);
		return bmp;
	}	
	
	/***
	 * 释放Bitmap所占用内存
	 * 
	 * @param bitmap
	 */
	public static void recycleBitmap(Bitmap bitmap) {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
	}
	
	/**
	 * 按长宽高缩放
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap createScaledBitmap(Bitmap bitmap, int width, int height) {
		Bitmap dstbmp = Bitmap.createScaledBitmap(bitmap, width, height, true);
		return dstbmp;
	}
}