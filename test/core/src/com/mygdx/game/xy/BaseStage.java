package com.mygdx.game.xy;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.xy.JueSe.SpriteState;

public abstract class BaseStage extends Stage{
	public CameraHelper cameraHelper;
	private Image ditu;

	public void setCameraHelper(CameraHelper cameraHelper){
		this.cameraHelper = cameraHelper;
		cameraHelper.setStage(this);
	}
	
	public BaseStage(CameraHelper cameraHelper) {
		super();
		setCameraHelper(cameraHelper);
		getCamera().viewportWidth = Constants.VIEWPORT_WIDTH;
		getCamera().viewportHeight = Constants.VIEWPORT_HEIGHT;
		getCamera().position.x = 0;
		getCamera().position.y = 0;
		Assets.instance.init(getStageFileList());
		initMap();
	}

	private void initMap() {
		Texture dituTexture = Assets.instance.getTexture(getMapFileName());
//		Texture dituTexture = new Texture(getMapFileName());
//		dituTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ditu = new Image(dituTexture);
		ditu.setPosition(0, 0);
		ditu.setSize(ditu.getWidth()/8, ditu.getHeight()/8);
		addActor(ditu);
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				getZJ().target = new Vector2(x, y);
				return true;
			}
		});
	}
	
	public abstract String getMapFileName();
	public abstract String[] getStageFileList();
	
	public Image getMap(){
		return ditu;
	};
	
	public abstract MainActor getZJ();
	
	public void onResize(int width, int height){
		getViewport().setScreenSize(width, height);
	}
	
	public void startStage(Class<? extends BaseStage> cls, Vector2 actorPosition, SpriteState actorState){
		BaseStage nextStage = null;
//		BaseStage nextStage = MainGame.findStage(cls);
//		if (nextStage!=null) {
//			MainGame.setCurrentStage(nextStage);
//			nextStage.setActorPositon(actorPosition);
//			nextStage.cameraHelper.setStage(nextStage);
//			MainActor zj = nextStage.getZJ();
//			zj.setOnImpact(true);
//			zj.setState(actorState);
//			nextStage.cameraHelper.setTarget(zj.getGameObject());
//		}else {
			try {
				Constructor<? extends BaseStage> cons = cls.getConstructor(new Class[] { CameraHelper.class , Vector2.class, SpriteState.class});
				nextStage = cons.newInstance(cameraHelper, actorPosition, actorState);
//				MainGame.addCurrentStage(nextStage);
				MainGame.setCurrentStage(nextStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		nextStage.onResize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cameraHelper.onResize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void setActorPositon(Vector2 position){
		getZJ().setPosition(position.x, position.y);
	}
}
