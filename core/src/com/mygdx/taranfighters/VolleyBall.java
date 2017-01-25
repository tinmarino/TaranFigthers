
package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class VolleyBall extends Character{
	ArrayList<TextureTime> walkList;

	public VolleyBall(World world){
		super(world);
		init();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		spriteChanging.setRotation(body.getAngle()*57); // 360/2pi
		super.draw(batch, delta);
		if (y < 0){ isDead = true; }
	}



	public void init(){
		playerNumber = -1;
		size = 1.4f;
		spriteOffset = new Vector2(-size / 2, -size / 2);
		

		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "img/volley200.png" , Float.MAX_VALUE));

		spriteChanging = new SpriteChanging(walkList.get(0).texture, size/2);
		spriteChanging.setList(walkList);

		createBodyVolleyBall();
	}

	@Override
	public  void die(){
		// please dont dye
	}

	public void createBodyVolleyBall(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y); // in meter position of the center 
			
		// BodyShape 
		CircleShape bodyShape = new CircleShape();
		bodyShape.setRadius(size/2);
		
		// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0.8f;
		bodyFix.friction = 1;
		bodyFix.density = 0.1f;

		// Create Body 
		body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		body.setFixedRotation(false);
		bodyShape.dispose();
		
		// Add Ref to me to Dye and staff 
		body.setUserData(this); 
	}




}
