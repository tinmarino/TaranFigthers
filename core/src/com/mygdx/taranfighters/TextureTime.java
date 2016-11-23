package com.mygdx.taranfighters;

import com.badlogic.gdx.graphics.Texture;

public class TextureTime{
	Texture texture; 
	float   time; 

	public TextureTime(Texture texture, float time){
		this.texture = texture;
		this.time = time;
	}

	public TextureTime(String string, float time){
		this.texture = new Texture(string);
		this.time = time;
	}
}
