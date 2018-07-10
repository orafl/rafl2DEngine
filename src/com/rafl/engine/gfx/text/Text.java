package com.rafl.engine.gfx.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.rafl.engine.gfx.Drawable;

public class Text implements Drawable{
	
	private Font font;
	private String text;
	private int x, y;
	private Color color;
	private Graphics g;
	
	public Text() {}

	public Text(Font font, String text, int x, int y, Color color) {
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
		this.g = g;
	}
	
	public FontMetrics getFontMetrics() {
		if(g == null) return null;
		return g.getFontMetrics(font);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
