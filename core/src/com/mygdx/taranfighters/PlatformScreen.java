package com.mygdx.taranfighters;

public class PlatformScreen extends TaranScreen{


	@Override
	public void show(){
		super.show();
		level = new Level("map/platformer1.tmx", world);
		char1 = new Iul(world);
	}

	@Override 
	public void render(float delta){
		super.render(delta);
	}



}
