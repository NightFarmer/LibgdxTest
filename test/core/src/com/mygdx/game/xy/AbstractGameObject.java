package com.mygdx.game.xy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractGameObject extends Actor implements Cloneable{
	/**
	 * 中心, 相对绝对坐标
	 */
	public Vector2 center;

	public AbstractGameObject() {
		center = new Vector2();
	}

	public void update(float deltaTime) {
	}

	public abstract void render(Batch batch);


	public Vector2 getCenter() {
		return center;
	}

}