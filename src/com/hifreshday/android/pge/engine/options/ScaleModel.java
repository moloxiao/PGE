package com.hifreshday.android.pge.engine.options;

public enum ScaleModel {

	FULLSCREEN(0), CONSTRAIN(1);
	
	private int model;

	private ScaleModel(int model) {
		this.model = model;
	}
	
	public static ScaleModel getScaleModel(int model) {
		switch(model) {
		case 0:
			return FULLSCREEN;
		case 1:
			return CONSTRAIN;
		 default:
	            throw new IllegalArgumentException("Illegal ScaleModel,ScaleModel=" + model);
		}
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}
	
}
