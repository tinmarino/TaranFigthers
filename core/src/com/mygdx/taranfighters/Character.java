package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Character{
	public SpriteChanging spriteChanging;
	public Body body;
	public World world;

	public float x=2 ,y=0;
	public float size=2; 
	public Vector2 maxSpeed = new Vector2(1f, 6f); // WRNING DEfault values
	public Vector2 spriteOffset = new Vector2(-size/2, -1f/4 *size);	// WARNING default values 

	public boolean willChangeSprite;
	public float timeLeftChangeSprite;

	boolean isKicking=false;
	boolean isPunching=false;
	boolean isJumping=false;
	boolean isMovingLeft=false;

	int playerNumber = 1;

	ArrayList<Fixture> fixtureList;
	Fixture bottomFixture;

	BitmapFont font;


	public Character(World world){
		this.world = world;
		font = new BitmapFont();
	}


	public void draw(SpriteBatch batch, float delta){
		x = body.getPosition().x;
		y = body.getPosition().y;
		
		// SPRITE 
		spriteChanging.setX( (x+spriteOffset.x) * G.world2pixel);
		spriteChanging.setY( (y+spriteOffset.y) * G.world2pixel);
		spriteChanging.draw(batch, delta);


		// Body Velocity 
		scaleVelocity(maxSpeed);
		Vector2 vel = this.body.getLinearVelocity();
		if (Gdx.input.isKeyPressed(Keys.LEFT)  && vel.x > -0.99f* maxSpeed.x){
			body.setLinearVelocity(-maxSpeed.x, body.getLinearVelocity().y);
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)  && vel.x < 0.99f * maxSpeed.x){
			body.setLinearVelocity(maxSpeed.x, body.getLinearVelocity().y);
		}

		// DEbug 
		if (G.debug){
			font.draw(batch, 
					"grounded: " + this.isPlayerGrounded() +
					"\nMaxSpeeed: " + this.maxSpeed +
					"\nSpeed: " + this.body.getLinearVelocity(),
					(x+0.5f) * G.world2pixel, (y+0.5f) * G.world2pixel);
		}

	}



	public void punch(){
		this.isPunching =true;
	}

	public void kick(){
		this.isKicking = true;
	}

	public void walk(int side){
		if (isPunching){return;}
		body.applyForceToCenter(1000 * side, 0, true);
		spriteChanging.setFlip(true, false);
	}

	public void jump(){
		body.applyForceToCenter(0, 1000, true);
	}

	public void down(){
		body.applyForceToCenter(0, -1000, true);
	}


	// Stolen from Mario 
	private boolean isPlayerGrounded() {				
		Array<Contact> contactList = world.getContactList();
		for(int i = 0; i < contactList.size; i++) {
			Contact contact = contactList.get(i);
			if(contact.isTouching()){
			    if (contact.getFixtureA().getBody() == body ||
			   			contact.getFixtureB().getBody() == body) {				
						Gdx.app.log("Character", "My bottom fixture ");
				
						return true;
				}
				// Vector2 pos = body.getPosition();
				// WorldManifold manifold = contact.getWorldManifold();
				// boolean below = true;
				// for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
				// 	below &= (manifold.getPoints()[j].y < pos.y - 1.5f);
				// }
				// 
				// if(below) {
				// 	return true;			
				// }
				// return false;
			}
		}
		return false;
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
			isMovingLeft = false;
			return true;
		}

		return false;


	}


	public boolean keyDown(int keycode){
        if(keycode == Input.Keys.LEFT)
		{
			if (playerNumber != 1){return false;}
			walk(-1);
			return true;
		}
			
        if(keycode == Input.Keys.RIGHT)
		{
			if (playerNumber != 1){return false;}
			walk(-1);
			return true;
		}
        if(keycode == Input.Keys.UP)
		{
			if (playerNumber != 1){return false;}
			jump();
			return true;
		}
        if(keycode == Input.Keys.DOWN)
		{
			if (playerNumber != 1){return false;}
			jump();
			return true;
		}

		if (keycode == Input.Keys.L){
			if (playerNumber != 1){return false;}
			kick();
			return true;
		}

		if (keycode == Input.Keys.K){
			if (playerNumber != 1){return false;}
			punch();
			return true;
		}

		if (keycode == Input.Keys.D){
			G.debug = !G.debug;
			return true;
		}

		return false; 
	}

}


