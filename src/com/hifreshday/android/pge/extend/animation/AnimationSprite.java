package com.hifreshday.android.pge.extend.animation;

import java.util.ArrayList;
import java.util.List;

import com.hifreshday.android.pge.entity.shape.sprite.Sprite;
import com.hifreshday.android.pge.view.res.IBitmapRes;

/**
 * 支持动画播放的精灵类。
 * @author molo
 *
 */
public abstract class AnimationSprite extends Sprite {

	private List<IModifier> modifierLists = new ArrayList<IModifier>();
	
	public AnimationSprite(IBitmapRes bitmapRes, int pX, int pY, int width,
			int height) {
		super(bitmapRes, pX, pY, width, height);
	}

	public void addModifier(IModifier modifier) {
		if(modifier != null && !modifier.isFinished()) {
			modifierLists.add(modifier);
		}
	}
	
	@Override
	protected void onUpdateSelf(float secondsElapsed) {
		if(modifierLists.size() > 0) {
			List<IModifier> removeBuffer = null ;
			for(IModifier item : modifierLists) {
				item.onUpdate(secondsElapsed);
				if(item.isFinished()) {
					if(removeBuffer == null) {
						removeBuffer = new ArrayList<IModifier>();
					}
					removeBuffer.add(item);
				}
			}
			if(removeBuffer != null) {
				for(IModifier item : removeBuffer) {
					modifierLists.remove(item);
				}
			}
			removeBuffer = null;
		}
	}
}
