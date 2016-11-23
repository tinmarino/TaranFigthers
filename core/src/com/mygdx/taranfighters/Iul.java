package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public float x=0 ,y=0;
	public float size=2; 

	public void setX(float x){
		this.x = x;
		spriteChanging.setX( (x-size/2) * G.world2pixel);
		body.setTransform(x, y-0.5f, 0f);
	}

	public void setY(float y){
		this.y = y;
		spriteChanging.setY( (y-size/2) * G.world2pixel);
		body.setTransform(x, y-0.5f, 0f);
	}
	
	public void setXY(float x, float y){
		setX(x);
		setY(y);
	}

	public Iul(World world){
		this.world = world;
		init();
	}

	public Iul(){
		super();
		init();
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

	@Override
	public void draw(SpriteBatch batch, float delta){
		spriteChanging.draw(batch, delta);
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
		bodyFix.restitution = 1;
		bodyFix.friction = 0;
		
	// Create Body 
		body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		
	// Add Body Sprite 
		body.setUserData(spriteChanging); 
	}


//
//
//
}
