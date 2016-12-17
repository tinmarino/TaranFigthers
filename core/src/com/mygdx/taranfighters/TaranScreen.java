package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TaranScreen implements Screen, InputProcessor {
	public World world;
	public OrthographicCamera camera;
	public Character char1, char2;
	public Level level;
	public int levelInt = 0;
	Stage stage;
	EscapeDialog escapeDialog;



	public TaranScreen(){
	}

	@Override
	public void show() {
		G.log("TaranScreen class showing");
		world = new World(new Vector2(0f, -10f), true);

		camera = new OrthographicCamera();
		camera.position.x = 0;
		camera.position.y = 0;
		camera.viewportWidth = 12 * G.world2pixel;
		camera.viewportHeight = 8 * G.world2pixel;

        Gdx.input.setInputProcessor(this);
		setContactListener();
	}



	@Override
	public void render(float delta){
		// Escape ??
		if (null != stage){
			if (escapeDialog.isDestroyed){
				stage.dispose();
				stage = null;
				Gdx.input.setInputProcessor(this);
				return;
			}
			stage.act(delta);
			stage.draw();
			return;
		}

		// Clear screen 
      	Gdx.gl.glClearColor(0, 0, 0.2f, 1);
      	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// World step 
    	world.step(G.timestep, G.velocity_iterations, G.position_iterations);


		// Center Camera 
		camera.position.x = char1.x * G.world2pixel;
		camera.position.y = char1.y * G.world2pixel;
		camera.update();

		// Render Level and get its batch
		level.tiledMapRenderer.setView(camera);
		level.tiledMapRenderer.render();
		SpriteBatch batch = (SpriteBatch) level.tiledMapRenderer.getBatch();

		// Render char 
		batch.begin();
			level.draw(batch, delta);
			char1.draw(batch, delta);
		batch.end();

		// Debug render 
		if (G.debug){
			level.debugRenderer.render(world, camera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
		}
	}



	public void setContactListener(){
        world.setContactListener(new ContactListener() {
             @Override
             public void beginContact(Contact contact) {
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
	public void dispose() { }

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resize(int arg0, int arg1) { }

	@Override
	public void resume() { }




	@Override
	public boolean keyDown(int keycode) {
		if (char1.keyDown(keycode)){return true;}

        if(keycode == Input.Keys.NUM_1)
            level.tiledMap.getLayers().get(0).setVisible(!level.tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            level.tiledMap.getLayers().get(1).setVisible(!level.tiledMap.getLayers().get(1).isVisible());
        if(keycode == Input.Keys.A){
			G.log("CombatScreen Changing to JacOverScreen");
			G.game.setScreen(new JakOverScreen());
		}

		if (keycode == Input.Keys.ESCAPE){
			G.log("TaranScreen : escape dialog called");

			FitViewport fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			stage = new Stage(fitViewport);
			Gdx.input.setInputProcessor(stage);
			escapeDialog = new EscapeDialog();
			escapeDialog.show(stage);
		}

		G.log("TaranScreen :" + " " +  char1.spriteChanging.getY() +" " +   char1.spriteChanging.getX());
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) { return false; }

	@Override
	public boolean keyUp(int arg0) {
		if (char1.keyUp(arg0)){return true;}
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) { return false; }

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
		Vector3 screenVect  = new Vector3(screenX, screenY, 0);
		Vector3 cameraVect = camera.project(screenVect);
		G.log("TaranScreen TouchDown at screen  " + screenX +","+ screenY );
		G.log("TaranScreen TouchDown at cam     " + cameraVect.x +","+ cameraVect.y );
		G.log("TaranScreen Player Position " + char1.x +","+ char1.y);
		if (char1.touchDown(screenX, screenY, pointer, button)){return true;}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

}
