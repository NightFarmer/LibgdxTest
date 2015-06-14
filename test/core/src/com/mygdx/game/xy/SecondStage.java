package com.mygdx.game.xy;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.xy.Rock.SpriteState;

public class SecondStage extends XYStage{

	private MainActor actor;

	public SecondStage(CameraHelper cameraHelper, Vector2 actorPosition, SpriteState actorState) {
		super(cameraHelper);
		Assets.instance.init(Constants.TEXTURE_ATLAS_FILE_LIST_LONGGONG);
		ChuanSongZhen chuansong1 = new ChuanSongZhen();
		chuansong1.setPosition(18, 13);
		addActor(chuansong1);
		
		actor = new MainActor();
		actor.setPosition(actorPosition.x, actorPosition.y);
		actor.setState(SpriteState.STAND_DOWN);
		addActor(actor);
		cameraHelper.setTarget(actor.getGameObject());
		
		actor.addImpactListener(new ImpactListener(chuansong1) {
			
			@Override
			public void onImpact(CanImpact obj) {
				startStage(FirstStage.class, FirstStage.getDefaultPosition2(), SpriteState.STAND_LEFT);
			}
		});
	}

	@Override
	public String getMapFileName() {
		return "images/map/dongfu.jpg";
	}

	@Override
	public MainActor getZJ() {
		return actor;
	}

	public static Vector2 getDefaultPosition() {
		return new Vector2(18, 13);
	}

	@Override
	public void setActorPositon(Vector2 position) {
		actor.setPosition(position.x, position.y);
	}

}
