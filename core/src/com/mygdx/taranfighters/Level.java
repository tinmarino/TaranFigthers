package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.taranfighters.Levels.LevelPlatform1;
import com.mygdx.taranfighters.Levels.LevelPlatform2;


public class Level{
	public ArrayList<Character> charList = new ArrayList<Character>();
	public ArrayList<Platform> platformList = new ArrayList<Platform>();

	// Disposables
	World world;
    OrthogonalTiledMapRenderer tiledMapRenderer;
	Box2DDebugRenderer debugRenderer;
    TiledMap tiledMap;
	Music music;


	public static Level createLevel(String string, World world){
		if (string == "platformer2"){
			return new LevelPlatform2(world);
		}
		if (string == "platformer1"){
			return new LevelPlatform1(world);
		}
		return null;
	}

	public void draw(SpriteBatch batch, float delta){
		for (Platform platform: platformList){
			platform.draw(batch, delta);
		}
		for (Character character : charList){
			character.draw(batch, delta);
		}
	}

	public Level(World world){
	}

	public Level(String mapString, World world){
		this.world = world;

		// Debug Renderer
		debugRenderer = new Box2DDebugRenderer();

		// Load tilemap 
        tiledMap = new TmxMapLoader().load(mapString);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		MapProperties prop = tiledMap.getProperties();
		int tilePixelWidth = prop.get("tilewidth", Integer.class);

		// Tilemap -> Box2d static body
		MapBodyBuilder.buildShapes(tiledMap, tilePixelWidth, world);
		G.log("Level I loaded map " + mapString + " with tileSize = " + tilePixelWidth);
		G.world2pixel = tilePixelWidth;
		
		// Music (to dispose)
		
		//music = Gdx.audio.newMusic(Gdx.files.internal("music/i_will_survice_full.mp3"));
		//music.play();
		//G.midiPlayer.open("music/brajta_orchestra.mid");
    	//G.midiPlayer.setVolume(0.5f);
		//G.midiPlayer.play();
		
		// createRat for debug
		makeRat(3, 2);
		makeRat(6, 3);


	}


	public void makeRat(float x, float y){
		Rat rat = new Rat(world);
		rat.setPosition(x, y);
		charList.add(rat);
	}



	public void dispose(){
		G.log("Level dispose");
		G.disposeW((Disposable) music);
		// G.disposeW(debugRenderer);
		G.disposeW((Disposable) tiledMap);
		G.disposeW((Disposable) tiledMapRenderer);
	}


}
