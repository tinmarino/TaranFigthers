package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class JakOverScreen implements Screen {
	TextureAtlas atlas;
	Animation animation;
	SpriteBatch batch;
	float elapsedTime;
	BitmapFont font;

	@Override
	public void show() {
        batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("video/over/jac_over360_4.atlas"));
		animation = new Animation(0.1f, atlas.getRegions());
		font = new BitmapFont(Gdx.files.internal("font/zreak-nfi.fnt"));
		font.getData().setScale(1,1);
		font.setColor(new Color(0x8A0707ff));
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
			font.draw(batch, "Game Over!" , 1f/4  * width, 1f/2 * height);
        batch.end();
	}

	@Override
	public void dispose() {
        atlas.dispose();
	}

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resize(int arg0, int arg1) { }

	@Override
	public void resume() { }


}
