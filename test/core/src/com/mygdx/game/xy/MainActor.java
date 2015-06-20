package com.mygdx.game.xy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.xy.JueSe.SpriteState;

public class MainActor extends MoveAbleActor implements CanImpact{

	private JueSe juese;
	
	private final ArrayList<ImpactListener> impactListeners = new ArrayList<ImpactListener>();

	public MainActor() {
		juese = new ShuSheng();
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		update();
		juese.render(batch);
	}
	
	private void update() {
		juese.move(target);
		checkImpact();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		target = null;
		juese.setX(x);
		juese.setY(y);
		prePosition = null;
	}
	
	public void setState(SpriteState state){
		juese.setState(state);
	}
	
	
	public AbstractGameObject getGameObject(){
		return juese;
	}

	@Override
	public float getRadius() {
		return 2;
	}

	@Override
	public Vector2 getCenter() {
		return juese.center;
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
		float x1 = juese.getX();
		float y1 = juese.getY();
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
