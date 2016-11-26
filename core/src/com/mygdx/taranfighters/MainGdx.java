package com.mygdx.taranfighters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MainGdx extends Game {
	
	@Override
	public void create (){
		G.game = this;
		screen = new CombatScreen();
		screen.show();
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}
}
