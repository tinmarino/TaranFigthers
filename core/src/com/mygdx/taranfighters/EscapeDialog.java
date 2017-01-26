package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Disposable;


public class EscapeDialog extends Dialog  implements Disposable{
	public static Skin skin;
	public boolean isDestroyed = false;
	public Music music;


	public EscapeDialog() {
		super("", getSkinPlease());


		this.addAction(Actions.alpha(0.2f));

		// SOUND 
		music = Gdx.audio.newMusic(Gdx.files.internal("music/hollande10.mp3"));
		music.play();
		music.setPosition(5);

		G.log("EscapeDialog : called");
		this.padTop(getPrefHeight()/8);

		text("What you wanna do ?");

		// CONTINUE 
		TextButton btn1 = new TextButton("Continue of course.", skin.get("iul", TextButtonStyle.class));
		btn1.getLabelCell().padTop(30);
		this.setObject(btn1, WHAT.CONTINUE);
		this.getButtonTable().add(btn1).height(getPrefHeight()/4).width(getPrefWidth() * 0.8f);
		this.getButtonTable().row();

		// LEVEL SWITCH 
		TextButton btn2 = new TextButton("ChangeLevel, please ...", skin.get("jak", TextButtonStyle.class));
		btn2.getLabelCell().padTop(-30);
		this.setObject(btn2, WHAT.LEVEL); 
		this.getButtonTable().add(btn2).height(getPrefHeight()/4).width(getPrefWidth() * 0.8f);
		this.getButtonTable().row();

		// EXIT 
		TextButton btn3 = new TextButton("Leave you now !", skin.get("roz", TextButtonStyle.class));
		btn3.getLabelCell().padTop(20);
		this.setObject(btn3, WHAT.EXIT);
		this.getButtonTable().add(btn3).height(getPrefHeight()/4).width(getPrefWidth() * 0.8f);
		this.getButtonTable().row();
	}
	
	@Override
	protected void result(Object object){
		G.log("Escape Dialog is " + object);

		// EXIT 
		if (object == WHAT.EXIT){
			G.log("Escape Dialog : Bye");
			Gdx.app.exit();
			return;
		}

		// CONTINUE
		if (object == WHAT.CONTINUE){
			G.log("Escape Dialog : Continue");
			this.isDestroyed = true;
			return;
		}

		// LEVEL 
		if (object == WHAT.LEVEL){
			G.log("Escape Dialog : Change Level");
			G.game.setScreen(new ChooseScreen());
			return;
		}
	}

    @Override
    public float getPrefWidth() {
        return Gdx.graphics.getHeight() * 0.8f;
    }

    @Override
    public float getPrefHeight() {
        return Gdx.graphics.getHeight() * 0.8f;
    }

	public enum WHAT{CONTINUE, LEVEL, EXIT};


	public static Skin getSkinPlease(){
		skin = new Skin(Gdx.files.internal("skin/escape/escape.json"));
		skin.getFont("escape-font").getData().setScale(0.3f,0.4f);
		return skin;
	}

	@Override
	public void dispose() {
		music.stop();
		music.dispose();
		skin.getAtlas().dispose();
		skin.dispose();
	}

}
