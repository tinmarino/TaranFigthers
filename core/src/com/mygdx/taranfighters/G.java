package com.mygdx.taranfighters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Disposable;

public class G{
	// 0/ must be initiated a null pointer exception
	public static Game game;
	public static MidiPlayer midiPlayer;


   	public static float timestep = 1/60f;
	public static int velocity_iterations = 8;
	public static int position_iterations = 3;
	public static float world2pixel = 128;
	public static boolean debug = true;


	public enum CHAR{JAK, ROZ, IUL, FIX}

	public enum LEVEL{L1, L2, l3, L4, l5}


	public static BitmapFont overFont;

	public static void init(){
		// OVER FONT 
		overFont = new BitmapFont(Gdx.files.internal("font/zreak-nfi.fnt"));
		overFont.getData().setScale(1,1);
		overFont.setColor(new Color(0x8A0707ff));

	}

	public static void dispose(){
		overFont.dispose();
	}

	public static boolean isBodyContact(Body body, Contact contact){
		if (contact.getFixtureA().getBody() == body
		  ||contact.getFixtureB().getBody() == body){
			return true;
		}

		return false;
	}


	public static void disposeW(Disposable disposable){
		if ( null != disposable ){disposable.dispose();}
	}

	public static void log(String string){
		Gdx.app.log("Gdx TaranFigthers: ", string);
	}


}

