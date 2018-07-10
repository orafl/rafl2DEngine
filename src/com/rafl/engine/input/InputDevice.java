package com.rafl.engine.input;

public interface InputDevice<T> {
	
	void update();
	void add(InputObserver<T> ob);
	void remove(InputObserver<T> ob);

}
