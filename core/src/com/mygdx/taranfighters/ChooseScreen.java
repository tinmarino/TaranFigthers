package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;


public class ChooseScreen implements Screen {

	ArrayList<Disposable> disposableList = new ArrayList<Disposable>();
	Stage stage;
	Table table;

	int debugCount = 0;	



	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);


		Drawable iulDrawable = PixmapFactory.drawableFromFile("iul/iul_id.png", disposableList);
		ImageButton iulImage = new ImageButton(iulDrawable);
		iulImage.addListener(
			new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event,x,y); 
					routineIul(); 
				} 
			}
		);


		table.add(iulImage);
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setFillParent(true);
		stage.addActor(scrollPane);


	}


	void routineIul(){
		G.log("ChooseScreen clicking Iul " + debugCount); 
		debugCount +=1;
		if (debugCount > 5){
			G.game.setScreen(new PlatformScreen());
		}
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
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
	}

	@Override
	public void resume() {
	}


}
