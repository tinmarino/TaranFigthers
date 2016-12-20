package com.mygdx.taranfighters;

import java.util.ArrayList;

// locked, open, finished
public class PreferenceSaved{
	ArrayList<ArrayList<String>> charLevelList = new ArrayList<ArrayList<String>>();



	public PreferenceSaved(){
		for (G.CHAR charKey : G.CHAR.values()){
			ArrayList<String> charList = new ArrayList<String>();

			for (G.LEVEL levelKey : G.LEVEL.values()){
				charList.add((int) levelKey.ordinal(), "locked");
			}

			this.charLevelList.add(charKey.ordinal(), charList);
		}
		this.charLevelList.get(G.CHAR.JAK.ordinal()).set(G.LEVEL.L1.ordinal(), "open");
	}
}
