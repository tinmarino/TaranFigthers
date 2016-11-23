package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Iul extends Character{
	ArrayList<TextureTime> walkList;

	public Iul(){
		super();
		
		// Walk list 
		walkList = new ArrayList<TextureTime>();
		walkList.add(new TextureTime( "iul/iul_walk1.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk2.png" , 0.3f ));
		walkList.add(new TextureTime( "iul/iul_walk3.png" , 0.3f ));

		spriteChanging.currentList = walkList;
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		spriteChanging.draw(batch, delta);
	}


	public void initSpriteChanging(){
	}


//
//
//
}
