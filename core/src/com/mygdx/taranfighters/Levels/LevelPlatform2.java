package com.mygdx.taranfighters.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Platform;

public class LevelPlatform2 extends Level {

	public LevelPlatform2(World world) {
		super("map/platformer2.tmx", world);




		platformList = new ArrayList<Platform>();
		// [X, (100 - Y - 0.25)] 100 is the tile size, 0.25 is half the sprite size

		// First vertical 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(8.00f, 1.75f), 
					new Vector2(8.00f, 9.75f), 
					10));

		// Bottom horizontal
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(10.00f, 0.75f), 
					new Vector2(20.00f, 0.75f), 
					7));


		// Begining diagonal
		Vector2[] vertices1 = {
					new Vector2(20.00f, 9.75f), 
					new Vector2(20.00f, 13.75f), 
					new Vector2(7.00f, 17.75f), 
		};
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), vertices1, 7));
	}

}
