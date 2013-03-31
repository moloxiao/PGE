package com.hifreshday.android.pge.engine.online;

import com.hifreshday.android.pge.engine.Engine;
import com.hifreshday.android.pge.engine.options.EngineOptions;

public class OnlineEngine extends Engine {
	
	private IGameNetworking gameNetworking;
	private IGameViewAgent viewAgent;

	public OnlineEngine(EngineOptions options, IGameNetworking gameNetworking) {
		super(options);
		setGameNetworking(gameNetworking);
	}

	public IGameNetworking getGameNetworking() {
		return gameNetworking;
	}

	public void setGameNetworking(IGameNetworking gameNetworking) {
		this.gameNetworking = gameNetworking;
	}

	public IGameViewAgent getViewAgent() {
		return viewAgent;
	}

	public void setViewAgent(IGameViewAgent viewAgent) {
		this.viewAgent = viewAgent;
	}
}
