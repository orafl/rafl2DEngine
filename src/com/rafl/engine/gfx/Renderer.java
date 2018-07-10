package com.rafl.engine.gfx;

import java.awt.Point;

public class Renderer {

	private Display display;

	public Renderer(Display display) {
		this.display = display; 
	}
	
	public static void render(Bitmap base, Sprite sp, int xPos, int yPos)
	{
		if(sp == null || base == null) return;
		
		int w = base.getWidth();
		int h = base.getHeight();

		for (int y = 0; y < sp.getHeight(); y++) {
			for (int x = 0; x < sp.getWidth(); x++) {

				int xx = x + xPos, yy = y + yPos;
				int pixel = sp.getPixel(x, y);

				if(pixel == sp.getTrasparency()) continue;
				if(xx < 0 || xx >= w || yy < 0 || yy >= h) continue;

				base.setPixel(xx, yy, pixel);
			}
		}
	}

	public void render(Sprite sp, int xPos, int yPos) {
		Renderer.render(display, sp, xPos, yPos);
	}
	
	public void render(Renderable r) {
		Point pos = r.renderPosition();
		this.render(r.getSprite(), pos.x, pos.y);
	}

	public void clear() {
		display.clear();
		for (int i = 0; i < display.getRaster().length; i++) {
			display.setPixel(i, 0);
		}
	}

	public void draw(Drawable d) {
		display.draw(d);
	}
}
