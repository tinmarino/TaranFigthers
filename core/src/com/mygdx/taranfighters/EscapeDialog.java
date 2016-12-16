package com.mygdx.taranfighters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class EscapeDialog extends Dialog {
	public Skin skin;


	public EscapeDialog() {
		super("escape dialog", getSkinPlease());
		G.log("EscapeDialog : called");
		text("What you wanna do ?");
		button("Continue of course.", WHAT.CONTINUE);
		button("ChangeLevel, please ...", WHAT.LEVEL);
		//button("Leave you now !", WHAT.EXIT);
	}
	
	@Override
	protected void result(Object object){
		G.log("Escape Dialog is " + object);
	}

	public enum WHAT{CONTINUE, LEVEL, EXIT};


	public static Skin getSkinPlease(){
		return new Skin(Gdx.files.internal("skin/escape/escape.json"));
	}

}
