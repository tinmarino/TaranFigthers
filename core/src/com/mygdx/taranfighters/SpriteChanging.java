package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteChanging extends Sprite{
	private ArrayList<TextureTime> currentList = new ArrayList<TextureTime>();
	public int currentIndex = 0;
	public float time = 0;
	

	SpriteChanging(float size){
		super(new Texture("iul/iul_walk1.png"));
		this.setSize(2 * size * G.world2pixel, 2* size * G.world2pixel);
		this.setOrigin(this.getWidth()/2 * G.world2pixel/2, this.getHeight()/2); // to resize and rotate around the origin, here center of the sprite
	}

	SpriteChanging(String string){
		super(new Texture(string));

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
		}
	}

	public void setList(ArrayList<TextureTime> list){
		this.currentIndex = 0;
		this.currentList = list;
	}







}
