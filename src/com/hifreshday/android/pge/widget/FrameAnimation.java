package com.hifreshday.android.pge.widget;

import java.util.Random;

import android.graphics.Bitmap;

/** <p>
 * An Animation stores a list of {@link Bitmap}s representing an animated sequence, e.g. for running or jumping. Each
 * region of an Animation is called a key frame, multiple key frames make up the animation.
 * </p>
 */
public class FrameAnimation {
	public static final int NORMAL = 0;
	public static final int REVERSED = 1;
	public static final int LOOP = 2;
	public static final int LOOP_REVERSED = 3;
	public static final int LOOP_PINGPONG = 4;
	public static final int LOOP_RANDOM = 5;

	final Bitmap[] keyFrames;
	public final float frameDuration;
	public final float animationDuration;

	private int playMode = NORMAL;

	/** Constructor, storing the frame duration and key frames.
	 * 
	 * @param frameDuration the time between frames in seconds.
	 * @param keyFrames the {@link Bitmap}s representing the frames. */
	public FrameAnimation (float frameDuration, Bitmap[] keyFrames) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.length * frameDuration;
		this.keyFrames = keyFrames;
		this.playMode = NORMAL;
	}

	/** Constructor, storing the frame duration, key frames and play type.
	 * 
	 * @param frameDuration the time between frames in seconds.
	 * @param keyFrames the {@link Bitmap}s representing the frames.
	 * @param playType the type of animation play (NORMAL, REVERSED, LOOP, LOOP_REVERSED, LOOP_PINGPONG, LOOP_RANDOM) */
	public FrameAnimation (float frameDuration, Bitmap[] keyFrames, int playType) {

		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.length * frameDuration;
		this.keyFrames = keyFrames;
		this.playMode = playType;
	}

	/** Returns a {@link Bitmap} based on the so called state time. This is the amount of seconds an object has spent in the
	 * state this Animation instance represents, e.g. running, jumping and so on. The mode specifies whether the animation is
	 * looping or not.
	 * 
	 * @param stateTime the time spent in the state represented by this animation.
	 * @param looping whether the animation is looping or not.
	 * @return the Bitmap representing the frame of animation for the given state time. */
	public Bitmap getKeyFrame (float stateTime, boolean looping) {
		// we set the play mode by overriding the previous mode based on looping
		// parameter value
		int oldPlayMode = playMode;
		if (looping && (playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == NORMAL)
				playMode = LOOP;
			else
				playMode = LOOP_REVERSED;
		} else if (!looping && !(playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == LOOP_REVERSED)
				playMode = REVERSED;
			else
				playMode = LOOP;
		}

		Bitmap frame = getKeyFrame(stateTime);
		playMode = oldPlayMode;
		return frame;
	}

	/** Returns a {@link Bitmap} based on the so called state time. This is the amount of seconds an object has spent in the
	 * state this Animation instance represents, e.g. running, jumping and so on using the mode specified by
	 * {@link #setPlayMode(int)} method.
	 * 
	 * @param stateTime
	 * @return the Bitmap representing the frame of animation for the given state time. */
	public Bitmap getKeyFrame (float stateTime) {
		int frameNumber = getKeyFrameIndex (stateTime);
		return keyFrames[frameNumber];
	}
	
	/** Returns the current frame number.
	 * @param stateTime
	 * @return current frame number */
	public int getKeyFrameIndex (float stateTime) {
		if(keyFrames.length == 1)
			return 0;

		int frameNumber = (int)(stateTime / frameDuration);
		switch (playMode) {
		case NORMAL:
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		case LOOP:
			frameNumber = frameNumber % keyFrames.length;
			break;
		case LOOP_PINGPONG:
			frameNumber = frameNumber % ((keyFrames.length * 2) - 2);
         if (frameNumber >= keyFrames.length)
            frameNumber = keyFrames.length - 2 - (frameNumber - keyFrames.length);
         break;
		case LOOP_RANDOM:
			frameNumber = new Random().nextInt(keyFrames.length);
			break;
		case REVERSED:
			frameNumber = Math.max(keyFrames.length - frameNumber - 1, 0);
			break;
		case LOOP_REVERSED:
			frameNumber = frameNumber % keyFrames.length;
			frameNumber = keyFrames.length - frameNumber - 1;
			break;

		default:
			// play normal otherwise
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		}
		
		return frameNumber;
	}

	/** Returns the animation play mode. Will be one of the following: Animation.NORMAL, Animation.REVERSED, Animation.LOOP,
	 * Animation.LOOP_REVERSED, Animation.LOOP_PINGPONG, Animation.LOOP_RANDOM */
	public int getPlayMode() {
		return playMode;
	}
	/** Sets the animation play mode.
	 * 
	 * @param playMode can be one of the following: Animation.NORMAL, Animation.REVERSED, Animation.LOOP, Animation.LOOP_REVERSED,
	 *           Animation.LOOP_PINGPONG, Animation.LOOP_RANDOM */
	public void setPlayMode (int playMode) {
		this.playMode = playMode;
	}

	/** Whether the animation would be finished if played without looping (PlayMode Animation#NORMAL), given the state time.
	 * @param stateTime
	 * @return whether the animation is finished. */
	public boolean isAnimationFinished (float stateTime) {
		int frameNumber = (int)(stateTime / frameDuration);
		return keyFrames.length - 1 < frameNumber;
	}
}

