package com.mygdx.taranfighters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

	boolean isKicking=false;
	boolean isPunching=false;
	boolean isJumping=false;

	int playerNumber = 1;


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



	public void punch(){
		this.isPunching =true;
	}

	public void kick(){
		this.isKicking = true;
	}


	public void manageContact(Contact contact){
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
		body.setTransform(x, y, body.getAngle()); 
	}


	public static void setFixtureMask(Fixture fixture, int mask){
		Filter filter = fixture.getFilterData();
		filter.maskBits = (short) mask;
		fixture.setFilterData(filter);
	}

	
	public static FixtureDef createMember(Vector2[] vertices){
		// LegShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.set(vertices);
		
		// LegFixture 
		FixtureDef legFix = new FixtureDef();
		legFix.shape = bodyShape;
		legFix.restitution = 0;
		legFix.friction = 0;
		legFix.filter.maskBits = 0;

		return legFix;
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


	public boolean keyDown(int keycode){
        if(keycode == Input.Keys.LEFT)
		{
			if (isPunching){return true;}
			body.applyForceToCenter( -1000, 0, true);
			spriteChanging.setFlip(true, false);
			return true;
		}
			
        if(keycode == Input.Keys.RIGHT)
		{
			if (isPunching){return true;}
			body.applyForceToCenter( 1000, 0, true);
			spriteChanging.setFlip(false, false);
			return true;
		}
        if(keycode == Input.Keys.UP)
		{
			body.applyForceToCenter(0, 1000, true);
			return true;
		}
        if(keycode == Input.Keys.DOWN)
		{
			body.applyForceToCenter(0, -1000, true);
			return true;
		}

		if (keycode == Input.Keys.L){
			kick();
			return true;
		}

		if (keycode == Input.Keys.K){
			punch();
			return true;
		}

		scaleVelocity(maxSpeed);
		return false; 
	}

}


