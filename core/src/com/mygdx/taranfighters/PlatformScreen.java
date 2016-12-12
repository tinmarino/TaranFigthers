package com.mygdx.taranfighters;

public class PlatformScreen extends TaranScreen{


	@Override
	public void show(){
		super.show();

		G.log("PlatformScreen will create level)");
		level = Level.createLevel("platformer2", world);

		G.log("PlatformScreen will create Character");
		char1 = new Fix(world);
	}

	@Override 
	public void render(float delta){
		super.render(delta);
	}



}
