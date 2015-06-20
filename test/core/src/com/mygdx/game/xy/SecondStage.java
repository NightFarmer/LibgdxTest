package com.mygdx.game.xy;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.xy.JueSe.SpriteState;

public class SecondStage extends BaseStage{

	private MainActor actor;

	public SecondStage(CameraHelper cameraHelper, Vector2 actorPosition, SpriteState actorState) {
		super(cameraHelper);
		long t1 = System.nanoTime();
		ChuanSongZhen chuansong1 = new ChuanSongZhen();
		chuansong1.setPosition(18, 13);
		addActor(chuansong1);
		
		actor = new MainActor();
		actor.setPosition(actorPosition.x, actorPosition.y);
		actor.setState(SpriteState.STAND_DOWN);
		addActor(actor);
		cameraHelper.setTarget(actor.getGameObject());
		long t6 = System.nanoTime();
		System.out.println("¹²ºÄÊ±£º"+(t6-t1));
		actor.addImpactListener(new ImpactListener(chuansong1) {
			
			@Override
			public void onImpact(CanImpact obj) {
				startStage(FirstStage.class, FirstStage.getDefaultPosition2(), SpriteState.STAND_LEFT);
			}
		});
	}

	@Override
	public String getMapFileName() {
		return Constants.TEXTURE_FILE_MAP_DONGFU;
	}

	@Override
	public MainActor getZJ() {
		return actor;
	}

	public static Vector2 getDefaultPosition() {
		return new Vector2(18, 13);
	}

	@Override
	public String[] getStageFileList() {
		return Constants.TEXTURE_ATLAS_FILE_LIST_DONGFU;
	}

}
