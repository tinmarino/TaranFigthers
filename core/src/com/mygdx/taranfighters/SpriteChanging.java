package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteChanging extends Sprite{
	public ArrayList<TextureTime> currentList = new ArrayList<TextureTime>();
	public int currentIndex = 0;
	public float time = 0;

	SpriteChanging(){
		super(new Texture("iul/iul_walk1.png"));
		this.setSize(128 , 128);
		this.setPosition(0,0);
		this.setTexture(new Texture("iul/iul_walk1.png"));
	}


	public void draw(SpriteBatch batch, float delta){
		this.draw(batch);
		time += delta;
		if ( time > currentList.get(currentIndex).time){
			time = 0;
			if (currentIndex >= currentList.size()-1){
				currentIndex = 0;
			}
			else{
				currentIndex += 1;
			}
			this.setTexture(currentList.get(currentIndex).texture);
			Gdx.app.log("changing", "" + currentIndex +","+ delta + "time" + time);
		}
	}







}
