package com.rafl.engine.execution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.rafl.engine.gfx.Renderer;

public class GameContext {
	
	private GameState current;

	public GameContext() {
		current = new NullState();
	}

	public void update(double delta) {
		current.update(delta);
	}

	public void render(Renderer r) {
		current.render(r);
	}
	
	public void setState(Class<? extends GameState> type) {
		try {
			changeState(type.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void changeState(GameState next) {
		current.terminate(this);
		current = next;
		current.initialize(this);
	}
	
	public void setState(Constructor<? extends GameState> state,
			Object...initargs) {
		try {
			changeState(state.newInstance(initargs));
		} catch (InstantiationException | IllegalAccessException 
		| IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public static class NullState implements GameState{

		@Override
		public void initialize(GameContext context) {}
		
		@Override
		public void update(double delta) {}

		@Override
		public void render(Renderer r) {}

	}

}
