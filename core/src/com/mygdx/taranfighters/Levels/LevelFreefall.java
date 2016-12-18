package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;

public class LevelFreefall extends Level {

	public LevelFreefall(World world) {
		super("map/platformer_freefall.tmx", world);
		music_quote = G.music("music/quote/it_is_always.mp3");
	}
}
