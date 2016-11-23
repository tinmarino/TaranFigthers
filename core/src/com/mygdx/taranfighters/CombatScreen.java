package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class CombatScreen implements Screen, InputProcessor {
	SpriteBatch batch;
	Texture img, img1, img2, img3;
	float iTimeCounter=0;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
	Character char1, char2;
	Sprite testSprite; 
	
	@Override
	public void show() {
		batch = new SpriteBatch();


		camera = new OrthographicCamera();
		camera.position.x = 0;
		camera.position.y = 0;
        tiledMap = new TmxMapLoader().load("map/castleArena1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);

		char1 = new Iul();
		testSprite = new Sprite(new Texture("iul/iul_walk1.png"));

		camera.position.x = 0;
		camera.position.y = 0;
		camera.viewportWidth = 300;
		camera.viewportHeight = 300;
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			
			testSprite.setSize(128 , 128);
			Gdx.app.log("CombatScreen", "Caemra " + camera.position  + "Sprite " + testSprite.getX()   +   testSprite.getY()   );
			testSprite.draw(batch);
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

	@Override
	public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-128,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(128,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,-128);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,128);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());


		testSprite.setPosition(camera.position.x-testSprite.getWidth()/2, camera.position.y-testSprite.getHeight()/2);
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
