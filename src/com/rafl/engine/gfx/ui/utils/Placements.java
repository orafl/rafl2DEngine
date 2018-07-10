package com.rafl.engine.gfx.ui.utils;

import java.awt.Rectangle;

import com.rafl.engine.gfx.ui.Component;

public enum Placements implements Placement{

	ABOVE {
		@Override
		public Rectangle place(int spX, int spY, Component a, Component b) {
		
		Rectangle ar = a.getBounds(), br = b.getBounds();
		
		int x = br.x + spX;
		int y = br.y - br.height - spY;
		
		return new Rectangle(x, y, ar.width, ar.height);
	   }
	},
	
	BELOW {
		@Override
		public Rectangle place(int spX, int spY, Component a, Component b) {
		
		Rectangle ar = a.getBounds(), br = b.getBounds();
		
		int x = br.x + spX;
		int y = br.y + br.height + spY;
		
		return new Rectangle(x, y, ar.width, ar.height);
	   }
	},
	
	LEFT{
		@Override
		public Rectangle place(int spX, int spY, Component a, Component b) {
		
		Rectangle ar = a.getBounds(), br = b.getBounds();
		
		int x = br.x - br.width - spX;
		int y = br.y + spY;
		
		return new Rectangle(x, y, ar.width, ar.height);
	   }
	},
	
	RIGHT{@Override
		public Rectangle place(int spX, int spY, Component a, Component b) {
		
		Rectangle ar = a.getBounds(), br = b.getBounds();
		
		int x = br.x + br.width + spX;
		int y = br.y + spY;
		
		return new Rectangle(x, y, ar.width, ar.height);
	   }
	};



}
