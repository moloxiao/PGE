package com.hifreshday.android.pge.extend.animation;

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
	 * 是否在动画播放结束时删除动画
	 * @return
	 */
	boolean isRemoveWhenFinish();
	
	
	void setRemoveWhenFinish(boolean removeWhenFinish);
	
	/**
	 * 动画结束时的回调
	 * @author molo
	 *
	 */
	public interface IModifierListener {
		void onModifierStoped();
	}
}
