package com.mygdx.taranfighters;

public class PlatformScreen extends TaranScreen{


	@Override
	public void show(){
		super.show();

		G.log("PlatformScreen will create level)");
		//level = new Level("map/platformer1.tmx", world);
		level = new Level("map/platformer2.tmx", world);

		G.log("PlatformScreen will create Iul");
		char1 = new Jak(world);
	}

	@Override 
	public void render(float delta){
		super.render(delta);
	}



}
