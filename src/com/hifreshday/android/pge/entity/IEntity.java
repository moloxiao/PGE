package com.hifreshday.android.pge.entity;

import com.hifreshday.android.pge.engine.handler.IUpdateHandler;
import com.hifreshday.android.pge.input.touch.ITouch;
import com.hifreshday.android.pge.view.IDrawable;

public interface IEntity extends IDrawable, IUpdateHandler, ITouch{

	public boolean isVisible();
	public void setVisible(final boolean visible);

	public boolean isIgnoreUpdate();
	public void setIgnoreUpdate(boolean ignoreUpdate);

	public boolean isChildrenVisible();
	public void setChildrenVisible(final boolean childrenVisible);

	public boolean isChildrenIgnoreUpdate();
	public void setChildrenIgnoreUpdate(boolean childrenIgnoreUpdate);

	public boolean hasParent();
	public IEntity getParent();
	public void setParent(final IEntity entity);

	public int getX();
	public int getY();
	public void setPosition(final int pX, final int pY);
	
	public boolean attachChild(final IEntity entity);
	public boolean detachChild(final IEntity entity);
	
}
