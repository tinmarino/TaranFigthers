package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.Zombie;
import com.mygdx.taranfighters.Character;

public class LevelPlage extends Level {
	float counterTime = 0;
	int zombieToKill = 3;
	double random;

	public LevelPlage(World world) {
		super("map/plage.tmx", world);
		initialPosition = new Vector2(12, 23);
		music_quote = G.music("music/quote/the_expert.mp3");
		music = G.music("music/fight_them.mp3");
		random = 4 + 2 * (Math.random()-0.5);
	}

	@Override
	public boolean isLevelFinished(Character char1){
		if (zombieKilled >= zombieToKill){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		return false;
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);

		counterTime += delta;
		if (counterTime > random && zombieKilled < zombieToKill){
			random += 4 + 2 * (Math.random()-0.5);
			random -= Math.max(2, 2 * (counterTime / 40));
			G.log("" + zombieKilled);
			Zombie zombie = makeZombie(17, 23);
			zombie.fromTo(13, 22);
		}
	}


}
