package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mario extends Actor{

	private int direction;//·½Ïò
	private List<Point> path = new ArrayList<Point>();
	
	private Point p;
	
	public Mario() {
		path = Path.calcLinePath(new Point(0, 0), new Point(300, 300f));
		p = new Point(0, 0);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		TextureRegion textureRegion = new TextureRegion(new Texture("badlogic.jpg"));
		if (path.size()-2>0) {
			p = path.get(0);
			path.remove(0);
			path.remove(0);
			path.remove(0);
		}
		batch.draw(textureRegion, p.x, p.y);
	}
}
