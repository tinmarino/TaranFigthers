package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Rat extends Character{
	ArrayList<TextureTime> walkList;


	public Rat(World world){
		super(world);
		init();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);
	}



	public void init(){
		// Params 
		x = 3;
		y = 0.5f;
		size = 2;
		spriteOffset.y = -size/8;
		
		
		// Sprite 
		initSprite();

		// Body 
	 	initBody();
	}


	public void initSprite(){
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "rat/rat1.png" , 0.1f ));
		walkList.add(new TextureTime( "rat/rat2.png" , 0.1f ));
		walkList.add(new TextureTime( "rat/rat3.png" , 0.1f ));
		walkList.add(new TextureTime( "rat/rat4.png" , 0.1f ));
		walkList.add(new TextureTime( "rat/rat5.png" , 0.1f ));

		spriteChanging = new SpriteChanging("rat/rat1.png");
		spriteChanging.setSize(size*G.world2pixel, size*G.world2pixel);
		spriteChanging.setOrigin(size*G.world2pixel/2, size*G.world2pixel/2);
		spriteChanging.setList(walkList);
	}


	public void initBody(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y); // in meter position of the center 
			
		// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(size/4, size/8);
		

		// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0.1f;
		bodyFix.friction = 0;
		bodyFix.filter.maskBits = 1;
		
		// Create Body 
		body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		
		// Add Body Sprite 
		body.setUserData(spriteChanging); 
	}





}
