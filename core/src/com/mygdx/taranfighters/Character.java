package com.mygdx.taranfighters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Character{
	public SpriteChanging spriteChanging;
	public Body body;
	public World world;

	public float x=2 ,y=0;
	public float size=2; 
	public Vector2 maxSpeed = new Vector2(3f, 7f);
	public Vector2 spriteOffset = new Vector2(-size/2, -1f/4 *size);

	public boolean willChangeSprite;
	public float timeLeftChangeSprite;



	public Character(World world){
		this.world = world;
	}


	public void draw(SpriteBatch batch, float delta){
		x = body.getPosition().x;
		y = body.getPosition().y;
		
		// SPRITE 
		spriteChanging.setX( (x+spriteOffset.x) * G.world2pixel);
		spriteChanging.setY( (y+spriteOffset.y) * G.world2pixel);
		spriteChanging.draw(batch, delta);

		scaleVelocity(maxSpeed);
	}


	public void setPosition(float x, float y){
		body.setTransform(x, y, body.getAngle()); 
	}


	public void scaleVelocity(Vector2 scaleSpeed){
		Vector2 vel = body.getLinearVelocity();

		// Scale X
		if (vel.x > scaleSpeed.x){
			vel.x = scaleSpeed.x;
		}
		else if (vel.x < -scaleSpeed.x){
			vel.x = -scaleSpeed.x;
		}

		// Scale Y
		if (vel.y > scaleSpeed.y){
			vel.y = scaleSpeed.y;
		}
		else if (vel.y < -scaleSpeed.y){
			vel.y = -scaleSpeed.y;
		}
		body.setLinearVelocity(vel);
	}


	public boolean keyUp(int keycode){
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT)
		{
			Vector2 vel = body.getLinearVelocity();
			vel.x = 0;
			body.setLinearVelocity(vel);
			return true;
		}

		return false;


	}
}


