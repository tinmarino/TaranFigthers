package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Character;

public class LevelFreefall extends Level {

	public LevelFreefall(World world) {
		super("map/platformer_freefall.tmx", world);
		initialPosition = new Vector2(25, 995); 
		music_quote = G.music("music/quote/it_is_always.mp3");
		music = G.music("music/el_aparecido.mp3");
	}

	@Override
	public boolean isLevelFinished(Character char1){
		// Victory 
		boolean victory = char1.x > 25 && char1.y < 3;
		if (victory){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		return false;
	}
}
