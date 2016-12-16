package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Jak extends Character {

	public Jak(World world) {
		super(world);
		init();
	}

	public void init(){
		G.log("Jak comes in the fight");
		x = 2;
		y = 3f;

		size = 1.6f;
		defaultMaxSpeed = new Vector2(4f, 9f);
		maxSpeed = defaultMaxSpeed;
		spriteOffset = new Vector2(-size, -size * 0.65f);

		// Body and fixtures 
		createBody();
		leftArmFixture = createArm(-1);
		rightArmFixture = createArm(1);
		leftLegFixture = createLeg(-1);
		rightLegFixture = createLeg(1);



		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "jak/jak_walk1.png" , 0.3f ));
		walkList.add(new TextureTime( "jak/jak_walk2.png" , 0.3f ));
		walkList.add(new TextureTime( "jak/jak_walk3.png" , 0.3f ));

		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "jak/jak_kick1.png" , 0.1f ));
		kickList.add(new TextureTime( "jak/jak_kick2.png" , 0.1f ));
		kickList.add(new TextureTime( "jak/jak_kick3.png" , 0.1f ));
		kickList.add(new TextureTime( "jak/jak_kick4.png" , 0.1f ));
		kickList.add(new TextureTime( "jak/jak_kick5.png" , 0.1f ));
		kickList.add(new TextureTime( "jak/jak_kick6.png" , 0.1f ));


		// Punch list
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "jak/jak_punch1.png" , 0.1f ));
		punchList.add(new TextureTime( "jak/jak_punch2.png" , 0.1f ));
		punchList.add(new TextureTime( "jak/jak_punch3.png" , 0.1f ));
		punchList.add(new TextureTime( "jak/jak_punch4.png" , 0.1f ));
		punchList.add(new TextureTime( "jak/jak_punch5.png" , 0.1f ));


		spriteChanging = new SpriteChanging("iul/iul_walk1.png");
		spriteChanging.setSize(2 * size * G.world2pixel, 2* size * G.world2pixel);
		spriteChanging.setOrigin(spriteChanging.getWidth()/2 * G.world2pixel/2, spriteChanging.getHeight()/2); // to resize and rotate around the origin, here center of the sprite
		spriteChanging.setList(walkList);
		spriteChanging.pause();
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
		vertices[1] = new Vector2(side * 0.5f * size, 0.3f * size);
		vertices[2] = new Vector2(side * 0.5f * size, 0.1f * size);

		FixtureDef fix = createMember(vertices);
		return body.createFixture(fix);
	}



}
