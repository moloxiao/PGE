package com.hifreshday.android.pge.engine.options;

public class EngineUtil {

	
	public static long getNanosecondsElapsed(final long lastTick)  {
		final long now = System.nanoTime();
		return EngineUtil.calculateNanosecondsElapsed(now, lastTick);
	}
	
	public static long calculateNanosecondsElapsed(final long pNow, final long pLastTick) {
		return pNow - pLastTick;
	}
}
