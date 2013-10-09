package com.hifreshday.android.pge.entity;

import java.util.ArrayList;

import com.hifreshday.android.pge.engine.options.EngineOptions;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class Entity implements IEntity{
	
	private IEntity parent;
	
	private int px;
	private int py;
	
	protected boolean visible = true;
	protected boolean ignoreUpdate = false;
	protected boolean childrenVisible = true;
	protected boolean childrenIgnoreUpdate = false;
	
	protected ArrayList<IEntity> children;
	
	@Override
	public void onDraw(Canvas canvas) {
		if(isVisible()) { 
			onDrawSelf(canvas);
			if(children != null && childrenVisible) {
				for(IEntity entity : this.children) {
					entity.onDraw(canvas);
				}
			}
		}
	}
	
	protected void onDrawSelf(Canvas canvas) {
		// child need rewrite this method if need draw
	}

	@Override
	public void onUpdate(final float secondsElapsed) {
		if(!ignoreUpdate) {
			onManageUpdate(secondsElapsed);
		}
	}
	
	protected void onManageUpdate(final float secondsElapsed) {
		onUpdateSelf(secondsElapsed);
		
		if(children != null && !childrenIgnoreUpdate) {
			for(IEntity entity : this.children) {
				entity.onUpdate(secondsElapsed);
			}
		}
	}
	
	protected void onUpdateSelf(final float secondsElapsed) {
		// child need rewrite this method if need update
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean isIgnoreUpdate() {
		return this.ignoreUpdate;
	}

	@Override
	public void setIgnoreUpdate(boolean ignoreUpdate) {
		this.ignoreUpdate = ignoreUpdate;
	}

	@Override
	public boolean isChildrenVisible() {
		return childrenVisible;
	}

	@Override
	public void setChildrenVisible(boolean childrenVisible) {
		this.childrenVisible = childrenVisible;
	}

	@Override
	public boolean isChildrenIgnoreUpdate() {
		return childrenIgnoreUpdate;
	}

	@Override
	public void setChildrenIgnoreUpdate(boolean childrenIgnoreUpdate) {
		this.childrenIgnoreUpdate = childrenIgnoreUpdate;
	}

	@Override
	public boolean hasParent() {
		return (this.parent != null);
	}

	@Override
	public IEntity getParent() {
		return this.parent;
	}

	@Override
	public void setParent(IEntity entity) {
		this.parent = entity;
	}

	@Override
	public int getX() {
		return this.px;
	}

	@Override
	public int getY() {
		return this.py;
	}

	@Override
	public void setPosition(int pX, int pY) {
		this.px = EngineOptions.getOffsetX() + (int)(pX*EngineOptions.getScreenScaleX());
		this.py = EngineOptions.getOffsetY() + (int)(pY*EngineOptions.getScreenScaleY());
	}

	@Override
	public boolean attachChild(IEntity entity) {
		if(entity == null){
			return false;
		}
		if(children == null) {
			children = new ArrayList<IEntity>();
		}
		children.add(entity);
		entity.setParent(this);
		return true;
	}
	

	@Override
	public boolean detachChild(IEntity entity) {
		if(children == null || entity == null){
			return false;
		}
		return this.children.remove(entity);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

}
