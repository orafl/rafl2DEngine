package com.rafl.engine.gfx.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.rafl.engine.gfx.Drawable;
import com.rafl.engine.gfx.Sprite;
import com.rafl.engine.gfx.text.Text;

public class Label extends Component implements Drawable {

	private Text text;
	private Font font;
	private Color textColor, background, transparency;

	public Label(Point pos, Font font, String text) {
		super(new Rectangle(pos));
		this.text = new Text();
		this.text.setText(text);
		this.font = font;
		this.textColor = Color.RED;
		this.background = Color.BLACK;
	}

	@Override
	public void update() {

		FontMetrics fm = text.getFontMetrics();
		if(fm != null) {
			bounds.width = fm.stringWidth(getText());
			bounds.height = fm.getHeight();
		}

		sprite = new Sprite(bounds.width, bounds.height, background);
		if(transparency != null) sprite.setTransparency(transparency);

		text.setX(bounds.x);
		text.setY(bounds.y+bounds.height);
		text.setFont(font);
		text.setColor(textColor);
	}

	@Override
	public void draw(Graphics2D g) {
		text.draw(g);
	}

	public String getText() {
		return text.getText();
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Color getBackground() {
		return background;
	}

	public Label setBackground(Color background) {
		if(background == null) {
			sprite.setTransparency(this.background).fill(this.background);
			transparency = this.background;
		}
		else {
			this.background = background;
			transparency = null;
		}
		return this;
	}

}
