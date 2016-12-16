package com.mygdx.taranfighters;

public class PlatformScreen extends TaranScreen{
	G.CHAR charEnum;
	G.LEVEL levelEnum;



	public PlatformScreen(G.CHAR charEnum, G.LEVEL levelEnum) {
		this.charEnum = charEnum; 
		this.levelEnum = levelEnum;
	}

	@Override
	public void show(){
		super.show();

		G.log("PlatformScreen will create level " + levelEnum);

		switch (levelEnum){
			case L1:
				level = Level.createLevel("platformer1", world);
				break;
			case L2:
				level = Level.createLevel("platformer2", world);
				break;
			default:
				level = Level.createLevel("platformer1", world);
		}

		G.log("PlatformScreen will create Character " + charEnum);
		switch (charEnum){
			case JAK:
				char1 = new Jak(world);
				break;
			case ROZ:
				char1 = new Roz(world);
				break;
			case IUL:
				char1 = new Iul(world);
				break;
			case FIX:
				char1 = new Fix(world);
				break;
			default:
				char1 = new Iul(world);
		}
	}

	@Override 
	public void render(float delta){
		super.render(delta);
	}



}
