package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteChanging extends Sprite{
	private ArrayList<TextureTime> currentList = new ArrayList<TextureTime>();
	public int currentIndex = 0;
	public float time = 0;
	public boolean onPause = false;
	

	SpriteChanging(float size){
		super(new Texture("iul/iul_walk1.png"));
		this.setSize(2 * size * G.world2pixel, 2* size * G.world2pixel);
		this.setOrigin(this.getWidth()/2 * G.world2pixel/2, this.getHeight()/2); // to resize and rotate around the origin, here center of the sprite
	}

	SpriteChanging(String string){
		super(new Texture(string));
	}

	public void pause(){
		this.onPause = true;
	}

	public void play(){
		this.onPause = false;
	}

	public void draw(SpriteBatch batch, float delta){
		this.draw(batch);
		if (!this.onPause){
			this.time += delta;
			if ( this.time > this.currentList.get(this.currentIndex).time){
				this.time = 0;
				if (currentIndex >= this.currentList.size()-1){
					this.currentIndex = 0;
				}
				else{
					this.currentIndex += 1;
				}
				this.setTexture(this.currentList.get(this.currentIndex).texture);
			}
		}
	}

	public void setList(ArrayList<TextureTime> list){
		this.play();
		this.currentIndex = 0;
		this.currentList = list;
		this.setTexture(this.currentList.get(this.currentIndex).texture);
	}







}
