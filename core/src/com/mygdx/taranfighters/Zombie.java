package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Zombie extends Character {
	public static boolean areTextureLoaded = false;
	public static ArrayList<TextureTime> walkListS;
	public static ArrayList<TextureTime> dieListS;
	public static ArrayList<TextureTime> kickListS;

	public ArrayList<TextureTime> dieList;




	public Zombie(World world) {
		super(world);
		init();
	}


	public void init(){
		G.log("Zombie : Zombie created");
		this.kind = KIND.ZOMBIE;
		playerNumber = 10;
		size = 2.5f;
		spriteOffset = new Vector2(-size, -size * 0.65f);
		createBody();
		loadTexture();
	}


	@Override
	public void die(){
		G.log("Zombie : is dying");
		// Sprite 
		spriteChanging.setList(dieList);
		willChangeSprite = true;
		timeLeftChangeSprite = (dieList.size()) *  0.2f;

		this.isDying = true;
	}

	public void loadTexture(){
		if (!areTextureLoaded){
			areTextureLoaded = true;
			G.log("Zombie : Is loading texture");

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

			// Kick
			kickListS = new ArrayList<TextureTime>();
			kickListS.add(new TextureTime( "zombie/hit_1_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_2_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_3_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_4_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_5_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_6_800.png" , 0.3f ));
			kickListS.add(new TextureTime( "zombie/hit_7_800.png" , 0.3f ));

			// Die
			dieListS = new ArrayList<TextureTime>();
			dieListS.add(new TextureTime( "zombie/die_1_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_2_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_3_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_4_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_5_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_6_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_7_800.png" , 0.2f ));
			dieListS.add(new TextureTime( "zombie/die_8_800.png" , 0.2f ));
		}
		walkList = walkListS;
		kickList = kickListS;
		dieList  = dieListS;

		spriteChanging = new SpriteChanging(size);
		spriteChanging.setList(walkList);
		spriteChanging.pause();
	}




}
