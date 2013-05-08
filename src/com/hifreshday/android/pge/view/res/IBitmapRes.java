package com.hifreshday.android.pge.view.res;

import android.graphics.Bitmap;

public interface IBitmapRes {
	
	public static final int DEFAULT_BITMAP_KEY = 0;
	
	/**
	 *  return default res. 
	 * @return If only one res, return res. else return null.
	 */
	public Bitmap getDefalutBitmap();
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Bitmap getBitmap(int key);
	
	
	public String getResName();
	
	public void recycle();
}
