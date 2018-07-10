package com.rafl.engine.gfx;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rafl.engine.utils.Timer;

public class Animation {

	private static class Frame {
		Sprite sprite;
		int durationMillis;

		private Frame(Sprite sprite, int durationMillis) {
			this.sprite = sprite;
			this.durationMillis = durationMillis;
		}
		
		public void flipHorizontally() { sprite.flipHorizontally(); }
		
		public void flipVertically() { sprite.flipVertically(); }
	}

	public static class Builder {

		private SpriteSheet spriteSheet;
		private List<Frame> frames;

		public Builder(SpriteSheet spriteSheet) {
			this.spriteSheet = spriteSheet;
			this.frames = new ArrayList<>();
		}

		public Frame getFrame(int row, int col, int durationMillis) {
			return new Frame(spriteSheet.getSprite(row, col), durationMillis);
		}

		public Builder setTransparencyOf(int index, int color) {
			frames.get(index).sprite.setTransparency(color);
			return this;
		}

		public Builder setTransparencyOf(int index, Color color) {
			return setTransparencyOf(index, color.getRGB());
		}

		public Builder setTransparency(int color) {
			
			frames.forEach(f -> f.sprite.setTransparency(color));
			return this;
		}

		public Builder setTransparency(Color color) {
			return setTransparency(color.getRGB());
		}

		public Builder frame(int row, int col, int durationMillis) {
			frames.add(getFrame(row, col, durationMillis));
			return this;
		}

		public Builder subAnimation(Animation animation) {
			frames.addAll(animation.frames);
			return this;
		}
		
		public Builder flipHorizontally() {
			frames.forEach(Frame::flipHorizontally);
			return this;
		}
		
		public Builder flipVertically() {
			frames.forEach(Frame::flipVertically);
			return this;
		}

		public Animation build() {

			if(frames.size() <= 0) 
				frames.add(getFrame(0, 0, 70));

			return new Animation(frames);
		}

	}

	private Frame current;
	private Timer timer;
	private List<Frame> frames;
	private int index;
	
	public static Animation nullAnimation(){
		return new Animation(Collections.emptyList());
	}
	
	public static Animation from(Animation other) {
		Animation a = new Animation(other.frames);
		a.index = 0; return a;
	}

	private Animation(List<Frame> frames){
		this.current = frames.get(0);
		this.timer = new Timer(1000);
		this.frames = frames;
	}
	
	public Animation flipHorizontally() {
		List<Frame> frames = new ArrayList<>(this.frames);
		frames.forEach(Frame::flipHorizontally);
		return new Animation(frames);
	}
	
	public Animation flipVertically() {
		List<Frame> frames = new ArrayList<>(this.frames);
		frames.forEach(Frame::flipVertically);
		return new Animation(frames);
	}

	public Sprite play() {
		
		if(timer.countdown()) {
			index++;
			if(index >= frames.size()) index = 0;
		}
		
		current = frames.get(index);
		timer.setRefreshRate(current.durationMillis);

		return current.sprite;
	}

	public Sprite getCurrentFrame() {
		return current.sprite;
	}

}
