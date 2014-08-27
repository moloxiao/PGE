package com.hifreshday.android.pge.audio.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.hifreshday.android.pge.audio.sound.impl.SoundImpl;

public class PgeSoundManager {
	
	public static final int UN_INIT_ID = -100;
	
	public static final int MODEL_INITFIRST = 0;
	public static final int MODEL_INITLATER = 1;

	private SoundPool soundPool;
	private int model;
	
	public PgeSoundManager(int model) {
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		this.model = model;
	}
	
	public Sound newSound(String filename) {
		try{
			int soundId = UN_INIT_ID;
			if(model == MODEL_INITFIRST){
				soundId = soundPool.load(filename, 0);
			}
			return new SoundImpl(soundId, soundPool, filename);
		}catch(Exception e) {
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}
	
	public Sound newSound(Context context,int resId){		
		Integer id = Integer.valueOf(resId);
		Log.i("MCH","id=" + id);
		if(id == null){
			throw new RuntimeException("Couldn't find id '" + id + "'");
		}
		int soundId = UN_INIT_ID;
		if(model == MODEL_INITFIRST){
			soundId = soundPool.load(context, resId, 0);
		}
		Log.i("MCH","soundId=" + soundId);
		return new SoundImpl(context,soundId, soundPool, resId);
	}
}
