package com.rafl.engine.gfx;

public interface Bitmap {
	
	int getWidth();
	int getHeight();
	int getPixel(int x ,int y);
	void setPixel(int x ,int y, int value);
	int[] getRaster();
}
