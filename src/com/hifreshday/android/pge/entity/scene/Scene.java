package com.hifreshday.android.pge.entity.scene;

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.MotionEvent;
import com.hifreshday.android.pge.entity.Entity;
import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.input.touch.ITouch;
import com.hifreshday.android.pge.input.touch.controler.ITouchControler;
import com.hifreshday.android.pge.input.touch.controler.TouchControler;

public abstract class Scene extends Entity implements ITouch{
	
	private ITouchControler touchControler = new TouchControler();
	
	protected List<Sprite> needRecycleLists = new ArrayList<Sprite>();
	
	private int width ;
	private int height ;
	
	public void setScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void addNeedRecyle(Sprite sprite) {
		needRecycleLists.add(sprite);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public abstract void onLoadResources(Resources res, AssetManager asset);
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
	
	public void recycle(){
		if(needRecycleLists != null && needRecycleLists.size()>0) {
			for(Sprite sprite : needRecycleLists) {
				if(sprite.isNeedRecyle()) {
					sprite.recycle();
				}
			}
		}
	}
}
