package com.mygdx.taranfighters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainGdx extends Game {
	
	@Override
	public void create (){
		Gdx.app.log("MainGdx:", "TaranFigthers Starting");
		G.init();
		G.game = this;
		Gdx.input.setCatchBackKey(true);
		screen = new ChooseScreen();
		//screen = new PlatformScreen(G.CHAR.TIN, G.LEVEL.L6);
		screen.show();
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}



	@Override
	public void setScreen (Screen screen) {
		if (this.screen != null){
			this.screen.dispose();
		}
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}
}
