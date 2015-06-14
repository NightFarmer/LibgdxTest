package com.mygdx.game.jiami;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
/**
 * @author GhuaZo
 * 
 * QQ：137336521
 * 
 * 声明一下：
 * 		本DEMO来自LIBGDX开发交流群 ：145353767，转载切勿删除本提示
 */
public class CipherTextureLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter>{

	public CipherTextureLoader(FileHandleResolver resolver) {
		super(resolver);
	}


	static public class TextureLoaderInfo {
		String filename;
		TextureData data;
		Texture texture;
	};
	TextureLoaderInfo info = new TextureLoaderInfo();

	
	public void loadAsync (AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
		info.filename = fileName;
		if (parameter == null || parameter.textureData == null) {
			Pixmap pixmap = null;
			Format format = null;
			boolean genMipMaps = false;
			info.texture = null;

			if (parameter != null) {
				format = parameter.format;
				genMipMaps = parameter.genMipMaps;
				info.texture = parameter.texture;
			}

				
			pixmap = this.decipherPixmap(file);
			info.data = new FileTextureData(file, pixmap, format, genMipMaps);
			
		} else {
			info.data = parameter.textureData;
			if (!info.data.isPrepared()) info.data.prepare();
			info.texture = parameter.texture;
		}
	}

	public Texture loadSync (AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
		if (info == null)
			return null;
		Texture texture = info.texture;
		if (texture != null) {
			texture.load(info.data);
		} else {
			texture = new Texture(info.data);
		}
		if (parameter != null) {
			texture.setFilter(parameter.minFilter, parameter.magFilter);
			texture.setWrap(parameter.wrapU, parameter.wrapV);
		}
		return texture;
	}

	private Pixmap decipherPixmap(FileHandle file){
		Gdx2DPixmap pixmap = null ; 
		short ff = (short)-128 ;
		try {
			byte[] bytes = file.readBytes();
			for(int i=0 ; i<bytes.length ; i++){
				bytes[i] = (byte) (bytes[i] ^ ff);
			}
			pixmap = new Gdx2DPixmap(bytes, 0, bytes.length, 0);
		} catch (Exception e) {
			throw new GdxRuntimeException("Couldn't load file: " + file, e);
		}
		return new Pixmap(pixmap) ; 
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, TextureLoader.TextureParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}

	

	

