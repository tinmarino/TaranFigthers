package com.mygdx.taranfighters;
/*
 * TODO add tin_kick12 ?? for better smooth
*/


import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class Tin extends Character {

	public Tin(World world) {
		super(world);
		init();
	}



	public void init(){
		G.log("Tin comes in the fight");
		music_name = G.music("music/char/tin_sound.mp3");

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
		kickList.add(new TextureTime( "tin/tin_kick1.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick2.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick3.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick4.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick5.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick6.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick7.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick8.png" , 0.07f ));
		kickList.add(new TextureTime( "tin/tin_kick9.png" , 0.07f ));

		// Punch list
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "tin/tin_punch1.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch2.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch3.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch4.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch5.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch5.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch4.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch3.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch2.png" , 0.05f ));
		punchList.add(new TextureTime( "tin/tin_punch1.png" , 0.05f ));


		spriteChanging = new SpriteChanging(walkList.get(0).texture, size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}

}
