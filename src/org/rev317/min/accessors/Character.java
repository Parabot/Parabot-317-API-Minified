package org.rev317.min.accessors;

public interface Character {
	
	public int getX();
	
	public int getY();
	
	public int getAnimation();
	
	public int getLoopCycleStatus();
	
	public int getCurrentHealth();
	
	public int getMaxHealth();
	
	public int getInteractingEntity();

    public boolean isLoggedIn();
}

