package com.bridgeit.usermanagement.dto;

public class CountUser {

	private int active;
	private int inActive;
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getInActive() {
		return inActive;
	}

	public void setInActive(int inActive) {
		this.inActive = inActive;
	}

	@Override
	public String toString() {
		return "Count [active=" + active + ", inActive=" + inActive + "]";
	}
	
}
