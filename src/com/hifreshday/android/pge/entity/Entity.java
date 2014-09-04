package com.hifreshday.android.pge.entity;

import java.util.ArrayList;

import com.hifreshday.android.pge.engine.handler.IFixUpdateHandler;
import com.hifreshday.android.pge.engine.options.EngineOptions;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class Entity implements IEntity {

	private IEntity parent;

	private int px;
	private int py;

	protected boolean needRemove = false;
	protected boolean visible = true;
	protected boolean ignoreUpdate = false;
	protected boolean childrenVisible = true;
	protected boolean childrenIgnoreUpdate = false;
	private boolean needFixUpdate = false;
	private boolean childNeedFixUpdate = false;

	protected ArrayList<IEntity> children;

	@Override
	public void onDraw(Canvas canvas) {
		if (isVisible()) {
			onDrawSelf(canvas);
			if (children != null && childrenVisible) {
				for (IEntity entity : this.children) {
					entity.onDraw(canvas);
				}
			}
		}
	}

	protected void onDrawSelf(Canvas canvas) {
		// child need rewrite this method if need draw
	}

	private float bufferFixTime = 0.0f;

	@Override
	public void onUpdate(final float secondsElapsed) {
		if (!ignoreUpdate) {
			if (isNeedFixUpdate()) {
				bufferFixTime += secondsElapsed;
				while (bufferFixTime >= 1 / IFixUpdateHandler.FIX_STEP) {
					bufferFixTime -= 1 / IFixUpdateHandler.FIX_STEP;
					onManagerFixUpdate();
				}
			}
			onManageUpdate(secondsElapsed);
		}
	}

	protected void onManagerFixUpdate() {
		if (isNeedFixUpdate()) {
			onFixUpdate();
		}
		if (children != null && !childrenIgnoreUpdate) {
			for (IEntity entity : this.children) {
				if (entity.isChildNeedFixUpdate()) {
					entity.onFixUpdate();
				}
			}
		}
	}

	protected void onManageUpdate(final float secondsElapsed) {
		onUpdateSelf(secondsElapsed);

		if (children != null && !childrenIgnoreUpdate) {
			for (IEntity entity : this.children) {
				entity.onUpdate(secondsElapsed);
			}
			detachChilds();
		}

	}

	private void detachChilds() {
		ArrayList<IEntity> buffer = new ArrayList<IEntity>();
		boolean needDo = false;
		for (IEntity entity : children) {
			if (entity.isNeedRemove()) {
				buffer.add(entity);
				needDo = true;
			}
		}
		if (needDo) {
			for (IEntity entity : buffer) {
				this.children.remove(entity);
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
		this.px = EngineOptions.getOffsetX()
				+ (int) (pX * EngineOptions.getScreenScaleX());
		this.py = EngineOptions.getOffsetY()
				+ (int) (pY * EngineOptions.getScreenScaleY());
	}

	@Override
	public boolean attachChild(IEntity entity) {
		if (entity == null) {
			return false;
		}
		if (children == null) {
			children = new ArrayList<IEntity>();
		}
		children.add(entity);
		entity.setParent(this);
		if (entity.isNeedFixUpdate()) {
			setNeedFixUpdate(true);
		}
		return true;
	}

	@Override
	public boolean detachChild(IEntity entity) {
		if (children == null || entity == null) {
			return false;
		}
		entity.setNeedRemove(true);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	@Override
	public boolean isNeedRemove() {
		return needRemove;
	}

	@Override
	public void setNeedRemove(boolean remove) {
		needRemove = remove;
	}

	@Override
	public void onFixUpdate() {
		// 需要重载,处理玩家物理模拟的行为
	}

	@Override
	public boolean isNeedFixUpdate() {
		return needFixUpdate;
	}

	@Override
	public void setNeedFixUpdate(boolean needFixUpdate) {
		this.needFixUpdate = needFixUpdate;
	}

	@Override
	public boolean isChildNeedFixUpdate() {
		return childNeedFixUpdate;
	}

	@Override
	public void setChildNeedFixUpdate(boolean childNeedFixUpdate) {
		this.childNeedFixUpdate = childNeedFixUpdate;

	}

}
