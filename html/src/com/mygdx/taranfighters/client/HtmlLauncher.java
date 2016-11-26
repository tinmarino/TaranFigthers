package com.mygdx.taranfighters.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.taranfighters.MainGdx;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480* 2, 320 * 2);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new MainGdx();
        }
}
