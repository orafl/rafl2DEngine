package com.rafl.engine.gfx.ui;

import java.awt.Rectangle;

import com.rafl.engine.gfx.Sprite;

public abstract class ActionComponent extends Component{
	
	public ActionComponent(Rectangle bounds) {
		super(bounds);
	}

	public ActionComponent(Sprite sprite, Rectangle bounds) {
		super(sprite, bounds);
	}

	public ActionComponent(Sprite sprite) {
		super(sprite);
	}

	public abstract void activate();

}
