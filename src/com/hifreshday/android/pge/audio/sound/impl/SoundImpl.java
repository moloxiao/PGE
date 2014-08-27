package com.hifreshday.android.pge.audio.sound.impl;

import android.content.Context;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;

import com.hifreshday.android.pge.audio.sound.PgeSoundManager;
import com.hifreshday.android.pge.audio.sound.Sound;

public class SoundImpl implements Sound {
	
	private int soundId;
	private String filename;
	private SoundPool soundPool;
	private int resId;
	private Context context;
	
	public SoundImpl(int soundId, SoundPool soundPool, String filename) {
		super();
		this.soundId = soundId;
		this.soundPool = soundPool;
		this.filename = filename;
	}
	
	public SoundImpl(Context context,int soundId,SoundPool soundPool,int resId){
		super();
		this.context = context;
		this.soundId = soundId;
		this.soundPool = soundPool;
		this.resId = resId;
	}

	@Override
	public void play(final float volume) {
		Log.i("MCH","allowPlay=" + allowPlay);
		if(allowPlay) {
			if(soundId == PgeSoundManager.UN_INIT_ID) {
				soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
					@Override
					public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
						soundId = sampleId;
						soundPool.play(soundId, volume, volume, 0, 0, 1);
					}
				});
				if(filename != null && !filename.equals("")){
					soundPool.load(filename, 0);
				}else{
					soundPool.load(context, resId, 0);
				}
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
