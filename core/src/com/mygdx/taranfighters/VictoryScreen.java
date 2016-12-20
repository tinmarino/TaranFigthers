package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class VictoryScreen implements Screen, InputProcessor {
	TextureAtlas atlas;
	Animation animation;
	float elapsedTime;
	boolean isMusicPlaying = false;
	// disposable
	Texture texture;
	SpriteBatch batch;
	Music music;

	@Override
	public void show() {
        batch = new SpriteBatch();

		// Video 
		atlas = new TextureAtlas(Gdx.files.internal("video/victory/victory.atlas"));
		animation = new Animation(0.1f, atlas.getRegions());

		// Blood
		texture = new Texture(Gdx.files.internal("img/blood1.png"));

		// Music
		music = G.music("music/sound/victory.mp3");
		music.setLooping(false);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int width  = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
        
        elapsedTime += delta;

        batch.begin();
			TextureRegion region = animation.getKeyFrame(elapsedTime);
			batch.draw(region, 0, 0 ,width, height);
			// Animation finished ?
			if (elapsedTime > 1.4){
				G.victoryFont.draw(batch, "Victory!" , 1.3f/4  * width, 1f/2 * height);
				//batch.draw(texture, 0, 0, width, height);
				if (!isMusicPlaying){
					isMusicPlaying = true;
					music.play();
				}
				Gdx.input.setInputProcessor(this);
			}
        batch.end();
	}

	@Override
	public void dispose() {
        atlas.dispose();
		batch.dispose();
		music.dispose();
	}

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resize(int arg0, int arg1) { }

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int arg0) {
		G.game.setScreen(new ChooseScreen());
		return false;
	}
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		G.game.setScreen(new ChooseScreen());
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) { return false; }

	@Override
	public boolean keyUp(int arg0) { return false; }

	@Override
	public boolean mouseMoved(int arg0, int arg1) { return false; }

	@Override
	public boolean scrolled(int arg0) { return false; }


	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) { return false; }

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) { return false; }

}
