package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.Level;

public class LevelPlage extends Level {
	float counterTime;

	public LevelPlage(World world) {
		super("map/plage.tmx", world);
		initialPosition = new Vector2(2, 13);



		makeZombie(6, 13);
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);

		counterTime += delta;
		if (counterTime > 5){
			counterTime = 0;
			makeZombie(7, 13);
		}
	}


}
