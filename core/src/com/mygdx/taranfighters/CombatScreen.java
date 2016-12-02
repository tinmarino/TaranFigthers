package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CombatScreen extends TaranScreen {
	Texture img, img1, img2, img3;
	float iTimeCounter=0;
    OrthographicCamera  rightCamera, leftCamera, topCamera, bottomCamera;
	Sprite testSprite; 
	Vector2 offset1 = new Vector2(-2, 0);
	Vector2 offset2 = new Vector2(2, 0);
	Vector2 arrowOffset = new Vector2(0, 0);
	Sprite arrowSprite1 = new Sprite(new Texture("img/arrow_blue.png"));
	Sprite arrowSprite2 = new Sprite(new Texture("img/arrow_blue.png"));

	
	@Override
	public void show() {
		super.show();

		level = new Level("map/castleArena100.tmx", world);

		rightCamera = new OrthographicCamera();
		leftCamera = new OrthographicCamera();
		topCamera = new OrthographicCamera();
		bottomCamera = new OrthographicCamera();



		char1 = new Iul(world);
		char2 = new Iul(world);
		char2.setPosition(12, 12);

		// ARROW 
		arrowSprite1.setSize(0.25f * G.world2pixel, 0.5f * G.world2pixel);
		arrowSprite1.setOrigin(arrowSprite1.getWidth()/2, arrowSprite1.getHeight()/2);
	}

	@Override
	public void render (float delta) {
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		int screenX = 0, screenY = 0;
		if (screenWidth < (int) (1.5f * screenHeight)){
			screenHeight = (int) (screenWidth / 1.5f);
			screenY = (Gdx.graphics.getHeight() - screenHeight) / 2;
			screenX = 0;
		}
		else {
			screenWidth = (int)(1.5f * screenHeight);
			screenX = (Gdx.graphics.getWidth() - screenWidth) / 2;
			screenY = 0;
		}

		int lineWidth = screenWidth/200;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	world.step(G.timestep, G.velocity_iterations, G.position_iterations);


		// CAMERA 
		camera.position.x = (char1.x + char2.x) / 2 * G.world2pixel;
		camera.position.y = (char1.y + char2.y) / 2 * G.world2pixel;
		offset1 = new Vector2((char1.x - char2.x)/2, (char1.y - char2.y)/2);
		offset2 = new Vector2((char2.x - char1.x)/2, (char2.y - char1.y)/2);
		offset1.x = Math.min(offset1.x, G.world2pixel*8/2/1.4f /G.world2pixel );
		offset1.x = Math.max(offset1.x, -G.world2pixel*8/2/1.4f/G.world2pixel );
		offset1.y = Math.min(offset1.y, G.world2pixel*8/2/1.4f /G.world2pixel );
		offset1.y = Math.max(offset1.y, -G.world2pixel*8/2/1.4f/G.world2pixel );
		offset2.x = Math.min(offset2.x, G.world2pixel*8/2/1.4f /G.world2pixel );
		offset2.x = Math.max(offset2.x, -G.world2pixel*8/2/1.4f/G.world2pixel );
		offset2.y = Math.min(offset2.y, G.world2pixel*8/2/1.4f /G.world2pixel );
		offset2.y = Math.max(offset2.y, -G.world2pixel*8/2/1.4f/G.world2pixel );

		float width = Math.abs(char1.x - char2.x) * 1.4f;
		float height = Math.abs(char1.y - char2.y) * 1.4f;


		// Single Screen 
		if (width < 8 && height < 8){
			if (width / height > 1.5){height = width / 1.5f;}
			else{width = height * 1.5f;}
  			Gdx.gl.glViewport(screenX, screenY, screenWidth, screenHeight);
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


			// DEBUG
			level.debugRenderer.render(world, camera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
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
				Gdx.gl.glViewport(screenX, screenY, (screenWidth-lineWidth)/2, screenHeight);
				leftCamera.position.x = (leftChar.x - leftOffset.x - 2) * G.world2pixel;
				leftCamera.position.y = (leftChar.y - leftOffset.y) * G.world2pixel;
				leftCamera.viewportWidth = 4 * G.world2pixel;
				leftCamera.viewportHeight = 8 * G.world2pixel;
				leftCamera.update();

				// ARROW 
				arrowOffset.x = G.world2pixel * rightChar.x - leftCamera.position.x;
				arrowOffset.y = G.world2pixel * rightChar.y - leftCamera.position.y;
				arrowOffset.limit(8 * G.world2pixel/4.4f);
				Gdx.app.log("Comabt", "world2 pixel" +  G.world2pixel + ",,," + leftCamera.viewportWidth + "," + camera.position + "," + leftChar.x + " y " + leftChar.y);
				arrowOffset.x += leftCamera.position.x;
				arrowOffset.y += leftCamera.position.y;
				Vector2 charDistance = new Vector2(rightChar.x - leftChar.x, rightChar.y- leftChar.y);
				arrowSprite1.setRotation(charDistance.angle() - 90);
				arrowSprite1.setPosition(arrowOffset.x - arrowSprite1.getWidth()/2, arrowOffset.y + arrowSprite1.getHeight()/2);

				level.tiledMapRenderer.setView(leftCamera);
				level.tiledMapRenderer.render();
				SpriteBatch leftBatch = (SpriteBatch) level.tiledMapRenderer.getBatch();
				leftBatch.begin();
					level.draw(leftBatch, delta);
					leftChar.draw(leftBatch, delta);
					arrowSprite1.draw(leftBatch);
				leftBatch.end();

				level.debugRenderer.render(world, leftCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
				

				// Right
				Gdx.gl.glViewport(screenX + (screenWidth+lineWidth)/2, screenY, (screenWidth-lineWidth)/2, screenHeight);
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

				level.debugRenderer.render(world, rightCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );

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
				Gdx.gl.glViewport(screenX, screenY + (screenHeight+lineWidth)/2 , screenWidth, (screenHeight-lineWidth)/2);
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

				level.debugRenderer.render(world, topCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );


				// Bottom
				Gdx.gl.glViewport(screenX, screenY, screenWidth, (screenHeight - lineWidth)/2);
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

				level.debugRenderer.render(world, bottomCamera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );

			}
		}
	}



}
