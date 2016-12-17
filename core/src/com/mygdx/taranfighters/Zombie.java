package com.mygdx.taranfighters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Zombie extends Character {
	int playerNumber = 10;

	public Zombie(World world) {
		super(world);
		init();
	}


	public void init(){
	}

	public static void addZombie(World world, Vector2 position){
	}

}
