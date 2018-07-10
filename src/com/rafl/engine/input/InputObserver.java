package com.rafl.engine.input;

import java.util.Set;

public interface InputObserver<T> {
	
	void registerInputs(Set<T> activated);
}
