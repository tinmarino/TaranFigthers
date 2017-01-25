package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class Jak extends Character {

	public Jak(World world) {
		super(world);
		init();
	}

	public void init(){
		G.log("Jak comes in the fight");
		music_name = G.music("music/char/jak_sound.mp3");

		// Body and fixtures 
		createBody();
		leftArmFixture = createArm(-1);
		rightArmFixture = createArm(1);
		leftLegFixture = createLeg(-1);
		rightLegFixture = createLeg(1);



		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "jak/jak_walk1.png" , 0.15f ));
		walkList.add(new TextureTime( "jak/jak_walk2.png" , 0.15f ));
		walkList.add(new TextureTime( "jak/jak_walk3.png" , 0.15f ));

		// Kick list 
		kickList = new ArrayList<TextureTime>();
		kickList.add(new TextureTime( "jak/jak_kick1.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick2.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick3.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick4.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick5.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick4.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick3.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick2.png" , 0.07f ));
		kickList.add(new TextureTime( "jak/jak_kick1.png" , 0.07f ));


		// Punch list
		punchList = new ArrayList<TextureTime>();
		punchList.add(new TextureTime( "jak/jak_punch1.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch2.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch3.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch4.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch5.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch4.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch3.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch2.png" , 0.07f ));
		punchList.add(new TextureTime( "jak/jak_punch1.png" , 0.07f ));


		spriteChanging = new SpriteChanging(walkList.get(0).texture, size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}

}
