/*
		Just a moving body, following a list of vectro2 world postion in a time.
*/
package com.mygdx.taranfighters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	Sprite sprite;
	ArrayList<Vector2> positionList;
	int index;
	float totalTime;
	float currentTime;
	int sz;
	Vector2 size;



	public Platform(World world, Vector2 size, Vector2 initialPosition, Vector2 finalPosition, float time){
		super();
		// fill positionList 
		this.positionList = new ArrayList<Vector2>();
		this.positionList.add(initialPosition);
		this.positionList.add(finalPosition);
		this.positionList.add(initialPosition);
		this.sz = positionList.size();
		this.index = 0;
		this.size = size;

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

		// Create Sprite 
		sprite = new Sprite(createPlaform());
		sprite.setSize(size.x * G.world2pixel, size.y * G.world2pixel);
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
		Vector2 position = positionList.get(index).cpy();
		Vector2 baricenter = positionList.get(index + 1).cpy();
		baricenter.sub(positionList.get(index));
		baricenter.scl(offset);
		position.add(baricenter);

		body.setTransform(position, 0);
		sprite.setPosition((position.x - 0.5f * size.x) * G.world2pixel , (position.y - 0.5f * size.y) * G.world2pixel );
		sprite.draw(batch);
	}


	public Texture createPlaform(){
		Pixmap fullPixmap = new Pixmap(Gdx.files.internal("map/Atlas/platformer_deluxe_2space.png"));
		Pixmap middle = PixmapFactory.cutPixmap(fullPixmap, 146, 2, 70, 40);
		Pixmap left = PixmapFactory.cutPixmap(fullPixmap, 146, 74, 70, 40);
		Pixmap right = PixmapFactory.flipPixmapX(left);

		Pixmap res1 = PixmapFactory.addHPixmap(left, middle);
		Pixmap res2 = PixmapFactory.addHPixmap(res1, right);
		
		fullPixmap.dispose();
		middle.dispose();
		left.dispose();
		right.dispose();
		res1.dispose();
		return new Texture(res2);
	}

}
