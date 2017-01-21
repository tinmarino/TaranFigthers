package com.mygdx.taranfighters;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;

public class G{
	// 0/ must be initiated a null pointer exception
	public static Game game;
	public static MidiPlayer midiPlayer;


   	public static float timestep = 1/60f;
	public static int velocity_iterations = 8;
	public static int position_iterations = 3;
	public static float world2pixel = 128;
	public static boolean debug = false;


	public enum CHAR{JAK, ROZ, IUL, FIX, TIN}

	// ChooseScreen + Level + create new file ThisLevel
	public enum LEVEL{L1, L2, L3, L4, L5, L6}


	public static BitmapFont overFont;
	public static BitmapFont debugFont;
	public static BitmapFont victoryFont;

	public static PreferenceSaved preferenceSaved;


	public static void init(){
		// PREF 
		preferenceSaved = new PreferenceSaved();
		
		// OVER FONT 
		overFont = new BitmapFont(Gdx.files.internal("font/zreak-nfi.fnt"));
		overFont.getData().setScale(1,1);
		overFont.setColor(new Color(0x8A0707ff));

		// VICTORY FONT 
		victoryFont = new BitmapFont(Gdx.files.internal("font/zreak-nfi.fnt"));
		victoryFont.getData().setScale(1,1);
		victoryFont.setColor(new Color(0x078A07FF));

		// DEBUG FONT
		debugFont = new BitmapFont();
		debugFont.setColor(Color.RED);
	}

	public static void dispose(){
		overFont.dispose();
		debugFont.dispose();
		victoryFont.dispose();
	}

	public static boolean isBodyContact(Body body, Contact contact){
		if (contact.getFixtureA().getBody() == body
		  ||contact.getFixtureB().getBody() == body){
			return true;
		}

		return false;
	}





	public static Music music(String string){
		return Gdx.audio.newMusic(Gdx.files.internal(string)); 
	}

	public static void disposeW(Disposable disposable){
		if ( null != disposable ){disposable.dispose();}
	}

	public static void log(String string){
		Gdx.app.log("Gdx TaranFigthers: ", string);
	}




	public static void writePref(){
		Json json = new Json();
		String sTosave = json.toJson(preferenceSaved);
		G.log("Global.writePref : " + json.prettyPrint(preferenceSaved));
     	Preferences preferences = Gdx.app.getPreferences("v1"); // v1 for version 1 
		preferences.putString("jsonKey1", sTosave);
		preferences.flush();
	}

	public static void readPref(){
		Json json = new Json(); 
     	Preferences preferences = Gdx.app.getPreferences("v1"); // v1 for version 1 
		if (preferences != null && preferences.get().containsKey("jsonKey1")) {
			String sToLoad = preferences.getString("jsonKey1");
			preferenceSaved = json.fromJson(PreferenceSaved.class, sToLoad );
			G.log("TBF Global.readPref : " + json.prettyPrint(preferenceSaved));
		}
		else{
			G.log("TBF Global.readPref : NULL !!! ");
			preferenceSaved = new PreferenceSaved();
		}
	}


	// locked, open, finished, prefered
	public static void finish(Level.FINISHED eFinish, CHAR eChar, LEVEL eLevel){
		if (eFinish == Level.FINISHED.GAMEOVER){
			G.game.setScreen(new JakOverScreen());
		}
		if (eFinish == Level.FINISHED.VICTORY){
			G.preferenceSaved.charLevelList.get(eChar.ordinal()).set(eLevel.ordinal(), "finished");

			// Open all current level 
			for (ArrayList<String> equiChar : G.preferenceSaved.charLevelList){
				if (equiChar.get(eLevel.ordinal()) == "locked"){
					equiChar.set(eLevel.ordinal(), "open");
				}
			}

			// If not opened yet, 
			if (eLevel.ordinal()+2 < G.preferenceSaved.charLevelList.get(0).size()){
				boolean isOtherLevelOpened = false;
				for (ArrayList<String> equiChar : G.preferenceSaved.charLevelList){
					if (equiChar.get(eLevel.ordinal()+1) != "locked"){
						isOtherLevelOpened=true;
					}
				}
				// open a random one 
				if(!isOtherLevelOpened){
					G.log("is taht open " + isOtherLevelOpened);
					Random random = new Random();
					int iChar = random.nextInt(CHAR.values().length);
					G.preferenceSaved.charLevelList.get(iChar).set(eLevel.ordinal()+1, "open");
				}
			}

			// Save pref 
			G.writePref();
			// Change screen 
			G.game.setScreen(new VictoryScreen());
		}
	}



}


