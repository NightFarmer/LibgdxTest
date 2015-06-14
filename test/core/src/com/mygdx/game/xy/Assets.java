package com.mygdx.game.xy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.jiami.CipherTextureLoader;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

public class Assets implements Disposable, AssetErrorListener{

	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
//	private java.util.Map<String, TextureAtlas> textureAtlasMap = new HashMap<String, TextureAtlas>();

	
	private Assets() {
	}
	
	public void init(String[] files) {
		this.assetManager = new AssetManager();
		assetManager.setErrorListener(this);
		assetManager.setLoader(Texture.class, ".png", new CipherTextureLoader(new InternalFileHandleResolver()));
		for (String string : files) {
			assetManager.load(string, TextureAtlas.class);
		}
		assetManager.finishLoading();
		Gdx.app.log(TAG, "# of assets loaded: "+ assetManager.getAssetNames().size);
		for (String name : assetManager.getAssetNames()) {
			Gdx.app.log(TAG, "asset: "+name);
		}
		
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

	/**
	 * @param file 纹理集文件
	 * @param folder 子文件名
	 * @param direction 方向 
	 * @param framCount 帧数
	 * @return
	 */
	public ArrayList<AtlasRegion> getList(String file, String folder, String preStr, int framCount){
		TextureAtlas atlas = assetManager.get(file);
		for (Texture texture : atlas.getTextures()) {
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		ArrayList<AtlasRegion> findRegion = new ArrayList<TextureAtlas.AtlasRegion>();
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 0; i < framCount; i++) {
			AtlasRegion findRegion2 = atlas.findRegion(folder+"/"+preStr+"0"+df.format(i));
			findRegion.add(findRegion2);
		}
		return findRegion;
	}
	
	
}
