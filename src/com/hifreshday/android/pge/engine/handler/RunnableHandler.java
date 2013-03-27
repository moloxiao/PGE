package com.hifreshday.android.pge.engine.handler;

import java.util.ArrayList;

public class RunnableHandler implements IUpdateHandler {

	private final ArrayList<Runnable> runnables = new ArrayList<Runnable>();

	@Override
	public synchronized void onUpdate(final float pSecondsElapsed) {

		synchronized (this.runnables) {
			int runnableCount = this.runnables.size();
            for(int i = 0; i < runnableCount; i++) {
                    this.runnables.remove(0).run();
            }
		}
	}

	public void reset() {
		this.runnables.clear();
	}

	public synchronized void postRunnable(final Runnable runnable) {
		synchronized (this.runnables) {
			this.runnables.add(runnable);
		}
	}
}
