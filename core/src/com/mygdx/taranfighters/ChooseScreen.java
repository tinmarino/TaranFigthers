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
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ChooseScreen implements Screen {

	ArrayList<Disposable> disposableList = new ArrayList<Disposable>();
	Stage stage;
	Table table;

	int debugCount = 0;	
	String[] arr = {"jak", "roz", "iul", "fix"}; 
	FitViewport fitViewport;


	@Override
	public void show() {
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(fitViewport);
		Gdx.input.setInputProcessor(stage);

		table = new Table();


		for (final String charString : arr){
		
			Drawable drawable = PixmapFactory.drawableFromFile(charString + "/" + charString + "_id.png", disposableList);
			ImageButton imageButton = new ImageButton(drawable);
			imageButton.setSize(140, 180);
			imageButton.addListener(
				new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event,x,y); 
						click(charString); 
					} 
				}
			);
			table.add(imageButton).size(140,180).pad(10);

			for (int i = 0; i <10 ; i++){
				Drawable drawable2 = PixmapFactory.drawableFromFile("img/hud/hud_" +  i + ".png", disposableList);
				ImageButton imageButton2 = new ImageButton(drawable2);
				imageButton2.setSize(140, 180);
				imageButton2.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event,x,y); 
							click(charString); 
						} 
					}
				);
				table.add(imageButton2).size(140, 180).pad(10);
			}

			table.row();
		}


		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setFillParent(true);
		stage.addActor(scrollPane);


	}


	void click(String charString){
		G.log("ChooseScreen clicking " + charString + " "  + debugCount); 
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
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void resume() {
	}


}
