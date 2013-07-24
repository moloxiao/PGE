package com.hifreshday.android.pge.audio.sound;

public interface Sound {
	
	public static boolean allowPlay = true;

	public void play(float volume);
    public void dispose();
}
