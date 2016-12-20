package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.taranfighters.Levels.LevelFreefall;
import com.mygdx.taranfighters.Levels.LevelPlage;
import com.mygdx.taranfighters.Levels.LevelPlatform1;
import com.mygdx.taranfighters.Levels.LevelPlatform2;
import com.mygdx.taranfighters.Levels.LevelSalon;
import com.mygdx.taranfighters.Levels.LevelSinai;


public class Level implements Disposable{
	// To set  + function isLevelFinished;
	public Vector2 initialPosition = new Vector2(2, 5);
	public G.LEVEL levelEnum = G.LEVEL.L1;
	
	// Used 
	public ArrayList<Character> charList = new ArrayList<Character>();
	public ArrayList<Platform> platformList = new ArrayList<Platform>();
	public ArrayList<Character> deadCharacter = new ArrayList<Character>();
	public enum FINISHED{GAMEOVER, VICTORY, NO}
	public FINISHED finishedEnum = FINISHED.NO;

	// Disposables
	World world;
    OrthogonalTiledMapRenderer tiledMapRenderer;
	Box2DDebugRenderer debugRenderer;
    TiledMap tiledMap;
	public Music music;
	public Music music_quote;
	public Music music_num;


	public static Level createLevel(G.LEVEL levelEnum, World world){
		Level level;
		switch (levelEnum){
			case L1:
				level = new LevelPlatform1(world);
				level.music_num = G.music("music/num/1one.mp3");
				return level;
			case L2:
				level = new LevelPlatform2(world);
				level.music_num = G.music("music/num/2two.mp3");
				return level;
			case L3:
				level = new LevelSalon(world);
				level.music_num = G.music("music/num/3three.mp3");
				return level;
			case L4:
				level = new LevelPlage(world);
				level.music_num = G.music("music/num/4four.mp3");
				return level;
			case L5:
				level = new LevelFreefall(world);
				level.music_num = G.music("music/num/5five.mp3");
				return level;
			case L6:
				level = new LevelSinai(world);
				level.music_num = G.music("music/num/6six.mp3");
				return level;
			default:
				//level = new LevelPlatform1(world);
				//level.music_num = G.music("music/num/1one.mp3");
				return null;
		}
	}

	public boolean isLevelFinished(Character char1){
		// Victory 
		boolean victory = char1.x > 10;
		if (victory){
			this.finishedEnum = FINISHED.VICTORY;
			return true;
		}
		return false;
	}

	public void draw(SpriteBatch batch, float delta){
		for (Platform platform: platformList){
			platform.draw(batch, delta);
		}
		for (Character character : charList){
			character.draw(batch, delta);
			if (character.isDead){
		 		deadCharacter.add(character);
			}
		}
		for (Character dead : deadCharacter){
			charList.remove(dead);
		}
		deadCharacter.clear();
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
	}


	// TODO use it !!
	public void makeRat(float x, float y){
		Rat rat = new Rat(world);
		rat.setPosition(x, y);
		charList.add(rat);
	}

	public Zombie makeZombie(float x, float y){
		Zombie zombie = new Zombie(world);
		zombie.setPosition(x, y);
		charList.add(zombie);
		return zombie;
	}



	public void dispose(){
		G.log("Level dispose");
		G.disposeW((Disposable) music);
		if (debugRenderer!= null){
			debugRenderer.dispose();
		}
		G.disposeW((Disposable) tiledMap);
		G.disposeW((Disposable) tiledMapRenderer);
		G.disposeW(music_num);
		G.disposeW(music_quote);
	}


}
