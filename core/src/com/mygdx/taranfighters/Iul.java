package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Iul extends Character{
	ArrayList<TextureTime> walkList;
	ArrayList<TextureTime> punchList;
	ArrayList<TextureTime> kickList;

	Fixture rightLegFixture;
	Fixture leftLegFixture;

	



	public Iul(World world){
		super(world);
		init();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);

		if (willChangeSprite){
			timeLeftChangeSprite -= delta;
			if (timeLeftChangeSprite < 0 ){
				spriteChanging.setList(walkList);
				willChangeSprite = false;

				setFixtureMask(leftLegFixture, 0);
				setFixtureMask(rightLegFixture, 0);
			}
		}
	}


	public void init(){
		// variables 
		x = 2;
		y = 0.5f; 
		size = 2;
		maxSpeed = new Vector2(3f, 7f);
	
		// Body 
		createBody();

		// Leg 
		leftLegFixture = createLeg(-1);
		rightLegFixture = createLeg(1);

		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "iul/iul_walk1.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk2.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk3.png" , 0.3f ));


		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "iul/kick/iul_kick1.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/kick/iul_kick2.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/kick/iul_kick3.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/kick/iul_kick4.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/kick/iul_kick5.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/kick/iul_kick6.png" , 0.1f ));


		spriteChanging = new SpriteChanging("iul/iul_walk1.png");
		spriteChanging.setSize(size*G.world2pixel, size*G.world2pixel);
		spriteChanging.setOrigin(size*G.world2pixel/2, size*G.world2pixel/2); // to resize and rotate around the origin, here center of the sprite
		spriteChanging.setList(walkList);
	}


	public void kick(){
		// Sprite 
		spriteChanging.setList(kickList);
		willChangeSprite = true;
		timeLeftChangeSprite = kickList.size() *  0.1f;

		// Body 
		Fixture fixture;
		if (spriteChanging.isFlipX()){
			setFixtureMask(leftLegFixture, 1);
		}
		else{
			setFixtureMask(rightLegFixture, 1);
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


	public Fixture createLeg(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(side * 0.5f, 0.6f);
		vertices[2] = new Vector2(side * 0.5f, -0.4f);

		// LegShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.set(vertices);
		
		// LegFixture 
		FixtureDef legFix = new FixtureDef();
		legFix.shape = bodyShape;
		legFix.restitution = 0;
		legFix.friction = 0;
		legFix.filter.maskBits = 0;
		
		return body.createFixture(legFix);
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
			return true;
		}
        if(keycode == Input.Keys.DOWN)
		{
			body.applyForceToCenter(0, -1000, true);
			return true;
		}

		if (keycode == Input.Keys.L){
			kick();
		}


		scaleVelocity(maxSpeed);
		return false; 
	}
}
