package com.rafl.engine.gfx.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rafl.engine.gfx.Renderer;
import com.rafl.engine.gfx.Sprite;

public class Pane extends Component {

	private List<Component> components;
	private HashMap<Component, Rectangle> previousPositions;
	private Color bgColor;

	public Pane(Rectangle bounds) {
		super(bounds);
		this.components = new ArrayList<>();
		this.previousPositions = new HashMap<>();
		this.bgColor = Color.WHITE;
	}

	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	@Override
	public void update() {
		for (Component c : components) c.update();
	}
	
	@Override
	public Sprite getSprite() {
		Sprite assembled = new Sprite(getWidth(), getHeight(), bgColor);
		
		components.forEach( c -> {
			Point pos = c.renderPosition();
			Renderer.render(assembled, c.getSprite(),
					pos.x + this.getX(), pos.y + this.getY());
		});
		
		return assembled;
	}

	public void add(Component component) {
		Rectangle cbounds = component.bounds;
		components.add(component);
		previousPositions.put(component, new Rectangle(cbounds));
		cbounds.setLocation(cbounds.x + bounds.x , cbounds.y + bounds.y);
	}
	
	public void add(Component...components) {
		for (Component c : components) {
			add(c);
		}
	}

	public void remove(Component component) {
		if(!components.contains(component)) return;
		components.remove(component);
		Rectangle origin = previousPositions.get(component);
		component.bounds.setBounds(origin);
	}

}
