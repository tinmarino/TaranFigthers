package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class Zombie extends Character {
	int playerNumber = 10;
	public static boolean areTextureLoaded = false;
	public static ArrayList<TextureTime> walkListS;
	public static ArrayList<TextureTime> dieListS;
	public static ArrayList<TextureTime> kickListS;

	public Zombie(World world) {
		super(world);
		init();
	}


	public void init(){
		createBody();
		loadTexture();
	}

	public void loadTexture(){
		if (!areTextureLoaded){
			// Walk 
			walkListS = new ArrayList<TextureTime>();
			walkListS.add(new TextureTime( "zombie/go_1_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_2_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_3_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_4_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_5_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_6_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_7_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_8_800.png" , 0.3f ));
			walkListS.add(new TextureTime( "zombie/go_9_800.png" , 0.3f ));

			// KickList 
			kickList = new ArrayList<TextureTime>();
			kickList.add(new TextureTime( "zombie/hit_1_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_2_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_3_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_4_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_5_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_6_800.png" , 0.3f ));
			kickList.add(new TextureTime( "zombie/hit_7_800.png" , 0.3f ));
		}
		walkList = walkListS;
		kickList = kickListS;
		spriteChanging = new SpriteChanging(size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}




}
