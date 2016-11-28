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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class CombatScreen implements Screen, InputProcessor {
	Texture img, img1, img2, img3;
	float iTimeCounter=0;
    OrthographicCamera camera, rightCamera, leftCamera, topCamera, bottomCamera;
	Iul char1, char2;
	Sprite testSprite; 
	World world;
	Box2DDebugRenderer  debugRenderer;
	Level level;
	Vector2 offset1 = new Vector2(-2, 0);
	Vector2 offset2 = new Vector2(2, 0);
	
	@Override
	public void show() {
		world = new World(new Vector2(0f, -10f), true);
		debugRenderer = new Box2DDebugRenderer();
		level = new Level("", world);

		camera = new OrthographicCamera();
		camera.position.x = 0;
		camera.position.y = 0;
		camera.viewportWidth = 1000;
		camera.viewportHeight = 1000;

		rightCamera = new OrthographicCamera();
		leftCamera = new OrthographicCamera();
		topCamera = new OrthographicCamera();
		bottomCamera = new OrthographicCamera();


        Gdx.input.setInputProcessor(this);

		char1 = new Iul(world);
		char2 = new Iul(world);
		char2.setPosition(12, 12);

		setContactListener();
	}

	@Override
	public void render (float delta) {
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		int lineWidth = Gdx.graphics.getWidth()/200;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	world.step(G.timestep, G.velocity_iterations, G.position_iterations);


		Gdx.app.log("Char1, char2 position", char2.x + ":" + char1.body.getPosition() + " , " + char2.body.getPosition());
		

		// CAMERA 
		camera.position.x = (char1.x + char2.x) / 2 * G.world2pixel;
		camera.position.y = (char1.y + char2.y) / 2 * G.world2pixel;

		float width = Math.abs(char1.x - char2.x) * 1.4f;
		float height = Math.abs(char1.y - char2.y) * 1.4f;

		Gdx.app.log("Comabt", "Width, height" + width + "," + height + "," + offset1);

		// Single Screen 
		if (width < 8 && height < 8){
			if (width / height > 1.5){height = width / 1.5f;}
			else{width = height * 1.5f;}
  			Gdx.gl.glViewport( 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight() );
			width = Math.max(8, width);
			height = Math.max(8, height);
			camera.viewportWidth = 8 * G.world2pixel;
			camera.viewportHeight = 8 * G.world2pixel;
        	camera.update();
        	level.tiledMapRenderer.setView(camera);
        	level.tiledMapRenderer.render();
			SpriteBatch batch = (SpriteBatch) level.tiledMapRenderer.getBatch();

			batch.begin();
				level.draw(batch, delta);
				char1.draw(batch, delta);
				char2.draw(batch, delta);
			batch.end();

			offset1 = new Vector2(char1.x - camera.position.x/G.world2pixel, char1.y - camera.position.y/G.world2pixel);
			offset2 = new Vector2(char2.x - camera.position.x/G.world2pixel, char2.y - camera.position.y/G.world2pixel);

			// DEBUG
			debugRenderer.render(world, camera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
		}

		// Vertical split 
		else {
			if (width >= 8)
			{
				if (width / height > 1.5){height = width / 1.5f;}
				else{width = height * 1.5f;}
				Character leftChar, rightChar;
				Vector2 leftOffset, rightOffset;
				if (char1.x < char2.x){
					leftChar = char1;
					rightChar = char2;
					leftOffset = offset1;
					rightOffset = offset2;
				}
				else{
					leftChar = char2;
					rightChar = char1;
					leftOffset = offset2;
					rightOffset = offset1;
				}

				// Left 
				Gdx.gl.glViewport(0, 0, (screenWidth-lineWidth)/2, screenHeight);
				leftCamera.position.x = (leftChar.x - leftOffset.x - 2) * G.world2pixel;
				leftCamera.position.y = (leftChar.y - leftOffset.y) * G.world2pixel;
				leftCamera.viewportWidth = 4 * G.world2pixel;
				leftCamera.viewportHeight = 8 * G.world2pixel;
				leftCamera.update();
				level.tiledMapRenderer.setView(leftCamera);
				level.tiledMapRenderer.render();
				SpriteBatch leftBatch = (SpriteBatch) level.tiledMapRenderer.getBatch();

				leftBatch.begin();
					level.draw(leftBatch, delta);
					leftChar.draw(leftBatch, delta);
				leftBatch.end();

				debugRenderer.render(world, leftCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
				

				// Right
				Gdx.gl.glViewport((screenWidth+lineWidth)/2, 0, (screenWidth-lineWidth)/2, screenHeight);
				rightCamera.position.x = (rightChar.x - rightOffset.x + 2) * G.world2pixel;
				rightCamera.position.y = (rightChar.y - rightOffset.y) * G.world2pixel;
				rightCamera.viewportWidth = 4 * G.world2pixel;
				rightCamera.viewportHeight = 8 * G.world2pixel;
				rightCamera.update();
				level.tiledMapRenderer.setView(rightCamera);
				level.tiledMapRenderer.render();
				SpriteBatch rightBatch = (SpriteBatch) level.tiledMapRenderer.getBatch();

				rightBatch.begin();
					level.draw(rightBatch, delta);
					rightChar.draw(rightBatch, delta);
				rightBatch.end();

				debugRenderer.render(world, rightCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );

				// Offsets
				//leftOffset.y = leftChar.y - leftCamera.position.y/G.world2pixel;
				//rightOffset.y = rightChar.y - rightCamera.position.y/G.world2pixel;
			}

			// Horizontal split
			else{
				if (width / height > 1.5){height = width / 1.5f;}
				else{width = height * 1.5f;}
				Character topChar, bottomChar;
				Vector2 topOffset, bottomOffset;
				if (char1.y < char2.y){
					bottomChar = char1;
					topChar = char2;
					bottomOffset = offset1;
					topOffset = offset2;
				}
				else{
					bottomChar = char2;
					topChar = char1;
					bottomOffset = offset2;
					topOffset = offset1;
				}


				// Top
				Gdx.gl.glViewport(0, (screenHeight+lineWidth)/2 , screenWidth, (screenHeight-lineWidth)/2);
				topCamera.position.x = (topChar.x - topOffset.x) * G.world2pixel;
				topCamera.position.y = (topChar.y - topOffset.y + 2) * G.world2pixel;
				topCamera.viewportWidth = 8 * G.world2pixel;
				topCamera.viewportHeight = 4 * G.world2pixel;
				topCamera.update();
				level.tiledMapRenderer.setView(topCamera);
				level.tiledMapRenderer.render();
				SpriteBatch topBatch = (SpriteBatch) level.tiledMapRenderer.getBatch();

				topBatch.begin();
					level.draw(topBatch, delta);
					topChar.draw(topBatch, delta);
				topBatch.end();

				debugRenderer.render(world, topCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );


				// Bottom
				Gdx.gl.glViewport(0, 0, screenWidth, (screenHeight - lineWidth)/2);
				bottomCamera.position.x = (bottomChar.x - bottomOffset.x) * G.world2pixel;
				bottomCamera.position.y = (bottomChar.y - bottomOffset.y - 2) * G.world2pixel;
				bottomCamera.viewportWidth = 8 * G.world2pixel;
				bottomCamera.viewportHeight = 4 * G.world2pixel;
				bottomCamera.update();
				level.tiledMapRenderer.setView(bottomCamera);
				level.tiledMapRenderer.render();
				SpriteBatch bottomBatch = (SpriteBatch) level.tiledMapRenderer.getBatch();

				bottomBatch.begin();
					level.draw(bottomBatch, delta);
					bottomChar.draw(bottomBatch, delta);
				bottomBatch.end();

				debugRenderer.render(world, bottomCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );

				// Offsets 
				//topOffset.x = topChar.x - topCamera.position.x/G.world2pixel;
				//bottomOffset.x = bottomChar.x - bottomCamera.position.y/G.world2pixel;
			}
		}
	}



	public void setContactListener(){
        world.setContactListener(new ContactListener() {
             @Override
             public void beginContact(Contact contact) {
                 if(contact.getFixtureA()  == char1.leftLegFixture 
				  ||contact.getFixtureB()  == char1.leftLegFixture){
					 Gdx.app.log("Combat", "I DEtect left leg");
				 }


                 if(contact.getFixtureA()  == char1.rightLegFixture 
				  ||contact.getFixtureB()  == char1.rightLegFixture){
					 Gdx.app.log("Combat", "I DEtect rigth leg");
				 }

				 if (G.isBodyContact(char1.body, contact)){
					 char1.manageContact(contact);
				 }
			 }
            	@Override
             public void endContact(Contact contact) { }
             @Override
             public void preSolve(Contact contact, Manifold oldManifold) { }
             @Override
             public void postSolve(Contact contact, ContactImpulse impulse) { }
         });
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
		if (char1.keyDown(keycode)){return true;}

        if(keycode == Input.Keys.NUM_1)
            level.tiledMap.getLayers().get(0).setVisible(!level.tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            level.tiledMap.getLayers().get(1).setVisible(!level.tiledMap.getLayers().get(1).isVisible());
        if(keycode == Input.Keys.A){
			Gdx.app.log("CombatScreen", "Changing to JacOverScreen");
			G.game.setScreen(new JakOverScreen());
		}


		//char1.spriteChanging.setPosition(camera.position.x-char1.spriteChanging.getWidth()/2, camera.position.y-char1.spriteChanging.getHeight()/2);
		Gdx.app.log("COmbat", " " +  char1.spriteChanging.getY() +" " +   char1.spriteChanging.getX());
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if (char1.keyUp(arg0)){return true;}
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// Zoom when scorll up 
		Gdx.app.log("MouseScrolled" , " " +arg0);
		if (arg0 == -1){ camera.zoom += 0.2f;}
		else{camera.zoom -= 0.2f;}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button){
		Vector3 worldVect  = new Vector3(screenX, screenY, 0);
		Vector3 cameraVect = camera.project(worldVect);
		Gdx.app.log("CombatScreen", "TouchDown at screen  " + screenX +","+ screenY );
		Gdx.app.log("CombatScreen", "TouchDown at cam  " + cameraVect.x +","+ cameraVect.y );
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
