/*
 *  I did not use g2d.Animation because too late + I want textures with a different time for each one (maybe);
 */
package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteChanging extends Sprite{
	public ArrayList<TextureTime> currentList = new ArrayList<TextureTime>();
	public int currentIndex = 0;
	public float time = 0;
	public boolean onPause = false;
	


	SpriteChanging(String string){
		super(new Texture(string));
	}

	SpriteChanging(Texture texture){
		super(texture);
	}

	SpriteChanging(Texture texture, float size){
		super(texture);
		scaleM(size);
	}

	// In meters
	public void scaleM(float size){
		this.setSize(2 * size * G.world2pixel, 2* size * G.world2pixel);
		this.setOrigin(this.getWidth()/2 * G.world2pixel/2, this.getHeight()/2); 
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
