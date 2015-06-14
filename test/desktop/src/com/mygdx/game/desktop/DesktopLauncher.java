package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.mygdx.game.bunny.CanyonBunnyMain;
import com.mygdx.game.xy.XYGame;

public class DesktopLauncher {

	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = false;

	public static void main(String[] arg) {

		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugOutline;
//			TexturePacker.processIfModified(settings, "assets-raw/images/npc",
//					"../android/assets/images", "npc.pack");
//			TexturePacker.processIfModified(settings, "assets-raw/images/juese",
//					"../android/assets/images", "juese.pack");
//			TexturePacker.process(settings, "assets-raw/images/texiao",
//					"../android/assets/images", "texiao.pack");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "≤‚ ‘";
		config.height = 480;
		config.width = 480 *16/9;
//		config.width = 100;
//		config.height = 180;
		// new LwjglApplication(new MyGdxGame(), config);
		// new LwjglApplication(new LeanDev1(), config);
		new LwjglApplication(new XYGame(), config);
	}
}
