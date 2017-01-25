package com.mygdx.taranfighters;

import com.badlogic.gdx.audio.Music;

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

		// Camera
		camera.viewportWidth =  18 * G.world2pixel;
		camera.viewportHeight = 12 * G.world2pixel;
		camera.position.y = char1.x;
		camera.position.x = char1.y;

		// Music 
		music1 = G.music("music/sound/welcome_to_level.mp3");
		music2 = G.music("music/sound/with.mp3");
		music3 = G.music("music/sound/remember_that.mp3");

		// Music Chain 
		if (null != level.music_num){
			music1.setOnCompletionListener(new Music.OnCompletionListener() {
				public void onCompletion(Music music) {
						level.music_num.play();
				}
			});
		}
		if (null != music2){
			level.music_num.setOnCompletionListener(new Music.OnCompletionListener() {
				public void onCompletion(Music music) {
					music2.play();
				}
			});
		}
		if (null != char1.music_name){
			music2.setOnCompletionListener(new Music.OnCompletionListener() {
				public void onCompletion(Music music) {
					char1.music_name.play();
				}
			});
		}
		if (null != music3){
			char1.music_name.setOnCompletionListener(new Music.OnCompletionListener() {
				public void onCompletion(Music music) {
					music3.play();
				}
			});
		}
		if (null != level.music_quote){
			music3.setOnCompletionListener(new Music.OnCompletionListener() {
				public void onCompletion(Music music) {
					level.music_quote.play();
				}
			});
		}
		if (null != level.music){
			level.music_quote.setOnCompletionListener(new Music.OnCompletionListener() {
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
		if (level.isLevelFinished(char1)){
			G.finish(level.finishedEnum, charEnum, levelEnum);
			return;
		}
		if (char1.isDead){
			G.finish(Level.FINISHED.GAMEOVER, charEnum, levelEnum);
			return;
		}
	}

	@Override
	public void dispose(){
		super.dispose();
		G.disposeW(music1);
		G.disposeW(music2);
		G.disposeW(music3);
	}



}
