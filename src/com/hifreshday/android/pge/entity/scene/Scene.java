package com.hifreshday.android.pge.entity.scene;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.MotionEvent;

import com.hifreshday.android.pge.engine.options.EngineOptions;
import com.hifreshday.android.pge.entity.Entity;
import com.hifreshday.android.pge.entity.IEntity;
import com.hifreshday.android.pge.input.touch.ITouch;
import com.hifreshday.android.pge.input.touch.controler.ITouchControler;
import com.hifreshday.android.pge.input.touch.controler.TouchControler;

public abstract class Scene extends Entity implements ITouch{
	
	private ITouchControler touchControler = new TouchControler();
	
	private int width ;
	private int height ;
	
	public void setScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public abstract void onLoadResources(Resources res);
	public abstract void onUnloadResources();
	
	public void registerTouch(ITouch touch) {
		touchControler.registerTouch(touch);
	}
	
	public void unregisterTouch(ITouch touch) {
		touchControler.unregisterTouch(touch);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return touchControler.onTouchEvent(event);
	}
	
//	private Rect sceneRect;
//	private Paint paint;
//	private PorterDuffXfermode clearmodel;
//	private PorterDuffXfermode paintmodel;
//	private Bitmap sceneBmp;
//	private Canvas sceneCanvas;
//	
//	@Override
//	public void onDraw(Canvas canvas) {
//		if(isVisible()) { 
//			onDrawSelf(canvas);
//			
//			if(sceneRect == null) {
//				sceneRect = new Rect(
//						EngineOptions.getOffsetX(), 
//						EngineOptions.getOffsetY(), 
//						EngineOptions.getOffsetX() + EngineOptions.getScreenWidth(), 
//						EngineOptions.getOffsetY() + EngineOptions.getScreenHeight());
//			}
//			
//			if(sceneBmp == null){
//				sceneBmp = Bitmap.createBitmap(
//						EngineOptions.getScreenWidth(),
//						EngineOptions.getScreenHeight(), 
//						Config.ARGB_8888);
//			}
//					
//			if(sceneCanvas==null){
//				sceneCanvas = new Canvas(sceneBmp);
//			}
//			if(paint==null){
//				paint = new Paint();
//			}
//			if(clearmodel == null) {
//				clearmodel = new PorterDuffXfermode(Mode.CLEAR);
//			}
//			paint.setXfermode(clearmodel);
//			sceneCanvas.drawPaint(paint);
//			if(paintmodel == null){
//				paintmodel = new PorterDuffXfermode(Mode.SRC);
//			}
//			paint.setXfermode(paintmodel);
//			
//			if(children != null && childrenVisible) {
//				for(IEntity entity : this.children) {
//					entity.onDraw(sceneCanvas);
//				}
//			}
//			canvas.drawBitmap(sceneBmp, null, sceneRect, null);
//			
//			sceneCanvas = null;
//			sceneBmp = null;
//		}
//	}
}
