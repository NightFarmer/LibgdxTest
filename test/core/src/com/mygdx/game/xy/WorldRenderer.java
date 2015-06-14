package com.mygdx.game.xy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable{

	private SpriteBatch batch;
	private WorldController controller;
	
	public WorldRenderer(WorldController controller) {
		this.controller = controller;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
	}
	
	public void render() { 
		renderTestObjects();
	}
	
	private void renderTestObjects() {
		controller.cameraHelper.applyTo();
		batch.setProjectionMatrix(controller.cameraHelper.getCamera().combined);
		batch.begin();
		controller.dituSprite.draw(batch);
		for (Sprite sprite : controller.testSprites) {
			sprite.draw(batch);
		}
		controller.rock.render(batch);
		controller.rock2.render(batch);
		batch.end();
		
	}

	public void resize(int width, int height) {
		controller.cameraHelper.onResize(width, height);
//		controller.cameraHelper.setPosition(camera.viewportWidth/2, camera.viewportHeight/2);
//		if (width<=height) {
//			camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
//			camera.viewportHeight = Constants.VIEWPORT_HEIGHT;
//		}else {
//			camera.viewportHeight = (Constants.VIEWPORT_WIDTH/width)*height;
//			camera.viewportWidth = Constants.VIEWPORT_WIDTH;
//		}
//		Лђеп
//		if (width>height*2) {
//			camera.viewportWidth = Constants.VIEWPORT_WIDTH*2;
//			camera.viewportHeight = (camera.viewportWidth/width)*height;
//		}else {
//			camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
//			camera.viewportHeight = Constants.VIEWPORT_HEIGHT;
//		}
	}


	@Override
	public void dispose() {
		batch.dispose();
	}

	
}
