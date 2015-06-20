package com.mygdx.game.xy;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {

	private static final String TAG = CameraHelper.class.getName();

	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;

	private Vector2 position;
	private float zoom;

	private AbstractGameObject target;

	private BaseStage stage;

	public CameraHelper() {
		position = new Vector2();
		zoom = 1;
	}
	
	public void setStage(BaseStage stage) {
		this.stage = stage;
	}

	public void update(float deltaTime) {
		if (!hasTarget())
			return;
		check();
	}

	public void check() {
		position.x = target.getX();
		position.y = target.getY() + target.getHeight() / 2;
		if (stage.getMap().getWidth() < position.x
				+ stage.getCamera().viewportWidth / 2) {
			position.x = stage.getMap().getWidth()
					- stage.getCamera().viewportWidth / 2;
		}
		if (stage.getMap().getHeight() < position.y
				+ stage.getCamera().viewportHeight / 2) {
			position.y = stage.getMap().getHeight()
					- stage.getCamera().viewportHeight / 2;
		}
		if (0 > position.y - stage.getCamera().viewportHeight / 2) {
			position.y = stage.getCamera().viewportHeight / 2;
		}
		if (0 > position.x - stage.getCamera().viewportWidth / 2) {
			position.x = stage.getCamera().viewportWidth / 2;
		}
	}

	public void setPosition(float x, float y) {
		position.set(x, y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void addZoom(float amount) {
		setZoom(zoom + amount);
	}

	public void setZoom(float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}

	public float getZoom() {
		return zoom;
	}

	public void setTarget(AbstractGameObject target) {
		this.target = target;
	}

	public AbstractGameObject getTarget() {
		return target;
	}

	public boolean hasTarget() {
		return target != null;
	}

	public boolean hasTarget(Sprite target) {
		return hasTarget() && this.target.equals(target);
	}

	public Camera getCamera() {
		return stage.getCamera();
	}

	public void applyTo() {
		stage.getCamera().position.x = position.x;
		stage.getCamera().position.y = position.y;
//		stage.getCamera().zoom = zoom;
		stage.getCamera().update();
	}

	public void onResize(int width, int height) {
		if (height*16<width*9) {
			stage.getCamera().viewportWidth = Constants.VIEWPORT_HEIGHT*16/9;
			stage.getCamera().viewportHeight = (stage.getCamera().viewportWidth / width) * height;
		}else {
			stage.getCamera().viewportHeight = Constants.VIEWPORT_HEIGHT;
			stage.getCamera().viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		}
		check();
		applyTo();
	}
}
