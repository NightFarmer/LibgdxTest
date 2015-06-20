package com.mygdx.game.xy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class JueSe extends AbstractGameObject {
	private SpriteState state;

	public SpriteState getState() {
		return state;
	}

	public void setState(SpriteState state) {
		this.state = state;
	}

	public void move(Vector2 target) {
		if (target == null) {
			return;
		}

		float x1 = getX();
		float y1 = getY();
		float x2 = target.x;
		float y2 = target.y;
		float distx = x2 - x1;
		float disty = y2 - y1;

		double tan = Math.tan(1f / 8 * Math.PI);
		if (distx > 0 && disty / distx < tan && disty / distx > -tan) {
			setState(SpriteState.RUN_RIGHT);
		}
		if (distx > 0 && disty / distx > tan && distx / disty > tan) {
			setState(SpriteState.RUN_RIGHTUP);
		}
		if (distx > 0 && disty / distx < -tan && distx / disty < -tan) {
			setState(SpriteState.RUN_RIGHTDOWN);
		}
		if (distx < 0 && disty / distx > -tan && disty / distx < tan) {
			setState(SpriteState.RUN_LEFT);
		}
		if (distx < 0 && disty / distx < -tan && distx / disty < -tan) {
			setState(SpriteState.RUN_LEFTUP);
		}
		if (distx < 0 && disty / distx > tan && distx / disty > tan) {
			setState(SpriteState.RUN_LEFTDOWN);
		}
		if (disty > 0 && distx / disty < tan && distx / disty > -tan) {
			setState(SpriteState.RUN_UP);
		}
		if (disty < 0 && distx / disty > -tan && distx / disty < tan) {
			setState(SpriteState.RUN_DOWN);
		}

		float dist = (float) Math.pow(distx * distx + disty * disty, 0.5);
		if (dist < 0.5) {
			target = null;
			if (distx > 0 && disty > 0) {
				setState(SpriteState.STAND_RIGHT);
			}
			if (distx < 0 && disty > 0) {
				setState(SpriteState.STAND_UP);
			}
			if (distx < 0 && disty < 0) {
				setState(SpriteState.STAND_LEFT);
			}
			if (distx > 0 && disty < 0) {
				setState(SpriteState.STAND_DOWN);
			}
		}
		float speed = Gdx.graphics.getDeltaTime() * 13;
		float dx = speed * distx / dist;
		float dy = speed * disty / dist;
		setX(getX()+dx);
		setY(getY()+dy);
	}

	public enum SpriteState {
		STAND_UP, STAND_DOWN, STAND_LEFT, STAND_RIGHT, RUN_UP, RUN_RIGHTUP, RUN_RIGHT, RUN_RIGHTDOWN, RUN_DOWN, RUN_LEFTDOWN, RUN_LEFT, RUN_LEFTUP,
	}

}
