package com.mygdx.game.xy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.xy.JueSe.SpriteState;

public class MainGame extends ApplicationAdapter {
	private static final String TAG = MainGame.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
	private boolean paused;
	private static BaseStage curentStage;
	private CameraHelper cameraHelper;
//	private static ArrayList<BaseStage> stageList = new ArrayList<BaseStage>();
	
//	public static void addCurrentStage(BaseStage curentStage){
//		MainGame.curentStage = curentStage;
//		stageList.add(curentStage);
//	}
	
	public static void setCurrentStage(BaseStage curentStage){
		MainGame.curentStage = curentStage;
	}
	
//	public static BaseStage findStage(Class<? extends BaseStage> cls) {
//		for (BaseStage stage : stageList) {
//			if (stage.getClass().equals(cls)) {
//				return stage;
//			}
//		}
//		return null;
//	}
	
	@Override
	public void create() {
//		Gdx.app.setLogLevel(Application.LOG_DEBUG);
//		Assets.instance.init(new AssetManager(), Constants.TEXTURE_ATLAS_FILE_LIST_LONGGONG);
//		worldController = new WorldController();
//		worldRenderer = new WorldRenderer(worldController);
//		paused = false;
		cameraHelper = new CameraHelper();
		  curentStage = new FirstStage(cameraHelper, new Vector2(10, 75), SpriteState.STAND_RIGHT);
//		  stageList.add(curentStage);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cameraHelper.update(Gdx.graphics.getDeltaTime());
		cameraHelper.applyTo();
		Gdx.input.setInputProcessor(curentStage);
		curentStage.act();
		curentStage.draw();
//		if (!paused) {
//			worldController.update(Gdx.graphics.getDeltaTime());
//		}
//		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f,
//				 0xff/255.0f);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		worldRenderer.render();
	}
	
	@Override
	public void resize(int width, int height) {
//		worldRenderer.resize(width, height);
		cameraHelper.onResize(width, height);
		curentStage.onResize(width, height);
	}
	
	@Override
	public void dispose() {
//		worldRenderer.dispose();
		Assets.instance.dispose();
	}
	
}
