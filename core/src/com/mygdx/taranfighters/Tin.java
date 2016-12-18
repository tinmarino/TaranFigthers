package com.mygdx.taranfighters;
/*
 * TODO add tin_kick12 ?? for better smooth
*/


import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Tin extends Character {

	public Tin(World world) {
		super(world);
		init();
	}



	public void init(){
		G.log("Tin comes in the fight");
		x = 2;
		y = 3f;
		music_name = G.music("music/char/tin_sound.mp3");

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
		walkList.add(new TextureTime( "tin/tin_walk1.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk2.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk3.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk4.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk5.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk6.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk7.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk8.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk9.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk10.png" , 0.1f ));
		walkList.add(new TextureTime( "tin/tin_walk11.png" , 0.1f ));

		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "tin/tin_kick1.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick2.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick3.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick4.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick5.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick6.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick7.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick8.png" , 0.05f ));
		kickList.add(new TextureTime( "tin/tin_kick9.png" , 0.05f ));

		// Punch list
		punchList = new ArrayList<TextureTime>();


		spriteChanging = new SpriteChanging(size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}

}