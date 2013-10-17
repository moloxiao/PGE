package com.hifreshday.android.pge.extend.animation;

/**
 * 动画接口
 * @author molo
 * @since 2013-10-17
 */
public interface IModifier {

	/**
	 * 充值动画状态，在动画结束的时候如果希望循环播放，可以调用此方法恢复初始化
	 */
	void reset();
	
	/**
	 * 每个时钟周期调用一次。
	 * @param secondsElapsed
	 */
	void onUpdate(float secondsElapsed);
	
	/**
	 * 判断动画是否结束。
	 * @return true，结束。false，进行中。
	 */
	boolean isFinished();
	
	/**
	 * 动画结束时的回调
	 * @author molo
	 *
	 */
	public interface IModifierListener {
		void onModifierStoped();
	}
}
