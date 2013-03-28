package com.hifreshday.android.pge.engine.options;

public class EngineOptions {
	
	public static final float DEFAULT_SCREEN_WIDTH = 800.0f;
	public static final float DEFAULT_SCREEN_HEIGHT = 480.0f;
	
	private static float screenScaleX = 1.0f;
	private static float screenScaleY = 1.0f;
	
	private static int screenWidth ;
	private static int screenHeight ;

	private GameType gameType ;
	
	public EngineOptions(int screenWidth, int screenHeight){
		this.gameType = GameType.ONLINEGAME;
		initScreenInfo(screenWidth, screenHeight);
	}

	public GameType getGameType() {
		return gameType;
	}
	
	private void initScreenInfo(int screenWidth, int screenHeight) {
		EngineOptions.screenWidth = screenWidth;
		EngineOptions.screenHeight = screenHeight;
		EngineOptions.screenScaleX = EngineOptions.screenWidth/DEFAULT_SCREEN_WIDTH;
		EngineOptions.screenScaleY = EngineOptions.screenHeight/DEFAULT_SCREEN_HEIGHT;
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
}
