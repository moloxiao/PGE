package com.hifreshday.android.pge.input.touch.controler;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;
import com.hifreshday.android.pge.input.touch.ITouch;

public class TouchControler implements ITouchControler {

	private final ArrayList<ITouch> touchList = new ArrayList<ITouch>();
	
	@Override
	public void registerTouch(ITouch touch) {
		if(touch != null) {
			touchList.add(touch);
		}

	}

	@Override
	public void unregisterTouch(ITouch touch) {
		if(touch != null) {
			touchList.remove(touch);
		}
	}

	@Override
	public void reset() {
		touchList.clear();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(touchList.size() > 0) {
			for(ITouch touch : touchList) {
				if(touch.onTouchEvent(event)) {
					return true;
				}
			}
		}
		return false;
	}

}
