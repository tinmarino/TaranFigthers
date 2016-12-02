package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Level{
	ArrayList<Character> charList = new ArrayList<Character>();
	World world;
    OrthogonalTiledMapRenderer tiledMapRenderer;
	Box2DDebugRenderer debugRenderer;
    TiledMap tiledMap;
	Music music;



	public void draw(SpriteBatch batch, float delta){
		for (Character character : charList){
			character.draw(batch, delta);
		}
	}


	public Level(String mapString, World world){
		this.world = world;

		// Debug Renderer
		debugRenderer = new Box2DDebugRenderer();

		// Load tilemap 
        tiledMap = new TmxMapLoader().load(mapString);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		// Tilemap -> Box2d static body
		MapBodyBuilder.buildShapes(tiledMap, 128, world);
		
		// Music (to dispose)
		music = Gdx.audio.newMusic(Gdx.files.internal("music/i_will_survive_full.mp3"));
		music.play();
		
		// createRat for debug
		makeRat(3, 2);
		makeRat(6, 3);
	}


	public void makeRat(float x, float y){
		Rat rat = new Rat(world);
		rat.setPosition(x, y);
		charList.add(rat);
	}


	public void makeMovingPlatform(Vector2 initialPosition, Vector2 finalPosition, float time){
	}

	public void debugBodies(){
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(-0.5f, -1f); // in meter position of the center 
			
		// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(1000, 1);
		

		// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0f;
		bodyFix.friction = 0;
		
		// Create Body 
		Body body = world.createBody(bodyDef);
		body.createFixture(bodyFix);
		
	}


	public void dispose(){
		G.disposeW(music);
	}

}
