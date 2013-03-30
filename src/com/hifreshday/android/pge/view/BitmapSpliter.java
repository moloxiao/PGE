package com.hifreshday.android.pge.view;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class BitmapSpliter {

	public final static int VERTICAL = 1;
	public final static int HORIZONTAL = 0;
	
	/**
	 * 根据图片大小和图片的方向将图片切成连续的rect
	 * @param source 原始图片
	 * @param totalNum 要切的数量
	 * @param direction 切的方向
	 * @return 切好的Rect
	 */
	public static Rect[] splitBitmap2Rect(Bitmap source, int totalNum, int direction){
		int width = source.getWidth();
		int height = source.getHeight();
		
		if(direction == VERTICAL){
			height = height /totalNum;
		}else{
			width = width/totalNum;
		}
		Rect[] result = new Rect[totalNum];
		int startX = 0;
		int startY = 0;
		for(int i = 0;i<totalNum;i++){
			Rect rect = new Rect();
			rect.set(startX, startY, startX + width, startY + height);
			if(direction == VERTICAL){
				startY += height;
			}else{
				startX += width;
			}
			result[i] = rect;
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param source 原始图片
	 * @param totalNum 总图片数量
	 * @param index 要取第几张图片，下标从0开始
	 * @param direction 图片的方向
	 * @return
	 */
	public static Rect splitBitmap2Rect(Bitmap source, int totalNum, int index, int direction){
		int width = source.getWidth();
		int height = source.getHeight();
		int startX = 0;
		int startY = 0;
		if(direction == VERTICAL){
			height = height /totalNum;
			startY += index * height;
		}else{
			width = width/totalNum;
			startX += index * width;
		}
		Rect rect = new Rect();
		rect.set(startX, startY, startX + width, startY + height);
		return rect;
	}
	
}
