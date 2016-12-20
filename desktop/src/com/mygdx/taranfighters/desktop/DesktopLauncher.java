package com.mygdx.taranfighters.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.MainGdx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480 *1;
		config.height = 320 *1;
		config.title = "Taran Fighters";
		config.addIcon("bck/taran_icon32.png", Files.FileType.Internal) ;
		config.addIcon("bck/taran_icon16.png", Files.FileType.Internal) ;
		
		G.midiPlayer = new DesktopMidiPlayer();
		new LwjglApplication(new MainGdx(), config);
	}
}
