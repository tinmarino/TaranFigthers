package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Zombie;

public class LevelPlage extends Level {
	float counterTime = 0;
	double random;

	public LevelPlage(World world) {
		super("map/plage.tmx", world);
		initialPosition = new Vector2(12, 23);
		random = Math.random();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);

		counterTime += delta;
		if (counterTime > 4 + 2*(random-0.5f)){
			random = Math.random();
			counterTime = 0;
			Zombie zombie = makeZombie(17, 23);
			zombie.fromTo(13, 22);
		}
	}


}
