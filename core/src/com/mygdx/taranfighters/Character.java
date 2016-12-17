package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Character{
	// To be setted
	public SpriteChanging spriteChanging;
	public World world;

	ArrayList<TextureTime> walkList;
	ArrayList<TextureTime> punchList;
	ArrayList<TextureTime> kickList;

	Fixture rightLegFixture;
	Fixture leftLegFixture;
	Fixture leftArmFixture;
	Fixture rightArmFixture;

	// Optional
	public float x=2 ,y=0;
	public float size=2; 
	public Vector2 maxSpeed = new Vector2(1f, 6f); // WRNING DEfault values
	public Vector2 defaultMaxSpeed = maxSpeed;
	public Vector2 spriteOffset = new Vector2(-size/2, -1f/4 *size);	// WARNING default values 

	public boolean willChangeSprite;
	public float timeLeftChangeSprite;

	boolean isKicking=false;
	boolean isPunching=false;
	boolean isJumping=false;
	boolean isWalking=false;
	boolean isDying = false, isDead = false;

	int playerNumber = 1;

	ArrayList<Fixture> fixtureList;
	Body body;
	Fixture bottomFixture;

	BitmapFont font;

	public enum KIND{CHAR, ZOMBIE, RAT}

	public KIND kind = KIND.CHAR;




	public Character(World world){
		G.log("New Character created;");
		this.world = world;
		font = new BitmapFont();
	}


	public void draw(SpriteBatch batch, float delta){
		// Dye ?
		if (isDying){
			if (null != body){
				world.destroyBody(body);
				body = null;
			}
			if (timeLeftChangeSprite < 0.1f){
				isDead = true;
			}
		}


		if (!isDying){
			x = body.getPosition().x;
			y = body.getPosition().y;
		}
			
		if (!isDead){
			// SPRITE 
			spriteChanging.setX( (x+spriteOffset.x) * G.world2pixel);
			spriteChanging.setY( (y+spriteOffset.y) * G.world2pixel);
			spriteChanging.draw(batch, delta);
		}


		if (!isDying)
		{
			// Body Velocity  + defreezze
			scaleVelocity(maxSpeed);
			Vector2 vel = this.body.getLinearVelocity();
			if (Gdx.input.isKeyPressed(Keys.LEFT)  && vel.x > -0.99f* maxSpeed.x){
				body.setLinearVelocity(-maxSpeed.x, body.getLinearVelocity().y);
				spriteChanging.play();
			}

			if (Gdx.input.isKeyPressed(Keys.RIGHT)  && vel.x < 0.99f * maxSpeed.x){
				body.setLinearVelocity(maxSpeed.x, body.getLinearVelocity().y);
				spriteChanging.play();
			}

			// Debug 
			if (G.debug){
				font.draw(batch, 
						"grounded: " + this.isPlayerGrounded() +
						"\nMaxSpeeed: " + this.maxSpeed +
						"\nSpeed: " + this.body.getLinearVelocity(),
						(x+0.5f) * G.world2pixel, (y+0.5f) * G.world2pixel);
			}
		}

		// Sprite Changes 
		if (willChangeSprite){
			timeLeftChangeSprite -= delta;
			if (timeLeftChangeSprite < 0 ){
				spriteChanging.setList(walkList);
				spriteChanging.pause();
				willChangeSprite = false;
				isKicking = false;
				isPunching = false;

				setFixtureMask(leftLegFixture, 0);
				setFixtureMask(rightLegFixture, 0);
				setFixtureMask(leftArmFixture, 0);
				setFixtureMask(rightArmFixture, 0);
				maxSpeed = defaultMaxSpeed;
			}

		}


	}


	public void die(){
	}

	public void punch(){
		// Memory
		this.isPunching =true;
		
		// Sprite 
		spriteChanging.setList(punchList);
		willChangeSprite = true;
		timeLeftChangeSprite = punchList.size() * 0.1f;
		
		// Body 
		if (spriteChanging.isFlipX()){
			setFixtureMask(leftArmFixture, 1);
		}
		else{
			setFixtureMask(rightArmFixture, 1);
		}
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

	public void walk(int side){
		// Memory 
		this.isWalking = true;
		spriteChanging.play();
		body.applyForceToCenter(1000 * side, 0, true);
		if (-1 == side){
			spriteChanging.setFlip(true, false);
		}
		else if(1 == side){
			spriteChanging.setFlip(false, false);
		}
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
			G.log("Character : I kick his ass ");
			otherBody.applyForceToCenter(0 ,1000, true);
			// dye 
			if (otherBody.getUserData() instanceof Character){
				((Character) otherBody.getUserData()).die();
			}
			return;
		}

		if (this.isPunching ){
			if (thisFixture != leftArmFixture && thisFixture != rightArmFixture){
				return;
			}
			G.log("Character : I punch his ass");
			otherBody.applyForceToCenter(1000, 0, true);
			return;
		}
		return;
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
		body.setTransform(x, y, body.getAngle()); 
	}


	public static void setFixtureMask(Fixture fixture, int mask){
		if (null == fixture){return;}
		Filter filter = fixture.getFilterData();
		filter.maskBits = (short) mask;
		fixture.setFilterData(filter);
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

	// BODY Utils calls createBottom
	public void createBody(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y); // in meter position of the center 
			
		// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		if (kind == KIND.CHAR){
			bodyShape.setAsBox(0.2f * size, 0.4f * size);
		}
		else{
			bodyShape.setAsBox(0.2f * size, 0.2f * size);
		}
		
		// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0;
		bodyFix.friction = 0;

		// Create Body 
		body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		
		// Add Ref to me to Dye and staff 
		body.setUserData(this); 

		// Call createBottom
		bottomFixture = createBottom();
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
			this.isWalking = false;

			Vector2 vel = body.getLinearVelocity();
			vel.x = 0;
			body.setLinearVelocity(vel);

			if (isJumping || isKicking || isPunching){
				return true;
			}

			spriteChanging.pause();
			return true;
		}

		return false;


	}


	public boolean keyDown(int keycode){
        if(keycode == Input.Keys.LEFT) {
			if (playerNumber != 1){return false;}
			walk(-1);
			return true;
		}
			
        if(keycode == Input.Keys.RIGHT) {
			if (playerNumber != 1){return false;}
			walk(1);
			return true;
		}

        if(keycode == Input.Keys.UP) {
			if (playerNumber != 1){return false;}
			jump();
			return true;
		}

        if(keycode == Input.Keys.DOWN){
			if (playerNumber != 1){return false;}
			down();
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

		if (keycode == Input.Keys.NUM_0){
			G.debug = !G.debug;
			return true;
		}

		return false; 
	}

	// Currently just for one player
	public boolean touchDown (int screenX, int screenY, int pointer, int button){
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		if (playerNumber != 1) {return false;}

		// MOVE left 
		if (screenX < width / 4){
			walk(-1);
			return true;
		}

		// MOVE right
		if (screenX < width / 2){
			walk(1);
			return true;
		}

		// JUMP (Y starts at top)
		if (screenY < height / 2 ){
			jump();
			return true;
		}

		// PUNCH
		if (screenX < 3.0f/4 * width){
			punch();
			return true;
		}
		
		// KICK
		kick();
		return true;
	}

}


