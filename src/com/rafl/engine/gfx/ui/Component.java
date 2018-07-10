package com.rafl.engine.gfx.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import com.rafl.engine.gfx.Bitmap;
import com.rafl.engine.gfx.Renderable;
import com.rafl.engine.gfx.Sprite;

public abstract class Component implements Renderable, Bitmap {
	
	protected Sprite sprite;
	protected Rectangle bounds;
	
	public Component(Sprite sprite, Rectangle bounds) {
		this.sprite = sprite;
		this.bounds = bounds;
	}
	
	public Component(Sprite sprite) {
		this(sprite, new Rectangle(sprite.getDimension()));
	}
	
	public Component(Rectangle bounds) {
		this.bounds = bounds;
		this.sprite = new Sprite(new Dimension(bounds.width, bounds.height));
	}
	
	public void update() {}
	
	public void setLocation(Point p) {
		bounds.setLocation(p);
	}
	
	public void setLocation(int x, int y) {
		bounds.setLocation(x,y);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getX() {
		return bounds.x;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	@Override
	public Point renderPosition() {
		return bounds.getLocation();
	}

	@Override
	public int getWidth() {
		return bounds.width;
	}

	@Override
	public int getHeight() {
		return bounds.height;
	}

	@Override
	public int getPixel(int x, int y) { return sprite.getPixel(x, y); }

	@Override
	public void setPixel(int x, int y, int value) {
		sprite.setPixel(x, y, value);
	}

	@Override
	public int[] getRaster() { return sprite.getRaster(); }
}
