package com.mygdx.taranfighters.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Platform;
import com.mygdx.taranfighters.Zombie;
import com.mygdx.taranfighters.Character;

public class LevelPlatform1 extends Level {


	@Override
	public boolean isLevelFinished(Character char1){
		// Victory 
		boolean victory = char1.x > 296;
		if (victory){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		// Defeat 
		boolean defeat = false;
		defeat |= char1.y < 0;
		if (defeat){
			this.finishedEnum = FINISHED.GAMEOVER;
			return true;
		}
		return false;
	}


	public LevelPlatform1(World world) {
		super("map/platformer1.tmx", world);

		// Music 
		music_quote = G.music("music/quote/the_journey.mp3");
		music = G.music("music/blackmore_tides.mp3");

		// Platfroms
		platformList = new ArrayList<Platform>();
		// For 166, 6 (on 300 20) not upside down so + (0.5+ 7.75) 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), new Vector2(166.5f, 13.75f), new Vector2(175.5f, 13.75f), 10));
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), new Vector2(181.5f, 13.75f), new Vector2(181.5f, 4.75f), 10));

		// Zombie from [49,6] to [61,6]
		Zombie zombie = makeZombie(49 + 0.5f, 6 + 7.75f);
		zombie.fromTo(49 + 0.5f, 61 + 0.5f); 

		Zombie zombie2 = makeZombie(145 + 0.5f, 15 + 7.75f);
		zombie2.fromTo(140 + 0.5f, 154 + 0.5f);

		Zombie zombie3 = makeZombie(272 + 0.5f, 7 + 7.75f);
		zombie3.fromTo(267 + 0.5f, 276 + 0.5f);
	}

}
