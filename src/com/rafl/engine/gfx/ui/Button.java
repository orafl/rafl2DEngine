package com.rafl.engine.gfx.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.SwingUtilities;

import com.rafl.engine.gfx.Renderer;
import com.rafl.engine.gfx.Sprite;
import com.rafl.engine.input.InputObserver;

public class Button extends ActionComponent 
implements InputObserver<MouseEvent> {

	public static interface Action { void onClick(); }

	private Action action;
	private MouseEvent event;

	public Button(Rectangle bounds,
			Sprite sprite, Action action) {
		super(sprite, bounds);
		this.action = action;
	}

	public static class Builder {

		private final Rectangle bounds;
		private Sprite sprite;
		private Action action;

		public Builder(Rectangle bounds) {
			this.bounds = bounds;
			this.sprite = new Sprite(bounds.width, bounds.height);
			this.action = () -> {};
		}

		public Builder(int width, int height) {
			this(new Rectangle(width, height));
		}

		public Builder(Renderer renderer, int x, int y, Sprite sprite) {
			this(new Rectangle
					(x, y, sprite.getWidth(), sprite.getHeight()));
			this.sprite = sprite;
		}

		public Builder sprite(Sprite sprite) {
			this.sprite = sprite;
			return this;
		}

		public Builder action(Action action) {
			this.action = action;
			return this;
		}

		public Button build() {
			return new Button(bounds, sprite, action);
		}

	}

	@Override
	public void update() {
		
		if(event == null) return;

		Point cursor = new Point(event.getX(), event.getY());

		if(bounds.contains(cursor) && SwingUtilities.isLeftMouseButton(event))
		{
			action.onClick();
		}

	}

	@Override
	public void activate() {
		action.onClick();
	}

	@Override
	public void registerInputs(Set<MouseEvent> selected) {
		for (MouseEvent event : selected) this.event = event;
	}

}
