package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.bunny.CanyonBunnyMain;
import com.mygdx.game.xy.XYGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		config.useGLSurfaceView20API18 = true;
//		initialize(new MyGdxGame(), config);
//		initialize(new CanyonBunnyMain(), config);
		initialize(new XYGame(), config);
		
	}
}
