package com.mygdx.taranfighters;

import java.util.ArrayList;

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


	public static float getTime(ArrayList<TextureTime> textureTimeList){
		if (textureTimeList == null){return 0;}
		float time = 0;
		for (TextureTime i : textureTimeList){
			time += i.time;
		}
		return time;
	}
}
