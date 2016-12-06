package com.mygdx.taranfighters.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Platform;

public class LevelPlatform1 extends Level {

	public LevelPlatform1(World world) {
		super("map/platformer1.tmx", world);

		platformList = new ArrayList<Platform>();
		// For 166, 6 (on 300 20) not upside down so + (0.5+ 7.75) 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), new Vector2(166.5f, 13.75f), new Vector2(175.5f, 13.75f), 10));
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), new Vector2(181.5f, 13.75f), new Vector2(181.5f, 4.75f), 10));
	}

}
