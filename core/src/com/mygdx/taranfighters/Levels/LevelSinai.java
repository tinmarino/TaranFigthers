package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;

public class LevelSinai extends Level {

	public LevelSinai(World world) {
		super("map/sinai.tmx", world);
		initialPosition = new Vector2(18, 20);
		music_quote = G.music("music/quote/he_who_is_prudent.mp3");
		//music = G.music("music/el_aparecido.mp3");
	}
}
