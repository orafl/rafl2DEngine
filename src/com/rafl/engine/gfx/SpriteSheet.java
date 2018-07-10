package com.rafl.engine.gfx;

import java.awt.Dimension;

public class SpriteSheet {

	private Sprite sheet;
	public final Dimension scene;
	private int offsetX, offsetY;
	private int transparency;

	public SpriteSheet(Sprite sheet,
			int sceneWidth, int sceneHeight, int offsetX, int offsetY)
	{
		this.sheet = sheet;
		this.scene = new Dimension(sceneWidth, sceneHeight);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public SpriteSheet(Sprite sheet,
			int sceneLength, int offsetX, int offsetY) {
		this(sheet, sceneLength, sceneLength, offsetX, offsetY);
	}

	public Sprite getSprite(int row, int col) {

		final int w = scene.width, h = scene.height;
		int xPos, yPos;

		xPos = (((row+1)*w)-w)+offsetX*row+offsetX;
		yPos = (((col+1)*h)-h)+offsetY*col+offsetY;
		
		return sheet.subSprite(xPos, yPos, w, h);
	}

	public int getRows() {
		return sheet.getWidth()/(scene.width+offsetX);
	}

	public int getColumns() {
		return sheet.getHeight()/(scene.height+offsetY);
	}

	public int sceneHeight() {
		return scene.height;
	}

	public int sceneWidth() {
		return scene.width;
	}

	public int getTransparency() {
		return transparency;
	}

	public void setTransparency(int transparency) {
		this.transparency = transparency;
	}

}
