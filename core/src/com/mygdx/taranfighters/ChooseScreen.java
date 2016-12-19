package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
	Music music;
	EscapeDialog escapeDialog;

	G.CHAR[] arr = {G.CHAR.JAK, G.CHAR.ROZ, G.CHAR.IUL, G.CHAR.TIN, G.CHAR.FIX}; 
	FitViewport fitViewport;


	@Override
	public void dispose(){
		G.disposeW(escapeDialog);
		G.disposeW(stage);
		G.disposeW(music);
		for (Disposable i : disposableList){
			G.disposeW(i);
		}
	}


	@Override
	public void show() {
		// Music
		music = G.music("music/sound/taran_fighters.mp3"); 
		music.play();

		// Stage
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(fitViewport);
		Gdx.input.setInputProcessor(stage);

		// Manage escape key 
		stage.addListener(new InputListener(){
			@Override
			public boolean keyDown (InputEvent event, int keycode) {
				super.keyDown(event, keycode);
				G.log("Choose screen press " + keycode);
				if (keycode != Input.Keys.ESCAPE){return false;}
				escapeDialog = new EscapeDialog();
				escapeDialog.show(stage);
				G.log("Choose screen press esc ");
				return true;
			}
		});

		table = new Table();


		for (final G.CHAR charEnum : arr){
			String charString;
			switch (charEnum){
				case JAK:
					charString = "jak/jak_id.png";
					break;
				case ROZ:
					charString = "roz/roz_id.png";
					break;
				case IUL:
					charString = "iul/iul_id.png";
					break;
				case TIN:
					charString = "tin/tin_id.png";
					break;
				case FIX:
					charString = "fix/fix_id.png";
					break;
				default:
					charString = "jak/jak_id.png";
			}
			Drawable drawable = PixmapFactory.drawableFromFile(charString, disposableList);
			ImageButton imageButton = new ImageButton(drawable);
			imageButton.setSize(140, 180);
			imageButton.addListener(
				new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event,x,y); 
						click(charEnum , G.LEVEL.L1); 
					} 
				}
			);
			table.add(imageButton).size(140,180).pad(10);

			for (G.LEVEL i : G.LEVEL.values()){
				final G.LEVEL levelEnum = i;
				String levelString;
				switch (levelEnum){
					case L1:
						levelString = "map/screenshot/platformer1.png";
						break;
					case L2:
						levelString = "map/screenshot/platformer2.png";
						break;
					case L3:
						levelString = "map/screenshot/salon1.png";
						break;
					case L4:
						levelString = "map/screenshot/plage1.png";
						break;
					case L5:
						levelString = "map/screenshot/freefall2.png";
						break;
					case L6:
						levelString = "map/screenshot/sinai1.png";
						break;
					case L7:
						levelString = "img/door/door_closed.png";
						break;
					case L8:
						levelString = "img/door/door_closed.png";
						break;
					default:
						levelString = "img/door/door_closed.png";
						break;
				}
				Drawable drawable2 = PixmapFactory.drawableFromFile(levelString, disposableList);
				ImageButton imageButton2 = new ImageButton(drawable2);
				imageButton2.setSize(140, 180);
				imageButton2.addListener(
					new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event,x,y); 
							click(charEnum, levelEnum); 
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


	// TODO : -1 must be the infoScreen "?"
	void click(G.CHAR  charEnum, G.LEVEL levelEnum){
		G.log("ChooseScreen clicking chose char : " + charEnum + " with level : "  + levelEnum ); 
		G.game.setScreen(new PlatformScreen(charEnum, levelEnum));
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}



	@Override
	public void hide() { }

	@Override
	public void pause() { }


	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void resume() { }


}
