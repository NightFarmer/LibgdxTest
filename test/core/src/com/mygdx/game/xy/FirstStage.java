package com.mygdx.game.xy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.xy.Rock.SpriteState;


public class FirstStage extends XYStage {

	private MainActor actor;

	public FirstStage(CameraHelper cameraHelper, Vector2 actorPosition, SpriteState actorState) {
		super(cameraHelper);
		Assets.instance.init(Constants.TEXTURE_ATLAS_FILE_LIST_LONGGONG);
		ChuanSongZhen chuansong1 = new ChuanSongZhen();
		chuansong1.setPosition(10, 75);
		addActor(chuansong1);
		ChuanSongZhen chuansong2 = new ChuanSongZhen();
		chuansong2.setPosition(40, 82);
		addActor(chuansong2);
		actor = new MainActor();
		actor.setPosition(actorPosition.x, actorPosition.y);
		actor.setState(SpriteState.STAND_DOWN);
		addActor(actor);
		cameraHelper.setTarget(actor.getGameObject());
		
		actor.addImpactListener(new ImpactListener(chuansong1) {
			
			@Override
			public void onImpact(CanImpact obj) {
				
			}
		});
		
		actor.addImpactListener(new ImpactListener(chuansong2) {
			
			@Override
			public void onImpact(CanImpact obj) {
//				dispose();
				startStage(SecondStage.class, SecondStage.getDefaultPosition(), SpriteState.STAND_UP);
			}
		});
	}

	@Override
	public String getMapFileName() {
		return "images/map/fangcunshan.jpg";
	}

	@Override
	public MainActor getZJ() {
		return actor;
	}
	
	@Override
	public void dispose() {
//		Assets.instance.dispose();
		super.dispose();
	}

	public static Vector2 getDefaultPosition() {
		return new Vector2(10, 75);
	}
	
	public static Vector2 getDefaultPosition2() {
		return new Vector2(40, 82);
	}

	@Override
	public void setActorPositon(Vector2 position) {
		actor.setPosition(position.x, position.y);
	}
}
