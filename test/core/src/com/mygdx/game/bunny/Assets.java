package com.mygdx.game.bunny;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

public class Assets implements Disposable, AssetErrorListener{

	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;

	public LongWang longWang;
	
	private Assets() {
	}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		Gdx.app.log(TAG, "# of assets loaded: "+ assetManager.getAssetNames().size);
		for (String name : assetManager.getAssetNames()) {
			Gdx.app.log(TAG, "asset: "+name);
		}
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for (Texture texture : atlas.getTextures()) {
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		longWang = new LongWang(atlas);
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" +
				asset.fileName + "'", (Exception)throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	public class LongWang {
		
		public List<AtlasRegion> findRegion;

		public LongWang(TextureAtlas atlas) {
			findRegion = new ArrayList<TextureAtlas.AtlasRegion>();
			for (int i = 0; i < 7; i++) {
				AtlasRegion findRegion2 = atlas.findRegion("longwang/1272-810e3469-0000"+i);
				findRegion.add(findRegion2);
			}
		}
	}
}
