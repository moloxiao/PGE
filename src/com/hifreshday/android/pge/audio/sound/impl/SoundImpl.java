package com.hifreshday.android.pge.audio.sound.impl;

import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import com.hifreshday.android.pge.audio.sound.PgeSoundManager;
import com.hifreshday.android.pge.audio.sound.Sound;

public class SoundImpl implements Sound {
	
	private int soundId;
	private String filename;
	private SoundPool soundPool;
	
	private boolean loadSuccess = false;
	
	public SoundImpl(int soundId, SoundPool soundPool, String filename) {
		super();
		this.soundId = soundId;
		this.soundPool = soundPool;
		this.filename = filename;
	}

	@Override
	public void play(final float volume) {
		if(allowPlay) {
			if(soundId == PgeSoundManager.UN_INIT_ID) {
				soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
					@Override
					public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
						soundId = sampleId;
						soundPool.play(soundId, volume, volume, 0, 0, 1);
					}
				});
				soundPool.load(filename, 0);
			}else {
				soundPool.play(soundId, volume, volume, 0, 0, 1);
			}
		}
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}

}
