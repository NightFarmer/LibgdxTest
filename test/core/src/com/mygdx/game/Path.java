package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Path {
	
	public static List<Point> calcLinePath(Point origine, Point target){
		ArrayList<Point> path = new ArrayList<Point>();
		// y = ax+b;
		float distX = target.x-origine.x;
		float distY = target.y-origine.y;
		double dist = Math.pow(distX*distX+distY*distY,0.5);
		for (float i = 1; i <= dist; i++) {
			float newDistx = (float) (distX*i/dist);
			float newDisty = (float) (distY*i/dist);
			float x = origine.x+newDistx;
			float y = origine.y+newDisty;
			path.add(new Point(x, y));
		}
		return path;
	}
}
