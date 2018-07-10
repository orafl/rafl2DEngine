package com.rafl.engine.gfx.ui;

import java.awt.Rectangle;

import com.rafl.engine.gfx.Sprite;

public class Pointer extends Component{

	public Pointer(Sprite sprite) {
		super(sprite);
	}

	public void pointTo(Component target) {
		
		Rectangle tb = target.getBounds();
		int offX = tb.x - bounds.width -tb.width/10;
		int offY = tb.y + ((tb.height - bounds.height)/2);
		
		this.bounds.setBounds(offX, offY, bounds.width, bounds.height);
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

}
