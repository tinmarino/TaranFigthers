package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Roz extends Character {

	public Roz(World world) {
		super(world);
		init();
	}



	public void init(){
		G.log("Fix comes in the fight");
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
		walkList.add(new TextureTime( "roz/roz_walk1.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk2.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk3.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk4.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk5.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk6.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk7.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk8.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk9.png" , 0.1f ));
		walkList.add(new TextureTime( "roz/roz_walk10.png" , 0.1f ));

		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "roz/roz_kick1.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick2.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick3.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick4.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick5.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick6.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick7.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick8.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick9.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick10.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick11.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick12.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick13.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick14.png" , 0.05f ));
		kickList.add(new TextureTime( "roz/roz_kick15.png" , 0.05f ));


		// Punch list
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "roz/roz_punch1.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch2.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch3.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch4.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch5.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch4.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch3.png" , 0.1f ));
		punchList.add(new TextureTime( "roz/roz_punch2.png" , 0.1f ));


		spriteChanging = new SpriteChanging(size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}

}
