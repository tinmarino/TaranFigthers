package com.mygdx.taranfighters.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.MainGdx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480 *2;
		config.height = 320 *2;
		G.midiPlayer = new DesktopMidiPlayer();
		new LwjglApplication(new MainGdx(), config);
	}
}
