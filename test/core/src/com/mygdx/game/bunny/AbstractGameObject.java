package com.mygdx.game.bunny;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
	/**
	 * λ��
	 */
	public Vector2 position;
	/**
	 * ��С
	 */
	public Vector2 dimension;
	/**
	 * ����
	 */
	public Vector2 origin;
	/**
	 * ����
	 */
	public Vector2 scale;
	/**
	 * �Ƕ�
	 */
	public float rotation;

	public AbstractGameObject() {
		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		scale = new Vector2(1, 1);
		rotation = 0;
	}

	public void update(float deltaTime) {
	}

	public abstract void render(SpriteBatch batch);
}