package com.mygdx.game.bunny;

import java.util.ArrayList;

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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class WorldController extends InputAdapter{

	public static final String TAG = WorldController.class.getName();
	public Sprite[] testSprites;
	private int selectedSprite;
	public CameraHelper cameraHelper;
	public Rock rock;
	public Rock rock2;
	public Sprite dituSprite;
	private OrthographicCamera camera;
	
	public WorldController() {
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		initTestObjects();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		cameraHelper = new CameraHelper(dituSprite, camera);
		cameraHelper.setTarget(rock2);
	}
	
	private void initTestObjects() {
		testSprites = new Sprite[5];
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width, height);
		Texture texture = new Texture(pixmap);
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		ArrayList<AtlasRegion> list = Assets.instance.getList(Constants.TEXTURE_ATLAS_FILE_NPC, Constants.NPC_LONGWANG, "00", Constants.TNPC_LONGWANG_FRAM_NUM[0]);
		
		for (int i = 0; i < testSprites.length; i++) {
			Sprite sprite = new Sprite(list.get(i));
			sprite.setSize(10, 10);
			sprite.setOrigin(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
			float randomX = MathUtils.random(-20f, 20f);
			float randomY = MathUtils.random(-20f, 20f);
			sprite.setPosition(randomX, randomY);
			testSprites[i] = sprite;
		}
		rock = new Rock();
		rock2 = new Rock();
		rock.position= new Vector2(25, -25);
		rock2.position= new Vector2(0, 0);
		
		
		Texture ditu = new Texture("images/map/fangcunshan.jpg");
		dituSprite = new Sprite(ditu);
		dituSprite.setPosition(0, 0);
		dituSprite.setSize(ditu.getHeight()/10, ditu.getHeight()/10);
//		ArrayList<AtlasRegion> list = Assets.instance.getList(Constants.TEXTURE_ATLAS_FILE_NPC, Constants.NPC_LONGWANG, "00", 10);
//		Sprite sprite = new Sprite(list.get(0));
//		sprite.setSize(1, 1);
//		sprite.setOrigin(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
//		Sprite sprite = new Sprite(new Texture("images/daxianren/2239-eb00de5f-00000.png"));
//		Sprite sprite = new Sprite(new Texture("images/testgame.pack.png"));
//		float randomX = MathUtils.random(-2.0f, 2.0f);
//		float randomY = MathUtils.random(-2.0f, 2.0f);
//		sprite.setPosition(randomX, randomY);
//		testSprites[testSprites.length-1] = sprite;
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
		float moveSpeed = 20* deltaTime;
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
		if (Gdx.input.isKeyPressed(Keys.COMMA))// ,¼ü
			cameraHelper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD))// .¼ü
			cameraHelper.addZoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH))// /¼ü
			cameraHelper.setZoom(1);
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}
		
		
	private void moveSelectedSprite(float x, float y){
//		testSprites[selectedSprite].translate(x, y);
		rock2.position.x+=x;
		rock2.position.y+=y;
	}
	
	private void updateTestObjects(float deltaTime) {
		float rotation = testSprites[selectedSprite].getRotation();
		rotation+=90*deltaTime;
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
//				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
			Gdx.app.debug(TAG, "sprite #"+selectedSprite+" selected");
		} else if (keycode == Keys.ENTER) {
//			cameraHelper.setTarget(cameraHelper.hasTarget() ? null
//					: testSprites[selectedSprite]);
			Gdx.app.debug(TAG,
					"Camera follow enabled: " + cameraHelper.hasTarget());
		}
		return super.keyUp(keycode);
	}
}
