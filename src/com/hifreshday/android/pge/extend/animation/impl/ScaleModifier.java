package com.hifreshday.android.pge.extend.animation.impl;

import com.hifreshday.android.pge.extend.animation.BaseModifier;

/**
 * 缩放动画。
 * @author molo
 * @since 2013-10-17
 *
 */
public abstract class ScaleModifier extends BaseModifier {

	private float startScale = 0.0f;
	private float endScale = 1.0f;
	
	/**
	 * 
	 * @param startTime
	 * @param duration
	 * @param listener
	 * @param startScale 初始的缩放倍数，默认值为0.0f，注意，值必须>=0.0f
	 * @param endScale 结束的缩放倍数，默认值为1.0f，注意，值必须>=0.0f
	 */
	public ScaleModifier(int startTime, int duration, IModifierListener listener, 
			float startScale, float endScale) {
		super(startTime, duration, listener);
		this.startScale = startScale;
		this.endScale = endScale;
	}

	@Override
	public void reset() {
		isFinished = false;

	}

	@Override
	public void onUpdate(float secondsElapsed) {
		if(isFinished) {
			return;
		}
		this.elapsed += secondsElapsed*1000;
		if(this.elapsed > (this.startTime + this.duration) ) {
			isFinished = true;
			if(listener != null) {
				listener.onModifierStoped();
			}
			onGetScale(endScale);
			return;
		}

		float per = (elapsed-startTime)/(this.duration);
		if(per < 0 ) {
			per = 0.0f;
		}
		onGetScale(startScale + (endScale - startScale)*per );
	}

	abstract void onGetScale(float scale);
}
