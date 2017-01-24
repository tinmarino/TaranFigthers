package com.mygdx.taranfighters;

import java.util.ArrayList;

// locked, open, finished, prefered
public class PreferenceSaved{
	ArrayList<ArrayList<CharLevelState>> charLevelList = new ArrayList<ArrayList<CharLevelState>>();



	public PreferenceSaved(){
		for (G.CHAR charKey : G.CHAR.values()){
			ArrayList<CharLevelState> charList = new ArrayList<CharLevelState>();

			for (G.LEVEL levelKey : G.LEVEL.values()){
				charList.add((int) levelKey.ordinal(), CharLevelState.LOCKED);
			}

			this.charLevelList.add(charKey.ordinal(), charList);
		}
		this.charLevelList.get(G.CHAR.JAK.ordinal()).set(G.LEVEL.L1.ordinal(), CharLevelState.OPEN);
	}

	public enum CharLevelState{
		LOCKED,
		OPEN,
		FINISHED,
		PREFERED
	}
}

