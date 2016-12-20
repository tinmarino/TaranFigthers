package com.mygdx.taranfighters;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

public class PlatformScreen extends TaranScreen{
	G.CHAR charEnum;
	G.LEVEL levelEnum;

	// disposable
	public Music music1, music2, music3;



	public PlatformScreen(G.CHAR charEnum, G.LEVEL levelEnum) {
		this.charEnum = charEnum; 
		this.levelEnum = levelEnum;
	}

	@Override
	public void show(){
		super.show();

		G.log("PlatformScreen will create level " + levelEnum);
		level = Level.createLevel(levelEnum, world);

		G.log("PlatformScreen will create Character " + charEnum);
		char1 = Character.createCharacter(charEnum, world);

		// Callbacks
		char1.setPosition(level.initialPosition.x, level.initialPosition.y);

		// Music 
		music1 = G.music("music/sound/welcome_to_level.mp3");
		music2 = G.music("music/sound/with.mp3");
		music3 = G.music("music/sound/remember_that.mp3");

		// Music Chain 
		if (null != level.music_num){
			music1.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
						level.music_num.play();
				}
			});
		}
		if (null != music2){
			level.music_num.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
					music2.play();
				}
			});
		}
		if (null != char1.music_name){
			music2.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
					char1.music_name.play();
				}
			});
		}
		if (null != music3){
			char1.music_name.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
					music3.play();
				}
			});
		}
		if (null != level.music_quote){
			music3.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
					level.music_quote.play();
				}
			});
		}
		if (null != level.music){
			level.music_quote.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(Music music) {
					level.music.play();
				}
			});
		}



		// Play 
		music1.play();

		
	}

	@Override 
	public void render(float delta){
		super.render(delta);

		// isFinished ?
		if (level.isLevelFinished(char1)){
			G.finish(level.finishedEnum, charEnum, levelEnum);
		}
	}


	@Override
	public void dispose(){
		super.dispose();
		music1.dispose();
		music2.dispose();
		music3.dispose();
	}



}
