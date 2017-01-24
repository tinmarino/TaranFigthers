package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Character;

public class LevelSalon extends Level {
	Music music1, music2;

	public LevelSalon(World world) {
		super("map/salon.tmx" , world);
		initialPosition.y = 20;

		music1 = Gdx.audio.newMusic(Gdx.files.internal("music/quote/it_is_always.mp3")); 
		music1.play();


		makeZombie(7, 30).fromTo(5f, 9f); 
		makeZombie(33, 20).fromTo(26, 42); 
		makeZombie(27, 14).fromTo(25, 28); 
		makeZombie(78, 30).fromTo(76, 84); 
		makeZombie(65, 10).fromTo(65, 83); 
		makeZombie(88, 07).fromTo(88, 117); 
		makeZombie(110, 25).fromTo(99, 110); 
	}

	@Override
	public boolean isLevelFinished(Character char1){
		// Victory 
		if (charList.size() == 0){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		return false;
	}



	@Override
	public void dispose(){
		super.dispose();
		music1.dispose();
	}

}
