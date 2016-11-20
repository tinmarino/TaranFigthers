package com.mygdx.taranfighters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainGdx extends ApplicationAdapter {
	Screen currentScreen;
	
	@Override
	public void create () {
		currentScreen = new CombatScreen();
		currentScreen.show();
	}

	@Override
	public void render () {
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}
}
