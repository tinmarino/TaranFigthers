package com.mygdx.taranfighters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public class G{
   	public static float timestep = 1/60f;
	public static int velocity_iterations = 8;
	public static int position_iterations = 3;
	public static float world2pixel = 128;
	public static Game game;
	public static boolean debug = true;




	public static boolean isBodyContact(Body body, Contact contact){
		if (contact.getFixtureA().getBody() == body
		  ||contact.getFixtureB().getBody() == body){
			return true;
		}

		return false;
	}


}

