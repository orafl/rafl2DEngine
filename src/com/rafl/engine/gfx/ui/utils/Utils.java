package com.rafl.engine.gfx.ui.utils;

import java.awt.Rectangle;
import java.util.Stack;

import com.rafl.engine.gfx.ui.Component;

public class Utils {
	
	private Utils() {}
	
	public static class Order {
		private int x, y, spacingX, spacingY;
		private Placement placement;
		public Order(int x, int y, int spacingX, int spacingY,
			Placement placement) {
			this.x = x;
			this.y = y;
			this.spacingX = spacingX;
			this.spacingY = spacingY;
			this.placement = placement;
		}
	}
	
	public static Rectangle place(Component a, Placement pos, Component b,
	int spacingX, int spacingY) {
		
		return pos.place(spacingX, spacingY, a, b);
	}
	
	public static void stack(Order order, Component...components) {
		
		Stack<Component> stack = new Stack<>();
		for (int i = components.length-1 ; i > -1; i--) {
			stack.add(components[i]);
		}
		Component offset = null;
		
		while(!stack.isEmpty()) {
			
			Component curr = stack.pop();
			if(offset == null) curr.setLocation(order.x, order.y);
			else curr.getBounds().setBounds(place(curr, order.placement,
					offset,order.spacingX, order.spacingY));
			offset = curr;
		}
		
	}

}
