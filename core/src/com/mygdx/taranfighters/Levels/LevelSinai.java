package com.mygdx.taranfighters.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.Level;
import com.mygdx.taranfighters.VolleyBall;
import com.mygdx.taranfighters.Character;

public class LevelSinai extends Level {
	int counter = 30;

	@Override
	public boolean isLevelFinished(Character char1){
		if (charList.size() == 0){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		if (char1.y < -5){
			this.finishedEnum = FINISHED.GAMEOVER;
			return true;
		}
		return false;
	}

	public LevelSinai(World world) {
		super("map/sinai.tmx", world);
		initialPosition = new Vector2(18, 20);
		music_quote = G.music("music/quote/he_who_is_prudent.mp3");
		music = G.music("music/i_will_survive_full.mp3");
	}


	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);
		if (counter > 0){
			addVolleyBall(new Vector2(20, 10));
			counter--;
		}
		for (Character volleyBall : charList){
			if (volleyBall.isDead){
				deadCharacter.add(volleyBall);
			}
		}
	}


	public void addVolleyBall(Vector2 position)
	{
		VolleyBall volleyBall = new VolleyBall(world);
		volleyBall.setPosition(position.x, position.y);
		charList.add(volleyBall);
	}

}
