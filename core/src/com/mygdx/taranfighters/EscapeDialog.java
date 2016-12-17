package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class EscapeDialog extends Dialog {
	public static Skin skin;


	public EscapeDialog() {
		super("", getSkinPlease());
		G.log("EscapeDialog : called");
		this.padTop(getPrefHeight()/5);

		text("What you wanna do ?");

		// CONTINUE 
		TextButton btn1 = new TextButton("Continue of course.", skin.get("iul", TextButtonStyle.class));
		this.setObject(btn1, WHAT.CONTINUE);
		this.getButtonTable().add(btn1).height(getPrefHeight()/5);
		this.getButtonTable().row();

		// LEVEL SWITCH 
		TextButton btn2 = new TextButton("ChangeLevel, please ...", skin.get("jak", TextButtonStyle.class));
		this.setObject(btn2, WHAT.LEVEL); 
		this.getButtonTable().add(btn2).height(getPrefHeight()/5);
		this.getButtonTable().row();

		// EXIT 
		TextButton btn3 = new TextButton("Leave you now !", skin.get("roz", TextButtonStyle.class));
		this.setObject(btn3, WHAT.EXIT);
		this.getButtonTable().add(btn3).height(getPrefHeight()/5);
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
        return Gdx.graphics.getWidth()/2;
    }

    @Override
    public float getPrefHeight() {
        return Gdx.graphics.getWidth()/2;
    }

	public enum WHAT{CONTINUE, LEVEL, EXIT};


	public static Skin getSkinPlease(){
		skin = new Skin(Gdx.files.internal("skin/escape/escape.json"));
		skin.getFont("escape-font").getData().setScale(0.5f,0.5f);
		return skin;
	}

}
