package com.rafl.engine.gfx.ui.utils;

import java.awt.Rectangle;

import com.rafl.engine.gfx.ui.Component;

public interface Placement {
	
	Rectangle place(int spacingX, int spacingY, Component a, Component b);
}
