package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class Character implements Disposable{
	// To be setted
	public SpriteChanging spriteChanging;
	public World world;
	public Music music_name;

	public ArrayList<TextureTime> walkList;
	public ArrayList<TextureTime> punchList;
	public ArrayList<TextureTime> kickList;

	public Fixture rightLegFixture;
	public Fixture leftLegFixture;
	public Fixture leftArmFixture;
	public Fixture rightArmFixture;

	// Optional
	public float x=2 ,y=0;
	public float size = 1.6f; 
	public Vector2 maxSpeed = new Vector2(6f, 13f); // WRNING DEfault values
	public Vector2 defaultMaxSpeed = maxSpeed;
	public Vector2 spriteOffset = new Vector2(-size, -size * 0.70f);

	public boolean willChangeSprite;
	public float timeLeftChangeSprite;

	public boolean isKicking=false;
	public boolean isPunching=false;
	public boolean isJumping=true;
	public boolean isDoubleJumping=false;
	public boolean isWalking=false;
	public boolean isDead = false;

	public int playerNumber = 1;

	public ArrayList<Fixture> fixtureList;
	public Body body;
	public Fixture bottomFixture;

	public enum KIND{CHAR, ZOMBIE, RAT}

	public KIND kind = KIND.CHAR;

	Shape toDisposeShape;



	private void drawSprite(SpriteBatch batch, float delta){
		spriteChanging.setX( (x+spriteOffset.x) * G.world2pixel);
		spriteChanging.setY( (y+spriteOffset.y) * G.world2pixel);
		spriteChanging.draw(batch, delta);


		// Debug 
		if (G.debug){
			String s = "\nMaxSpeeed: " + this.maxSpeed +
						"\nJump: " + this.isJumping +
						"\nDJump: " + this.isDoubleJumping +
						"\nPos: " + x  + ", " + y;
			if (body != null){ 
				s += "\nSpeed: " + this.body.getLinearVelocity();
			}
			G.debugFont.draw(batch, s,
					(x+0.5f) * G.world2pixel, (y+0.5f) * G.world2pixel);
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

				if ( body != null){
					setFixtureMask(leftLegFixture, 0);
					setFixtureMask(rightLegFixture, 0);
					setFixtureMask(leftArmFixture, 0);
					setFixtureMask(rightArmFixture, 0);
				}
				maxSpeed = defaultMaxSpeed;
			}
		}
	}


	public void draw(SpriteBatch batch, float delta){
		drawSprite(batch, delta);
		if (body == null){return;}
		x = body.getPosition().x;
		y = body.getPosition().y;

		// Body Velocity  + defreezze
		scaleVelocity(maxSpeed);
		// For humans 
		Vector2 vel = this.body.getLinearVelocity();
		if (playerNumber == 1){
			if (this.isPressingLeft()){
				spriteChanging.play();
				if (vel.x > -0.99f* maxSpeed.x){
					body.setLinearVelocity(-maxSpeed.x, body.getLinearVelocity().y);
				}
			}

			if (this.isPressingRight()){
				spriteChanging.play();
				if (vel.x < 0.99f * maxSpeed.x){
					body.setLinearVelocity(maxSpeed.x, body.getLinearVelocity().y);
				}
			}
		}
		// For zombie
		if (playerNumber == 10){
			if (((Zombie) this).direction == -1  && vel.x > -0.99f* maxSpeed.x){
				body.setLinearVelocity(-maxSpeed.x, body.getLinearVelocity().y);
				spriteChanging.play();
			}

			if (((Zombie) this).direction == 1  && vel.x < 0.99f * maxSpeed.x){
				body.setLinearVelocity(maxSpeed.x, body.getLinearVelocity().y);
				spriteChanging.play();
			}
		}


	}

	public Character(World world){
		G.log("New Character created;");
		this.world = world;
	}


	public static Character createCharacter(G.CHAR charEnum, World world){
		switch (charEnum){
			case JAK:
				return new Jak(world);
			case ROZ:
				return new Roz(world);
			case IUL:
				return new Iul(world);
			case FIX:
				return new Fix(world);
			case TIN:
				return new Tin(world);
			default:
				return new Iul(world);
		}
	}



	public void die(){
		isDead = true;
	}

	public void punch(){
		// Memory
		this.isPunching =true;
		
		// Sprite 
		spriteChanging.setList(punchList);
		willChangeSprite = true;
		timeLeftChangeSprite = TextureTime.getTime(punchList);
		
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
		timeLeftChangeSprite = TextureTime.getTime(kickList);

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
		if (this.isJumping){
			if (this.isDoubleJumping){return;}
			this.isDoubleJumping = true;
		}
		this.isJumping = true;
		body.applyForceToCenter(0, 1000, true);
	}

	public void down(){
		body.applyForceToCenter(0, -1000, true);
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


		// Make Zombies kick me
		if (playerNumber == 1){
			if (otherBody.getUserData() instanceof Zombie){
				//Character otherCharacter = ((Character) otherBody.getUserData());
				if (!isKicking && !isPunching){
					if (! ((Zombie) otherBody.getUserData()).isDead){
						die();
					}
				}
			}
		}

		// Reset jumps
		if (thisFixture == bottomFixture){
			isDoubleJumping = false;
			isJumping = false;
		}

		if (this.isKicking){
			if (thisFixture != leftLegFixture && thisFixture != rightLegFixture){
				return;
			}
			G.log("Character : I kick his ass ");
			otherBody.applyForceToCenter(500 , 2000, true);
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
		circleShape.setRadius(0.17f *size);
		circleShape.setPosition(new Vector2(0, -0.4f *size));

		FixtureDef fix = new FixtureDef();
		fix.shape = circleShape;
		fix.restitution = 0;
		fix.friction = 1;

		Fixture fixture = body.createFixture(fix);
		circleShape.dispose();
		return fixture;
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
		bodyShape.dispose();
		
		// Add Ref to me to Dye and staff 
		body.setUserData(this); 

		// Call createBottom
		bottomFixture = createBottom();
	}

	
	public FixtureDef createMember(Vector2[] vertices){
		// LegShape 
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.set(vertices);
		toDisposeShape = polygonShape;
		
		// LegFixture 
		FixtureDef legFix = new FixtureDef();
		legFix.shape = polygonShape;
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

	public Fixture createLeg(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(0.7f * side * size, 0.4f * size);
		vertices[2] = new Vector2(0.7f * side * size, -0.4f * size);

		FixtureDef fix = createMember(vertices);
		Fixture fixture = body.createFixture(fix);
		toDisposeShape.dispose();
		return fixture;
	}

	public Fixture createArm(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0.2f * size);
		vertices[1] = new Vector2(side * 0.5f * size, 0.3f * size);
		vertices[2] = new Vector2(side * 0.5f * size, 0.1f * size);

		FixtureDef fix = createMember(vertices);
		Fixture fixture = body.createFixture(fix);
		toDisposeShape.dispose();
		return fixture;
	}

	// TOUCH 
	public boolean keyUp(int keycode){
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT)
		{
			// if no both buttons
			if ((!isPressingLeft()) && (!isPressingRight())){
				stopWalking();
			}

			Vector2 vel = body.getLinearVelocity();
			vel.x = 0;
			body.setLinearVelocity(vel);

			if (isJumping || isKicking || isPunching){
				return true;
			}
			stopWalking();

			return true;
		}

		return false;


	}

	public void stopWalking(){
		if (spriteChanging.currentList == walkList){
			spriteChanging.pause();
		}
		this.isWalking = false;
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
		return false; 
	}

	// Currently just for one player,
	// Warning change also isPressingLeft 
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

	public boolean isPressingLeft(){
		int width = Gdx.graphics.getWidth();
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			return true;
		}
		if (Gdx.input.isTouched()){
			int screenX = Gdx.input.getX();
			if (screenX < width / 4){
				return true;
			}
		}
		return false;
	}

	public boolean isPressingRight(){
		int width = Gdx.graphics.getWidth();
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			return true;
		}
		if (Gdx.input.isTouched()){
			int screenX = Gdx.input.getX();
			if (screenX < width / 2 && screenX > width/4){
				return true;
			}
		}
		return false;
	}

	@Override
	public void dispose() {
		if (null != walkList){
	 		for (TextureTime i : walkList){
				G.disposeW(i.texture);
			}
		}
		if (null != kickList){
	 		for (TextureTime i : kickList){
				G.disposeW(i.texture);
			}
		}
		if (null != punchList){
	 		for (TextureTime i : punchList){
				G.disposeW(i.texture);
			}
		}
	}

}


