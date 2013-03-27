package com.hifreshday.android.pge.input.touch.controler;

import com.hifreshday.android.pge.input.touch.ITouch;

public interface ITouchControler extends ITouch{

	public void registerTouch(ITouch touch);
	public void unregisterTouch(ITouch touch);
	public void reset();
}
