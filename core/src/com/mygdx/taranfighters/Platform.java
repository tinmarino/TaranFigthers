/*
		Just a moving body, following a list of vectro2 world postion in a time.
*/
package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



public class Platform{
	Body body;
	ArrayList<Vector2> positionList;
	int index;
	float totalTime;
	float currentTime;
	int sz;



	public Platform(World world, Vector2 size, Vector2 initialPosition, Vector2 finalPosition, float time){
		super();
		// fill positionList 
		this.positionList = new ArrayList<Vector2>();
		this.positionList.add(initialPosition);
		this.positionList.add(finalPosition);
		this.positionList.add(initialPosition);
		this.sz = positionList.size();
		this.index = 0;

		// Fill totalTime
		totalTime = time;

		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(initialPosition); // in meter position of the center 
			
		// BodyShape 
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(0.5f * size.x, 0.5f * size.y);

		// BodyFixture 
		FixtureDef bodyFix = new FixtureDef();
		bodyFix.shape = bodyShape;
		bodyFix.restitution = 0f;
		bodyFix.friction = 0.6f;
		
		// Create Body 
		this.body = world.createBody(bodyDef);
		this.body.createFixture(bodyFix);
	}
	

	public void draw(SpriteBatch batch, float delta){
		// Restart ?
		currentTime += delta;
		if (currentTime >= totalTime * (sz-1) / sz){
			currentTime = 0;
			index = 0;
		}
		float normalized = currentTime / (totalTime / sz);
		index  = (int) normalized;
		float offset = normalized % 1; 

		// Baricenter
		G.log("offset " + offset + " " + index);
		Vector2 position = positionList.get(index).cpy();
		Vector2 baricenter = positionList.get(index + 1).cpy();
		baricenter.sub(positionList.get(index));
		baricenter.scl(offset);
		position.add(baricenter);
		G.log("PLatform POsition " + position + "  " +  offset);

		body.setTransform(position, 0);
		
		if (index >= positionList.size()-1){
			index = 0;
		}
	}


}
