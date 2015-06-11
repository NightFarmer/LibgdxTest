package com.mygdx.game.bunny;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable{

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController controller;
	
	public WorldRenderer(WorldController controller) {
		this.controller = controller;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
	}
	
	public void render() { 
		renderTestObjects();
	}
	
	private void renderTestObjects() {
		controller.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite sprite : controller.testSprites) {
			sprite.draw(batch);
		}
		batch.end();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
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
		camera.update();
	}


	@Override
	public void dispose() {
		batch.dispose();
	}

	
}
