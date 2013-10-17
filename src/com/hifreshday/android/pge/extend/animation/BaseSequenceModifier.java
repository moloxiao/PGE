package com.hifreshday.android.pge.extend.animation;

import java.util.List;

/**
 * 用于处理多个顺序播放的动画
 * @author molo
 * @since 2013-10-17
 *
 */
public class BaseSequenceModifier implements IModifier {

	private static final int NO_MODIFIER_NEED_UPDATE = -1;
	
	private List<IModifier> modifierLists;
	private int currentModifier;
	
	protected boolean removeWhenFinish;
	protected boolean isFinished = false;
	
	public BaseSequenceModifier(List<IModifier> modifierLists) {
		if(modifierLists != null && modifierLists.size() > 0 ){
			this.modifierLists = modifierLists;
			currentModifier = 0;
		}else {
			isFinished = true;
			currentModifier = NO_MODIFIER_NEED_UPDATE;
		}
	}
	
	public void addModifier(IModifier modifier) {
		if(modifier != null && !modifier.isFinished()) {
			modifierLists.add(modifier);
		}
	}

	@Override
	public void reset() {
		currentModifier = 0;
		if(modifierLists != null && modifierLists.size() > 0 ){
			currentModifier = 0;
			isFinished = false;
			for(IModifier item : modifierLists) {
				item.reset();
			}
		}else {
			currentModifier = NO_MODIFIER_NEED_UPDATE;
			isFinished = true;
		}
		
		
	}

	@Override
	public void onUpdate(float secondsElapsed) {
		if(currentModifier != NO_MODIFIER_NEED_UPDATE) {
			modifierLists.get(currentModifier).onUpdate(secondsElapsed);
			if(modifierLists.get(currentModifier).isFinished()) {
				if(modifierLists.size() > (currentModifier+1)) {
					currentModifier++;
				}else {
					currentModifier = NO_MODIFIER_NEED_UPDATE;
					isFinished = true;
				}
			}
		}
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
	
}
