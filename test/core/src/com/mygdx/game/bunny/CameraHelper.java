package com.mygdx.game.bunny;

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

	private Sprite ditu;

	private OrthographicCamera camera;
	
	public CameraHelper(Sprite dituSprite, OrthographicCamera camera) {
		position = new Vector2();
		zoom = 1;
		this.ditu = dituSprite;
		this.camera = camera;
	}
	
	public void update(float deltaTime) {
		if (!hasTarget()) return;
		check();
	}
	
	public void check(){
		position.x = target.position.x + target.dimension.x/2;
		position.y = target.position.y + target.dimension.y/2;
		if (ditu.getWidth()<position.x+camera.viewportWidth/2) {
			position.x = ditu.getWidth()-camera.viewportWidth/2;
		}
		if (ditu.getHeight()<position.y+camera.viewportHeight/2) {
			position.y = ditu.getHeight()-camera.viewportHeight/2;
		}
		if (0>position.y-camera.viewportHeight/2) {
			position.y = camera.viewportHeight/2;
		}
		if (0>position.x-camera.viewportWidth/2) {
			position.x = camera.viewportWidth/2;
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

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void applyTo() {
		camera.position.x = position.x;
		camera.position.y = position.y;
		camera.zoom = zoom;
		camera.update();
	}
	
	public void onResize(int width, int height){
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
		check();
		applyTo();
	}
}
