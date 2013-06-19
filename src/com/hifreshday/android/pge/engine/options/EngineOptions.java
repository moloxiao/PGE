package com.hifreshday.android.pge.engine.options;

public class EngineOptions {
	
	public static final float DEFAULT_SCREEN_WIDTH = 800.0f;
	public static final float DEFAULT_SCREEN_HEIGHT = 480.0f;
	
	public static final int GAME_SCALE_MODEL_NORMAL = 0;
	public static final int GAME_SCALE_MODEL_KEEPWIDTHANDHEIGHT = 1;
	
	private static float screenScaleX = 1.0f;
	private static float screenScaleY = 1.0f;
	
	private static int screenWidth ;
	private static int screenHeight ;
	
	private static int offsetX = 0;
	private static int offsetY = 0;
	
	private static int realScreenWidth;
	private static int realScreenHeight;

	private GameType gameType ;
	private static ScaleModel model;
	private boolean openHighQuality;
	
	public EngineOptions(int screenWidth, int screenHeight, ScaleModel model,
			boolean openHighQuality){
		this.gameType = GameType.ONLINEGAME;
		this.openHighQuality = openHighQuality;
		EngineOptions.model = model;
		initScreenInfo(screenWidth, screenHeight);
	}

	public GameType getGameType() {
		return gameType;
	}
	
	private void initScreenInfo(int screenWidth, int screenHeight) {
		EngineOptions.realScreenWidth = screenWidth;
		EngineOptions.realScreenHeight = screenHeight;
		
		EngineOptions.screenScaleX = EngineOptions.realScreenWidth/DEFAULT_SCREEN_WIDTH;
		EngineOptions.screenScaleY = EngineOptions.realScreenHeight/DEFAULT_SCREEN_HEIGHT;
		
		if(EngineOptions.model == ScaleModel.FULLSCREEN) {
			EngineOptions.screenWidth = EngineOptions.realScreenWidth;
			EngineOptions.screenHeight = EngineOptions.realScreenHeight;
			return ;
		}
		
		if(EngineOptions.screenScaleX == EngineOptions.screenScaleY) {
			EngineOptions.screenWidth = EngineOptions.realScreenWidth;
			EngineOptions.screenHeight = EngineOptions.realScreenHeight;
		}else if(EngineOptions.screenScaleX > EngineOptions.screenScaleY) {
			EngineOptions.screenScaleX = EngineOptions.screenScaleY;
			EngineOptions.screenWidth = (int)(DEFAULT_SCREEN_WIDTH*EngineOptions.screenScaleX);
			EngineOptions.offsetX = (EngineOptions.realScreenWidth - EngineOptions.screenWidth)/2;
			EngineOptions.screenHeight = EngineOptions.realScreenHeight;
		}else {
			EngineOptions.screenScaleY = EngineOptions.screenScaleX;
			EngineOptions.screenHeight = (int)(DEFAULT_SCREEN_HEIGHT*EngineOptions.screenScaleY);
			EngineOptions.offsetY = (EngineOptions.realScreenHeight - EngineOptions.screenHeight)/2;
			EngineOptions.screenWidth = EngineOptions.realScreenWidth;
		}
	}

	public static int getOffsetX() {
		return offsetX;
	}

	public static int getOffsetY() {
		return offsetY;
	}

	public static int getRealScreenWidth() {
		return realScreenWidth;
	}

	public static int getRealScreenHeight() {
		return realScreenHeight;
	}

	public static float getScreenScaleX() {
		return screenScaleX;
	}

	public static float getScreenScaleY() {
		return screenScaleY;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public boolean isOpenHighQuality() {
		return openHighQuality;
	}
	
	public void closeOpenHightQuality() {
		this.openHighQuality = false;
	}
}
