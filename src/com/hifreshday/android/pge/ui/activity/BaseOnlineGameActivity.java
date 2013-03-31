package com.hifreshday.android.pge.ui.activity;

import com.hifreshday.android.pge.engine.online.IGameViewAgent;
import com.hifreshday.android.pge.engine.online.OnlineEngine;

public abstract class BaseOnlineGameActivity extends BaseGameActivity {
	@Override
	protected void doGameLoad() {
		super.doGameLoad();
		((OnlineEngine)engine).setViewAgent((IGameViewAgent)engine.getScene());
		((OnlineEngine)engine).getGameNetworking().setGameViewAgent(
				((OnlineEngine)engine).getViewAgent());
		((OnlineEngine)engine).getViewAgent().setGameNetworking(
				((OnlineEngine)engine).getGameNetworking());
	}
}
