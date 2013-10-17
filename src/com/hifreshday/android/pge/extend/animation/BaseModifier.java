package com.hifreshday.android.pge.extend.animation;

/**
 * 基本的动画抽象
 * @author molo
 * @since 2013-10-17
 */
public abstract class BaseModifier implements IModifier {

	protected boolean removeWhenFinish;
	protected int startTime;
	protected int duration;
	protected IModifierListener listener;
	protected boolean isFinished = false;
	protected int elapsed;
	
	/**
	 * 
	 * @param startTime 动画开始播放的时间。单位毫秒。如果立即播放则填写0.2秒后播放填写2000.
	 * @param duration 动画持续时间。单位毫秒。
	 * @param listener
	 */
	public BaseModifier(int startTime, int duration, IModifierListener listener) {
		this.startTime = startTime;
		this.duration = duration;
		this.listener = listener;
	}
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
