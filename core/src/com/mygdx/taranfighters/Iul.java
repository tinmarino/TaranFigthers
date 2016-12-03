/*
	He can jump 4m but not 5. He can go under 3 cases

*/


package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
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

	public Vector2 defaultMaxSpeed;


	



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
				setFixtureMask(leftArmFixture, 0);
				setFixtureMask(rightArmFixture, 0);
				maxSpeed = defaultMaxSpeed;
			}

			if (isPunching){
				if (spriteChanging.isFlipX()){
					if(body.getLinearVelocity().x > -0.8f * maxSpeed.x){
						body.applyForceToCenter(-1000, 0, true);
					}
				}
				else{ 
					if ( body.getLinearVelocity().x < 0.8f * maxSpeed.x){
						body.applyForceToCenter(1000, 0, true);
					}
				}
			}
		}
	}


	public void init(){
		G.log("Iul Initing");
		// variables 
		x = 2;
		y = 3f; 
		size = 1f;
		defaultMaxSpeed = new Vector2(2f, 6.5f);
		maxSpeed = defaultMaxSpeed;
		spriteOffset = new Vector2(-size, -size * 0.65f);
	
		// Body 
		createBody();

		// Leg Arm Bottom
		leftLegFixture = createLeg(-1);
		rightLegFixture = createLeg(1);
		leftArmFixture = createArm(-1);
		rightArmFixture = createArm(1);
		bottomFixture = createBottom();

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
		spriteChanging.setSize(2 * size * G.world2pixel, 2* size * G.world2pixel);
		spriteChanging.setOrigin(spriteChanging.getWidth()/2 * G.world2pixel/2, spriteChanging.getHeight()/2); // to resize and rotate around the origin, here center of the sprite
		spriteChanging.setList(walkList);
	}


	@Override
	public void kick(){
		super.kick();
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

	@Override
	public void punch(){
		super.punch();
		// Sprite 
		spriteChanging.setList(punchList);
		willChangeSprite = true;
		timeLeftChangeSprite = punchList.size() * 0.1f;
		maxSpeed = new Vector2(1.7f * defaultMaxSpeed.x, defaultMaxSpeed.y);
		
		// Body 
		if (spriteChanging.isFlipX()){
			setFixtureMask(leftArmFixture, 1);
		}
		else{
			setFixtureMask(rightArmFixture, 1);
		}
	}



	// BODY Utils
	public void createBody(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y); // in meter position of the center 
			
		// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(0.2f * size, 0.4f * size);
		
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
		vertices[1] = new Vector2(0.7f * side * size, 0.4f * size);
		vertices[2] = new Vector2(0.7f * side * size, -0.4f * size);

		FixtureDef fix = createMember(vertices);
		return body.createFixture(fix);
	}

	public Fixture createArm(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0.2f * size);
		vertices[1] = new Vector2(side * 1.0f * size, 0.3f * size);
		vertices[2] = new Vector2(side * 1.0f *size, 0.1f * size);

		FixtureDef fix = createMember(vertices);
		return body.createFixture(fix);
	}

	public Fixture createBottom(){
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(0.2f *size);
		circleShape.setPosition(new Vector2(0, -0.4f *size));

		FixtureDef fix = new FixtureDef();
		fix.shape = circleShape;
		fix.restitution = 0;
		fix.friction = 1;

		body.createFixture(fix);
		return null;
	}




	@Override
	public void manageContact(Contact contact){
		Gdx.app.log("Iul", "I contact ");
		Body otherBody;
		Fixture thisFixture;
		if (contact.getFixtureA().getBody() == body) {
			thisFixture = contact.getFixtureA();
			otherBody = contact.getFixtureB().getBody();
		}
		else if (contact.getFixtureB().getBody() == body) {
			thisFixture = contact.getFixtureB();
			otherBody = contact.getFixtureA().getBody();
		}
		else{
			return; 
		}

		if (this.isKicking){
			if (thisFixture != leftLegFixture && thisFixture != rightLegFixture){
				return;
			}
			Gdx.app.log("Iul", "I kick his ass");
			otherBody.applyForceToCenter(0 ,1000, true);
		}

		if (this.isPunching ){
			if (thisFixture != leftArmFixture && thisFixture != rightArmFixture){
				return;
			}
			Gdx.app.log("Iul", "I punch his ass");
			otherBody.applyForceToCenter(1000, 0, true);
		}
	}

}
