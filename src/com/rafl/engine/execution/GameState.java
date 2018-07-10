package com.rafl.engine.execution;

import com.rafl.engine.gfx.Renderer;

public interface GameState {

	void initialize(GameContext context);
	void update(double delta);
	void render(Renderer renderer);
	default void terminate(GameContext context) {}
}
