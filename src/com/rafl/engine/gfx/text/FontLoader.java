package com.rafl.engine.gfx.text;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontLoader {

	private FontLoader() {}
	
	public static Font loadTTF(String file, int size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT,
					FontLoader.class.getResourceAsStream(file))
					.deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
