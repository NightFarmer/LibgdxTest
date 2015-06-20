package com.mygdx.game.xy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ChuanSongZhen extends Actor implements CanImpact{
	private   ArrayList<AtlasRegion> textureList =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_TEXIAO, "chuansongzhen", "10",
			66);
	private   Animation animation = new Animation(0.025f, textureList.subList(12, 66).toArray(new AtlasRegion[]{}));
	private float stateTime;
	
	public ChuanSongZhen() {
		stateTime = 0f;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		Sprite sprite = new Sprite(currentFrame);
		sprite.setSize(currentFrame.getRegionWidth()/12, currentFrame.getRegionHeight()/12);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		setOrigin(sprite.getOriginX(), sprite.getOriginY());
		setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(getX()-getOriginX(), getY()-getOriginY()/5*4);
		sprite.draw(batch);
	}

	@Override
	public float getRadius() {
		return 1;
	}

	@Override
	public Vector2 getCenter() {
		return new Vector2(getX(), getY());
	}
}
