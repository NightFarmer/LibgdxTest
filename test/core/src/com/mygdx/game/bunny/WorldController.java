package com.mygdx.game.bunny;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class WorldController extends InputAdapter{

	public static final String TAG = WorldController.class.getName();
	public Sprite[] testSprites;
	private int selectedSprite;
	public CameraHelper cameraHelper;
	
	public WorldController() {
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initTestObjects();
	}
	
	private void initTestObjects() {
		testSprites = new Sprite[5];
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width, height);
		Texture texture = new Texture(pixmap);
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		for (int i = 0; i < testSprites.length; i++) {
			Sprite sprite = new Sprite(texture);
			sprite.setSize(1, 1);
			sprite.setOrigin(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			sprite.setPosition(randomX, randomY);
			testSprites[i] = sprite;
		}
		selectedSprite = 0;
	}
	
	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(1, 0, 0, 0.5f);
		 pixmap.fill();
		 // Draw a yellow-colored X shape on square
		 pixmap.setColor(1, 1, 0, 1);
		 pixmap.drawLine(0, 0, width, height);
		 pixmap.drawLine(width, 0, 0, height);
		 // Draw a cyan-colored border around square
		 pixmap.setColor(0, 1, 1, 1);
		 pixmap.drawRectangle(0, 0, width, height);
		 return pixmap;
	}

	public void update(float deltaTime) {
		handleDebugInput(deltaTime);
		updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void handleDebugInput(float deltaTime) {
		if (Gdx.app.getType()!=ApplicationType.Desktop) {
			return;
		}
		float moveSpeed = 5* deltaTime;
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveSelectedSprite(-moveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveSelectedSprite(moveSpeed, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveSelectedSprite(0, moveSpeed);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			moveSelectedSprite(0, -moveSpeed);
		}

		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveCamera(-camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveCamera(camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveCamera(0, -camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
			cameraHelper.setPosition(0, 0);

		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA))// ,��
			cameraHelper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD))// .��
			cameraHelper.addZoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH))// /��
			cameraHelper.setZoom(1);
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}
		
		
	private void moveSelectedSprite(float x, float y){
		testSprites[selectedSprite].translate(x, y);
	}
	
	private void updateTestObjects(float deltaTime) {
		float rotation = testSprites[selectedSprite].getRotation();
		rotation+=400*deltaTime;
		rotation%=360;
		testSprites[selectedSprite].setRotation(rotation);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (Keys.R == keycode) {
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		} else if (Keys.SPACE == keycode) {
			selectedSprite = (selectedSprite+1)%testSprites.length;
			if (cameraHelper.hasTarget()) {
				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
			Gdx.app.debug(TAG, "sprite #"+selectedSprite+" selected");
		} else if (keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null
					: testSprites[selectedSprite]);
			Gdx.app.debug(TAG,
					"Camera follow enabled: " + cameraHelper.hasTarget());
		}
		return super.keyUp(keycode);
	}
}
