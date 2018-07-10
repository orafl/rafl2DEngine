package com.rafl.engine.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class Sprite implements Bitmap {

	private int width, height;
	private int[] raster, original;
	private int trasparency;

	public Sprite(int width, int height, int[] raster) {
		this.width = width;
		this.height = height;
		this.raster = raster;
		this.original = raster;
	}

	public Sprite(int width, int height) {
		this(width, height, new int[width*height]);
	}

	public Sprite(int width, int height, int color) {
		this(width, height);
		fill(color);
	}

	public Sprite(Sprite sprite) {
		this(sprite.width, sprite.height);
	}

	public Sprite(BufferedImage img) {
		this(img.getWidth(), img.getHeight(),
				img.getRGB(0, 0, img.getWidth(), img.getHeight(),
						null, 0, img.getWidth()));
	}

	public Sprite(int width, int height, Color color) {
		this(width, height, color.getRGB());
	}

	public Sprite(Dimension dimension) {
		this( (int) dimension.getWidth(),(int) dimension.getHeight());
	}

	public int area() {
		return raster.length;
	}

	public void fill(int color) {
		for (int i = 0; i < raster.length; i++) {
			raster[i] = color;
		}
	}

	public void fill(Color color) {
		fill(color.getRGB());
	}

	public void restore() {
		this.raster = original;
	}

	public Sprite subSprite(int xPixel, int yPixel, int width, int height){

		int[] subPart = new int[width*height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				subPart[x + y * width] = raster[(xPixel + x) + (yPixel + y) * this.width];
			}
		}

		return new Sprite(width, height, subPart);
	}

	public void swapColors(int a, int b) {
		for (int i = 0; i < raster.length; i++) {
			if(raster[i] == a) raster[i] = b;
		}
	}

	public void swapColors(Color a, Color b) {
		swapColors(a.getRGB(), b.getRGB());
	}

	public static Sprite spriteFrom (BufferedImage i){
		return new Sprite(i);
	}

	public boolean validCoord(int x, int y) {
		if(x > width || x < 0 || y > height || y < 0)
			return false;
		return true;
	}

	private void catchInvalidCoord(int x, int y) {
		if(!validCoord(x, y)) throw new IllegalArgumentException
		("Given coordenate lies outside of the raster:" + x + " " +y);
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		catchInvalidCoord(x, y);
		return raster[x+y*width];
	}

	@Override
	public void setPixel(int x, int y, int value) {
		catchInvalidCoord(x, y);
		raster[x+y*width] = value;
	}
	
	@Override
	public int[] getRaster() {
		return raster;
	}

	public int getTrasparency() {
		return trasparency;
	}

	public void flipHorizontally() {

		int[] newArr = new int[raster.length];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				newArr[x + y * width] = raster[(width-x-1)+ y * width];
			}
		}
		
		raster = newArr;
	}

	public void flipVertically() {
		int[] newArr = new int[raster.length];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				newArr[x + y * width] = raster[x + (height - y) * width];
			}
		}
		
		raster = newArr;
	}

	public Sprite setTransparency(int trasparency) {
		this.trasparency = trasparency;
		return this;
	}

	public Sprite setTransparency(Color trasparency) {
		return setTransparency(trasparency.getRGB());
	}

	public Dimension getDimension() {
		return new Dimension(width, height);
	}

}
