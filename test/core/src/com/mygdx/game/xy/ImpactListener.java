package com.mygdx.game.xy;

public abstract class ImpactListener {
	public boolean onImpact = true;
	private CanImpact obj;

	public ImpactListener(CanImpact obj) {
		this.obj = obj;
	}
	
	public CanImpact getObj() {
		return obj;
	}

	public abstract void onImpact(CanImpact obj);
}
