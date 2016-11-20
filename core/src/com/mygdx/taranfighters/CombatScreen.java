package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CombatScreen implements Screen {
	SpriteBatch batch;
	Texture img, img1, img2, img3;
	float iTimeCounter=0;

	
	@Override
	public void show() {
		batch = new SpriteBatch();
		img1 = new Texture("iul/iul_walk1.png");
		img2 = new Texture("iul/iul_walk2.png");
		img3 = new Texture("iul/iul_walk3.png");
	}

	@Override
	public void render (float arg1) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		iTimeCounter += arg1;
		if (iTimeCounter % 1 < 0.3){img = img1;}
		else if (iTimeCounter % 1  < 0.6){img = img2;}
		else {
			img = img3;
		}
		Gdx.app.log("CombatScreen", "rest " + (iTimeCounter) + " " + iTimeCounter %1 );
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
