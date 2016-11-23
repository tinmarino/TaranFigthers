package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteChanging{
	public ArrayList<TextureTime> currentList = new ArrayList<TextureTime>();
	public int currentIndex = 0;
	public Sprite sprite = new Sprite();

	SpriteChanging(){
	}


	public void draw(Batch batch, float delta){
		sprite.draw(batch);
	}




}
