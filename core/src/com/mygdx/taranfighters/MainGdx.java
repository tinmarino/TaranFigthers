package com.mygdx.taranfighters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MainGdx extends Game {
	
	@Override
	public void create (){
		Gdx.app.log("MainGdx:", "TaranFigthers Starting");
		G.game = this;
		screen = new PlatformScreen();
		screen.show();
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}
}
