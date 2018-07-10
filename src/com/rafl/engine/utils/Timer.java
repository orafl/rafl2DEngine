package com.rafl.engine.utils;

public class Timer {
	
	private double refreshRate;
	private double passed, previous;
	
	public Timer(double refreshRateMillis) {
		this.refreshRate = refreshRateMillis;
		previous = System.currentTimeMillis();
	}
	
	public boolean countdown() {
		
		passed += System.currentTimeMillis() - previous;
		previous = System.currentTimeMillis();
		
		if(passed >= refreshRate) {
			passed = 0;
			return true;
		}
		
		return false;
		
	}
	
	public double getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(double refreshRateMillis) {
		this.refreshRate = refreshRateMillis;
	}

}
