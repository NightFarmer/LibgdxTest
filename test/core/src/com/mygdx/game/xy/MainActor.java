package com.mygdx.game.xy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.xy.Rock.SpriteState;

public class MainActor extends MoveAbleActor implements CanImpact{

	private Rock rock;
	
	private final ArrayList<ImpactListener> impactListeners = new ArrayList<ImpactListener>();

	public MainActor() {
		rock = Rock.instance;
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		update();
		rock.render(batch);
	}
	
	private void update() {
		move();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		target = null;
		rock.position.x = x;
		rock.position.y = y;
		prePosition = null;
	}
	
	public void setState(SpriteState state){
		rock.setState(state);
	}
	
	private void move() {
		if (target==null) {
			return;
		}
		
		float x1 = rock.position.x;
		float y1 = rock.position.y;
		float x2 = target.x;
		float y2 = target.y;
		float distx = x2-x1;
		float disty = y2-y1;
		
		double tan = Math.tan(1f/8*Math.PI);
		if (distx>0 && disty/distx<tan && disty/distx>-tan) {
			rock.setState(SpriteState.RUN_RIGHT);
		}
		if (distx>0 && disty/distx>tan && distx/disty>tan) {
			rock.setState(SpriteState.RUN_RIGHTUP);
		}
		if (distx>0 && disty/distx<-tan && distx/disty<-tan) {
			rock.setState(SpriteState.RUN_RIGHTDOWN);
		}
		if (distx<0 && disty/distx>-tan && disty/distx<tan) {
			rock.setState(SpriteState.RUN_LEFT);
		}
		if (distx<0 && disty/distx<-tan && distx/disty <-tan) {
			rock.setState(SpriteState.RUN_LEFTUP);
		}
		if (distx<0 && disty/distx>tan && distx/disty>tan) {
			rock.setState(SpriteState.RUN_LEFTDOWN);
		}
		if (disty>0 && distx/disty <tan && distx/disty >-tan) {
			rock.setState(SpriteState.RUN_UP);
		}
		if (disty<0 && distx/disty >-tan && distx/disty<tan) {
			rock.setState(SpriteState.RUN_DOWN);
		}
		
		
		float dist = (float) Math.pow(distx*distx+disty*disty, 0.5);
		if (dist<0.5) {
			target=null;
			if (distx>0 && disty>0) {
				rock.setState(SpriteState.STAND_RIGHT);
			}
			if (distx<0 && disty>0) {
				rock.setState(SpriteState.STAND_UP);
			}
			if (distx<0 && disty<0) {
				rock.setState(SpriteState.STAND_LEFT);
			}
			if (distx>0 && disty<0) {
				rock.setState(SpriteState.STAND_DOWN);
			}
//			return;
		}
		float speed = Gdx.graphics.getDeltaTime() * 13;
		float dx = speed*distx/dist;
		float dy = speed*disty/dist;
		rock.position.x+=dx;
		rock.position.y+=dy;
		checkImpact();
	}

	public AbstractGameObject getGameObject(){
		return rock;
	}

	@Override
	public float getRadius() {
		return 2;
	}

	@Override
	public Vector2 getCenter() {
		return rock.origin;
	}
	
	public void addImpactListener(ImpactListener l){
		impactListeners.add(l);
	}
	
	public void setOnImpact(boolean b){
		for (ImpactListener l : impactListeners) {
			l.onImpact = b;
		}
	}
	
	private void checkImpact(){
		float x1 = rock.position.x;
		float y1 = rock.position.y;
//		System.out.println(x1+"======="+y1);
		for (ImpactListener listener : impactListeners) {
			CanImpact obj = listener.getObj();
			Vector2 center = obj.getCenter();
			float x2 = center.x;
			float y2 = center.y;
			float distx = x1-x2;
			float disty = y1-y2;
//			System.out.println(getRadius()+obj.getRadius()+"====="+Math.pow(distx*distx+disty*disty, 0.5));
			if (getRadius()+obj.getRadius()>Math.pow(distx*distx+disty*disty, 0.5)) {
				if (!listener.onImpact) {
					listener.onImpact(obj);
				}
			}else {
				listener.onImpact = false;
			}
		}
	}
}
