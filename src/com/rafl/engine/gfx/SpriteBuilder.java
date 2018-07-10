package com.rafl.engine.gfx;

import java.awt.image.BufferedImage;

public class SpriteBuilder {

	private SpriteBuilder() {}

	public static Sprite build(Sprite a, Location l, Sprite b) {
		return l.strategy(a, b);
	}

	public static enum Location {
		ABOVE {
			@Override
			Sprite strategy(Sprite a, Sprite b) {

				int difw = a.getWidth() - b.getWidth();

				BufferedImage build = new BufferedImage
				(b.getWidth() + difw, a.getHeight() + b.getHeight(),
				BufferedImage.TYPE_INT_RGB);

				for (int y = 0; y < a.getHeight(); y++) {
					for (int x = 0; x < a.getWidth(); x++) {
						build.setRGB(x, y, a.getPixel(x, y));
					}
				}

				for (int y = 0; y < b.getHeight(); y++) {
					for (int x = 0; x < b.getWidth(); x++) {
						build.setRGB(x, a.getHeight()+y, b.getPixel(x, y));
					}
				}

				return new Sprite(build);
			}
		}, BELOW {
			@Override
			Sprite strategy(Sprite a, Sprite b) {
				return ABOVE.strategy(b, a);
			}
		}, LEFT {
			@Override
			Sprite strategy(Sprite a, Sprite b) {
				
				int difh = a.getHeight() - b.getHeight();

				BufferedImage build = new BufferedImage
				(a.getWidth() + b.getWidth(), b.getHeight() + difh,
				BufferedImage.TYPE_INT_RGB);

				for (int y = 0; y < a.getHeight(); y++) {
					for (int x = 0; x < a.getWidth(); x++) {
						build.setRGB(x, y, a.getPixel(x, y));
					}
				}

				for (int y = 0; y < b.getHeight(); y++) {
					for (int x = 0; x < b.getWidth(); x++) {
						build.setRGB(x+a.getWidth(), y, b.getPixel(x, y));
					}
				}

				return new Sprite(build);
			}
		}, RIGHT {
			@Override
			Sprite strategy(Sprite a, Sprite b) {
				return LEFT.strategy(b,a);
			}
		};

		abstract Sprite strategy(Sprite a, Sprite b);
	}

}
