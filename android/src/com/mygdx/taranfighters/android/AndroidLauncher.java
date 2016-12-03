package com.mygdx.taranfighters.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.taranfighters.G;
import com.mygdx.taranfighters.MainGdx;

import android.os.Bundle;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//G.midiPlayer = new AndroidMidiPlayer(this);
		G.midiPlayer = null;
		initialize(new MainGdx(), config);
	}
}
