package com.mygdx.game.bunny;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Rock extends AbstractGameObject {
	private int length;
	private Animation p1Animation;
	private float stateTime;
	private static ArrayList<AtlasRegion> p1 =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_NPC, Constants.NPC_LONGWANG, "00",
			Constants.TNPC_LONGWANG_FRAM_NUM[0]);

	public Rock() {
		init();
	}

	private void init() {
		dimension.set(8, 8);
		p1Animation = new Animation(0.1f, p1.toArray(new AtlasRegion[]{}));
		stateTime = 0f;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void increaseLength(int amount) {
		setLength(length + amount);
	}
	
	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = p1Animation.getKeyFrame(stateTime, true);
		Sprite sprite = new Sprite(currentFrame);
		sprite.setSize(dimension.x, dimension.y);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(position.x-sprite.getOriginX(), position.y);
		sprite.draw(batch);
	}
}