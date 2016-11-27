package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
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

	Fixture leftArmFixture;
	Fixture rightArmFixture;

	boolean isKicking=false;
	boolean isPunching=false;
	boolean isJumping=false;

	



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
				isKicking = false;
				isPunching = false;

				setFixtureMask(leftLegFixture, 0);
				setFixtureMask(rightLegFixture, 0);
				maxSpeed = new Vector2(3f, 7f);
			}

			if (isPunching){
				if (spriteChanging.isFlipX()){
					if(body.getLinearVelocity().x > -maxSpeed.x/1.3f){
						body.applyForceToCenter(-1000, 0, true);
					}
				}
				else{ 
					if ( body.getLinearVelocity().x < maxSpeed.x/1.1f){
						body.applyForceToCenter(1000, 0, true);
					}
				}
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
		leftArmFixture = createArm(-1);
		rightArmFixture = createArm(1);

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

		// Punch lisr 
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "iul/iul_punch1.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch2.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch3.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch4.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch5.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch6.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch7.png" , 0.1f ));

		spriteChanging = new SpriteChanging("iul/iul_walk1.png");
		spriteChanging.setSize(size*G.world2pixel, size*G.world2pixel);
		spriteChanging.setOrigin(size*G.world2pixel/2, size*G.world2pixel/2); // to resize and rotate around the origin, here center of the sprite
		spriteChanging.setList(walkList);
	}


	public void kick(){
		this.isKicking = true;
		// Sprite 
		spriteChanging.setList(kickList);
		willChangeSprite = true;
		timeLeftChangeSprite = kickList.size() *  0.1f;

		// Body 
		if (spriteChanging.isFlipX()){
			setFixtureMask(leftLegFixture, 1);
		}
		else{
			setFixtureMask(rightLegFixture, 1);
		}
	}

	public void punch(){
		this.isPunching =true;
		// Sprite 
		spriteChanging.setList(punchList);
		willChangeSprite = true;
		timeLeftChangeSprite = punchList.size() * 0.1f;
		maxSpeed = new Vector2(6f, 7f);
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
		vertices[1] = new Vector2(side * 0.7f, 0.6f);
		vertices[2] = new Vector2(side * 0.7f, -0.4f);

		FixtureDef fix = createMember(vertices);
		return body.createFixture(fix);
	}

	public Fixture createArm(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0.4f);
		vertices[1] = new Vector2(side * 1.0f, 0.5f);
		vertices[2] = new Vector2(side * 1.0f, 0.3f);

		FixtureDef fix = createMember(vertices);
		return body.createFixture(fix);
	}


	@Override
	public void manageContact(Contact contact){
		Gdx.app.log("Iul", "I contact ");
		Body otherBody;
		if (contact.getFixtureA().getBody() == body) {
			otherBody = contact.getFixtureB().getBody();
		}
		else if (contact.getFixtureB().getBody() == body) {
			otherBody = contact.getFixtureA().getBody();
		}
		else{
			return; 
		}

		if (this.isKicking){
			Gdx.app.log("Iul", "I kick his ass");
			otherBody.applyForceToCenter(1000,1000, true);
		}

		if (this.isPunching){
			Gdx.app.log("Iul", "I punch his ass");
			otherBody.applyForceToCenter(-1000,-1000, true);
		}
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
		}

		if (keycode == Input.Keys.K){
			punch();
		}

		scaleVelocity(maxSpeed);
		return false; 
	}
}
