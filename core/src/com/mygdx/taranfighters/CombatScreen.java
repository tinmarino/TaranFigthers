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
	SpriteBatch batch;
	Texture img, img1, img2, img3;
	float iTimeCounter=0;
    OrthographicCamera camera;
	Iul char1, char2;
	Sprite testSprite; 
	World world;
	Box2DDebugRenderer  debugRenderer;
	Level level;
	
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


        Gdx.input.setInputProcessor(this);

		char1 = new Iul(world);

		setContactListener();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	world.step(G.timestep, G.velocity_iterations, G.position_iterations);


		camera.position.x = char1.x * G.world2pixel;
		camera.position.y = char1.y * G.world2pixel;
        camera.update();

        level.tiledMapRenderer.setView(camera);
        level.tiledMapRenderer.render();
		
		SpriteBatch batch = (SpriteBatch) level.tiledMapRenderer.getBatch();

		batch.begin();
			level.draw(batch, delta);
			char1.draw(batch, delta);
		batch.end();
		
		// DEBUG
		debugRenderer.render(world, camera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
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
