package com.mygdx.taranfighters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Iul extends Character{

	public Iul(){
		super();
		show();
	}

	public void show(){
		initSpriteChanging();
	}

	@Override
	public void draw(SpriteBatch batch, float delta){
		
		spriteChanging.draw(batch, delta);

	}


	public void initSpriteChanging(){
		spriteChanging.currentList.add(new TextureTime( new Texture("iul/iul_walk1.png") , 0.3f ));
		spriteChanging.currentList.add(new TextureTime( new Texture("iul/iul_walk2.png") , 0.3f ));
		spriteChanging.currentList.add(new TextureTime( new Texture("iul/iul_walk3.png") , 0.3f ));
	}


//
//
//
}
