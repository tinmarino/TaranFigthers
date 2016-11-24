package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Iul extends Character{
	ArrayList<TextureTime> walkList;
	ArrayList<TextureTime> punchList;
	World world;
	// in meters 
	public float x=2 ,y=0;
	public float size=2; 
	public float maxSpeed = 1f;




	public void ImpulseX(float imp){
	}

	public Iul(World world){
		this.world = world;
		init();
	}

	public Iul(){
		super();
		init();
	}
		
	@Override
	public void draw(SpriteBatch batch, float delta){
		x = body.getPosition().x;
		y = body.getPosition().y;
		
		// SPRITE 
		spriteChanging.setX( (x-size/2) * G.world2pixel);
		spriteChanging.setY( (y-1f/4* size) * G.world2pixel);
		spriteChanging.draw(batch, delta);

		scaleVelocity(maxSpeed);

	}


	public void init(){
		// Body 
		createBody();

		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "iul/iul_walk1.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk2.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk3.png" , 0.3f ));

		spriteChanging = new SpriteChanging("iul/iul_walk1.png");
		spriteChanging.setSize(size*G.world2pixel, size*G.world2pixel);
		spriteChanging.setOrigin(size*G.world2pixel/2, size*G.world2pixel/2); // to resize and rotate around the origin, here center of the sprite
		spriteChanging.currentList = walkList;
	}



	private void scaleVelocity(float scaleSpeed){
		Vector2 vel = body.getLinearVelocity();
		float speed = vel.len2();
		if (speed > scaleSpeed){
			body.setLinearVelocity( vel.limit( scaleSpeed) );
		}
	}


	public void createBody(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y); // in meter position of the center 
			
	// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(size/8, size/4);
		

	// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0;
		bodyFix.friction = 0;
		
	// Create Body 
		body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		
	// Add Body Sprite 
		body.setUserData(spriteChanging); 
	}


	public boolean keyDown(int keycode){
        if(keycode == Input.Keys.LEFT)
		{
			body.applyForceToCenter( -1000, 0, true);
			spriteChanging.setFlip(true, false);
			return true;
		}
			
        if(keycode == Input.Keys.RIGHT)
		{
			body.applyForceToCenter( 1000, 0, true);
			spriteChanging.setFlip(false, false);
			return true;
		}
        if(keycode == Input.Keys.UP)
		{
			body.applyForceToCenter(0, 1000, true);
			spriteChanging.flip(true, false);
			return true;
		}
        if(keycode == Input.Keys.DOWN)
		{
			body.applyForceToCenter(0, -1000, true);
			return true;
		}

		scaleVelocity(maxSpeed);
		return false; 
	}
//
//
//
}
