package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
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
	Matrix4 HUDMatrix;
	public float age = 0;
	private SpriteBatch batch;

	@Override
	public void dispose(){ 
		G.disposeW(escapeDialog);
		G.disposeW(stage);
		G.disposeW(level);
		G.disposeW(char1);
		G.disposeW(char2);
		G.disposeW(world);
	}


	public TaranScreen(){
	}

	@Override
	public void show() {
		G.log("TaranScreen class showing");
		world = new World(new Vector2(0f, -20f), true);

		camera = new OrthographicCamera();
		camera.zoom = 1;
		batch = new SpriteBatch();

		HUDMatrix = new Matrix4();
 	  	HUDMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(this);
		setContactListener();
	}


	float Round(float floatA,float floatB){
		return Math.round(floatA / floatB) * floatB;
	}

	@Override
	public void render(float delta){
		age += delta;
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
      	Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
      	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// World step 
    	world.step(G.timestep, G.velocity_iterations, G.position_iterations);


		// Center Camera 
		camera.position.x = Round(char1.x * G.world2pixel, 1);
		camera.position.y = Round(char1.y * G.world2pixel, 1);
		camera.update();

		// Render Level and get its batch
		level.tiledMapRenderer.setView(camera);
		level.tiledMapRenderer.render();


		// Render char 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			level.draw(batch, delta);
			char1.draw(batch, delta);
		batch.end();

		// Debug render 
		if (G.debug){
			level.debugRenderer.render(world, camera.combined.scale(G.world2pixel, G.world2pixel, G.world2pixel) );
		}

		// HUD debug 
		if (G.debug){
			batch.setProjectionMatrix(HUDMatrix);
			batch.begin();
				G.debugFont.draw(batch,
							"FPS :" + 1/delta + "\n" +
							"TIME :" + age + "\n" +
							"ENEMY :" + level.charList.size(),
							0.5f * G.world2pixel,
							2f * G.world2pixel);
			batch.end();
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
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resize(int arg0, int arg1) {
 	  	HUDMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void resume() { }




	@Override
	public boolean keyDown(int keycode) {
		G.log("TaranScreen : keydown" + " " +  char1.spriteChanging.getY() +" " +   char1.spriteChanging.getX());
		if (char1.keyDown(keycode)){return true;}

		switch(keycode){
			case Input.Keys.NUMPAD_8:
				level.tiledMap.getLayers().get(0).setVisible(!level.tiledMap.getLayers().get(0).isVisible());
				return true;

			case Input.Keys.NUMPAD_7:
				level.tiledMap.getLayers().get(1).setVisible(!level.tiledMap.getLayers().get(1).isVisible());
				return true;

			case Input.Keys.A:
				G.log("CombatScreen Changing to JacOverScreen");
				G.game.setScreen(new JakOverScreen());
				return true;

			case Input.Keys.NUMPAD_9:
				G.debug = !G.debug;
				return true;

			case Input.Keys.NUMPAD_1:
				this.char1.maxSpeed = new Vector2(40, 20);
				return true;

			case Input.Keys.NUMPAD_2:
				this.char1.maxSpeed = this.char1.defaultMaxSpeed;
				return true;

			case Input.Keys.ESCAPE:
			case Input.Keys.BACK:
				G.log("TaranScreen : escape dialog called");

				FitViewport fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				stage = new Stage(fitViewport);
				Gdx.input.setInputProcessor(stage);
				escapeDialog = new EscapeDialog();
				escapeDialog.show(stage);
				return true;
			default:
				return false;
		}
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
		if (char1.touchDown(screenX, screenY, pointer, button)){return true;}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (char1.touchUp(screenX, screenY, pointer, button)){return true;}
		return false;
	}

}
