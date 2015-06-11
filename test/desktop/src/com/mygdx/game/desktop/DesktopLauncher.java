package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.LeanDev1;
import com.mygdx.game.bunny.CanyonBunnyMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "≤‚ ‘";
		config.width = 800;
		config.height = 480;
		config.width = 100;
		config.height = 180;
//		new LwjglApplication(new MyGdxGame(), config);
//		new LwjglApplication(new LeanDev1(), config);
		new LwjglApplication(new CanyonBunnyMain(), config);
	}
}
