package com.hifreshday.android.pge.view.res;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameBitmapUtil {
	
	/**
	 * 加载图片资源,从res目录
	 * 
	 * @param res
	 * @param resId
	 * @return
	 */
	public static Bitmap loadBitmap(Resources res, int resId) {
		Bitmap bmp = BitmapFactory.decodeResource(res, resId);
		return bmp;
	}
	
	/**
	 * 加载图片资源，从assets目录
	 * @param assets
	 * @param fileName
	 * @return
	 */
	public static Bitmap loadBitmap(AssetManager assets, String fileName) {
		InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            // TODO : 考虑处理为放置默认图片
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return bitmap;
	}
	
	/**
	 * 从sd卡外部存储读取文件
	 * @param filepath
	 * @return
	 */
	public static Bitmap loadBitmap(String filepath) {
		File file = new File(filepath);
        if (file.exists()) {
                return BitmapFactory.decodeFile(filepath);
        }
        return null;
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