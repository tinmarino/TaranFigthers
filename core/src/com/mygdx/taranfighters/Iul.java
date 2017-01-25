/*
	He can jump 4m but not 5. He can go under 3 cases

*/


package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Iul extends Character{


	public Iul(World world){
		super(world);
		init();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		super.draw(batch, delta);
		if (!isPunching){
			return;
		}

		Vector2 vel = this.body.getLinearVelocity();
		// force key pushed 
		if (vel.x > -0.99f* maxSpeed.x){
			body.setLinearVelocity(-maxSpeed.x, body.getLinearVelocity().y);
			spriteChanging.play();
		}
		if (vel.x < 0.99f * maxSpeed.x){
			body.setLinearVelocity(maxSpeed.x, body.getLinearVelocity().y);
			spriteChanging.play();
		}

		// accelerate like bitch
		if (spriteChanging.isFlipX()){
			if(body.getLinearVelocity().x > -0.8f * maxSpeed.x){
				body.applyForceToCenter(-1000, 0, true);
			}
		}
		else if ( body.getLinearVelocity().x < 0.8f * maxSpeed.x){
			body.applyForceToCenter(1000, 0, true);
		}
	}

	public void init(){
		G.log("Iul Initing");
		// variables 
		music_name = G.music("music/char/iul_sound.mp3");
	
		// Body 
		createBody();

		// Leg Arm Bottom
		leftLegFixture = createLeg(-1);
		rightLegFixture = createLeg(1);
		leftArmFixture = createArm(-1);
		rightArmFixture = createArm(1);

		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "iul/iul_walk1.png" , 0.1f ));
		walkList.add(new TextureTime( "iul/iul_walk2.png" , 0.1f ));
		walkList.add(new TextureTime( "iul/iul_walk3.png" , 0.1f ));


		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "iul/iul_kick1.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/iul_kick2.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/iul_kick3.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/iul_kick4.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/iul_kick5.png" , 0.1f ));
		kickList.add(new TextureTime( "iul/iul_kick6.png" , 0.1f ));

		// Punch list
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "iul/iul_punch1.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch2.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch3.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch4.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch5.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch6.png" , 0.1f ));
		punchList.add(new TextureTime( "iul/iul_punch7.png" , 0.1f ));

		spriteChanging = new SpriteChanging(walkList.get(0).texture, size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}

	@Override
	public void punch(){
		super.punch();
		maxSpeed = new Vector2(1.7f * defaultMaxSpeed.x, defaultMaxSpeed.y);
	}

	@Override 
	public void walk(int side){
		if (isPunching){return;}
		super.walk(side);
	}


	@Override
	public Fixture createArm(int side){
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0.2f * size);
		vertices[1] = new Vector2(side * 1.0f * size, 0.3f * size);
		vertices[2] = new Vector2(side * 1.0f *size, 0.1f * size);

		FixtureDef fix = createMember(vertices);
		Fixture fixture = body.createFixture(fix);
		toDisposeShape.dispose();
		return fixture;
	}

}
