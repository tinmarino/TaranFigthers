package com.mygdx.taranfighters.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Platform;

public class LevelPlatform2 extends Level {

	public LevelPlatform2(World world) {
		super("map/platformer2.tmx", world);
		music_quote = G.music("music/quote/appear.mp3");
		music = G.music("music/battle_theme_a.mp3");




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

		// Left 1 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(04.00f, 99.75f - 86), 
					new Vector2(04.00f, 99.75f - 73), 
					8));

		// Save 1 horizontla 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(07.00f, 99.75f - 79), 
					new Vector2(23.00f, 99.75f - 79), 
					11));

		// Middle inverted V
		Vector2[] vertices2 = {
					new Vector2(04.00f, -70 + 99.75f), 
					new Vector2(11.00f, -63 + 99.75f), 
					new Vector2(18.00f, -70 + 99.75f), 
		};
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), vertices2, 10, Platform.TYPE.CIRCLE));



		// Right vertical 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(24.00f, -69 + 99.75f), 
					new Vector2(24.00f, -64 + 99.75f), 
					10));


		// All the people 1
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(25.00f, -62 + 99.75f), 
					new Vector2(13.00f, -57 + 99.75f), 
					10));
		// All the people 2
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(23.00f, -59 + 99.75f), 
					new Vector2(11.00f, -54 + 99.75f), 
					10));
		// All the people 3
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(25.00f, -56 + 99.75f), 
					new Vector2(13.00f, -51 + 99.75f), 
					10));
		// All the people 4
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(25.00f, -52 + 99.75f), 
					new Vector2(11.00f, -47 + 99.75f), 
					10));

		// Left top,
		Vector2[] vertices3 = {
					new Vector2(05.00f, -48 + 99.75f), 
					new Vector2(02.00f, -44 + 99.75f), 
					new Vector2(06.00f, -40 + 99.75f), 
					new Vector2(02.00f, -36 + 99.75f), 
		};
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), vertices3, 15, Platform.TYPE.TELEPORT));


		// Middel top,
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(07.00f, -37 + 99.75f), 
					new Vector2(22.00f, -36 + 99.75f), 
					10));
		
		// Right top vertical 
		platformList.add(new Platform(world, new Vector2(3f, 0.5f), 
					new Vector2(21.00f, -27 + 99.75f), 
					new Vector2(21.00f, -34 + 99.75f), 
					10));
		
	}

}
