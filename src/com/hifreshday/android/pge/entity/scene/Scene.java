package com.hifreshday.android.pge.entity.scene;

import android.content.res.Resources;

import com.hifreshday.android.pge.entity.Entity;

public abstract class Scene extends Entity {
	
	public abstract void onLoadResources(Resources res);
	public abstract void onUnloadResources();
}
