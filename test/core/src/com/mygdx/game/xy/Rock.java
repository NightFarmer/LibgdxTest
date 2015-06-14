package com.mygdx.game.xy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Rock extends AbstractGameObject {
	
	private Animation currentAnimation;
	private float stateTime;
	
	private static ArrayList<AtlasRegion> r_rightdown =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-00",
			Constants.JUESE_SHUSHENG_FRAM_NUM[0]);
	private static ArrayList<AtlasRegion> r_leftdown =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-01",
			Constants.JUESE_SHUSHENG_FRAM_NUM[1]);
	private static ArrayList<AtlasRegion> r_leftup =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-02",
			Constants.JUESE_SHUSHENG_FRAM_NUM[2]);
	private static ArrayList<AtlasRegion> r_rightup =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-03",
			Constants.JUESE_SHUSHENG_FRAM_NUM[3]);
	private static ArrayList<AtlasRegion> r_down =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-04",
			Constants.JUESE_SHUSHENG_FRAM_NUM[4]);
	private static ArrayList<AtlasRegion> r_left =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-05",
			Constants.JUESE_SHUSHENG_FRAM_NUM[5]);
	private static ArrayList<AtlasRegion> r_up =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-06",
			Constants.JUESE_SHUSHENG_FRAM_NUM[6]);
	private static ArrayList<AtlasRegion> r_right =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0168-122d197f-07",
			Constants.JUESE_SHUSHENG_FRAM_NUM[7]);
	
	private static ArrayList<AtlasRegion> s_right =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0573-3878205a-03",
			Constants.JUESE_SHUSHENG_FRAM_NUM[8]);
	private static ArrayList<AtlasRegion> s_down =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0573-3878205a-00",
			Constants.JUESE_SHUSHENG_FRAM_NUM[9]);
	private static ArrayList<AtlasRegion> s_left =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0573-3878205a-01",
			Constants.JUESE_SHUSHENG_FRAM_NUM[10]);
	private static ArrayList<AtlasRegion> s_up =  Assets.instance.getList(
			Constants.TEXTURE_ATLAS_FILE_JUESE, Constants.JUESE_SHUSHENG, "0573-3878205a-02",
			Constants.JUESE_SHUSHENG_FRAM_NUM[11]);

	private Animation a_r_rightdown;
	private Animation a_r_leftdown;
	private Animation a_r_leftup;
	private Animation a_r_rightup;
	private Animation a_r_left;
	private Animation a_r_down;
	private Animation a_r_up;
	private Animation a_r_right;
	private Animation a_s_right;
	private Animation a_s_down;
	private Animation a_s_left;
	private Animation a_s_up;
	private SpriteState state;
	
	public static final Rock instance = new Rock();
	
	private Rock() {
		init();
	}

	private void init() {
		dimension.set(7, 10);
		a_r_rightdown = new Animation(0.1f, r_rightdown.toArray(new AtlasRegion[]{}));
		a_r_leftdown = new Animation(0.1f, r_leftdown.toArray(new AtlasRegion[]{}));
		a_r_leftup = new Animation(0.1f, r_leftup.toArray(new AtlasRegion[]{}));
		a_r_rightup = new Animation(0.1f, r_rightup.toArray(new AtlasRegion[]{}));
		a_r_down = new Animation(0.1f, r_down.toArray(new AtlasRegion[]{}));
		a_r_left = new Animation(0.1f, r_left.toArray(new AtlasRegion[]{}));
		a_r_up = new Animation(0.1f, r_up.toArray(new AtlasRegion[]{}));
		a_r_right = new Animation(0.1f, r_right.toArray(new AtlasRegion[]{}));
		a_s_right = new Animation(0.1f, s_right.toArray(new AtlasRegion[]{}));
		a_s_down = new Animation(0.1f, s_down.toArray(new AtlasRegion[]{}));
		a_s_left = new Animation(0.1f, s_left.toArray(new AtlasRegion[]{}));
		a_s_up = new Animation(0.1f, s_up.toArray(new AtlasRegion[]{}));
		stateTime = 0f;
		
		currentAnimation = a_s_down;
	}

	@Override
	public void update(float deltaTime) {
		
	}

	public SpriteState getState(){
		return state;
	}
	
	public void setState(SpriteState state){
		this.state = state;
		switch (state) {
		case STAND_UP:
			this.currentAnimation = a_s_up;
			break;
		case STAND_RIGHT:
			this.currentAnimation = a_s_right;
			break;
		case STAND_DOWN:
			this.currentAnimation = a_s_down;
			break;
		case STAND_LEFT:
			this.currentAnimation = a_s_left;
			break;
		case RUN_DOWN:
			this.currentAnimation = a_r_down;
			break;
		case RUN_LEFT:
			this.currentAnimation = a_r_left;
			break;
		case RUN_RIGHT:
			this.currentAnimation = a_r_right;
			break;
		case RUN_UP:
			this.currentAnimation = a_r_up;
			break;
		case RUN_LEFTDOWN:
			this.currentAnimation = a_r_leftdown;
			break;
		case RUN_LEFTUP:
			this.currentAnimation = a_r_leftup;
			break;
		case RUN_RIGHTDOWN:
			this.currentAnimation = a_r_rightdown;
			break;
		case RUN_RIGHTUP:
			this.currentAnimation = a_r_rightup;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void render(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		Sprite sprite = new Sprite(currentFrame);
		sprite.setSize(currentFrame.getRegionWidth()/8, currentFrame.getRegionHeight()/8);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(position.x-sprite.getOriginX(), position.y-sprite.getOriginY()/5);
		sprite.draw(batch);
		origin.x = sprite.getX();
		origin.y = sprite.getY();
	}
	
	public Vector2 getCenter(){
		return origin;
	}
	
	public enum SpriteState{
		STAND_UP,
		STAND_DOWN,
		STAND_LEFT,
		STAND_RIGHT,
		RUN_UP,
		RUN_RIGHTUP,
		RUN_RIGHT,
		RUN_RIGHTDOWN,
		RUN_DOWN,
		RUN_LEFTDOWN,
		RUN_LEFT,
		RUN_LEFTUP,
	}
}