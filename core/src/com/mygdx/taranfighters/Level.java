package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Level{
	ArrayList<Body> bodyList = new ArrayList<Body>();
	World world;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    TiledMap tiledMap;


	public Level(String map, World world){
		this.world = world;
        tiledMap = new TmxMapLoader().load("map/castleArena1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		MapBodyBuilder.buildShapes(tiledMap, 128, world);
		debugBodies();
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

}
