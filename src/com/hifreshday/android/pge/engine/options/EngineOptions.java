package com.hifreshday.android.pge.engine.options;

public class EngineOptions {

	private GameType gameType ;
	
	public EngineOptions(){
		this.gameType = GameType.ONLINEGAME;
	}

	public GameType getGameType() {
		return gameType;
	}
	
}
