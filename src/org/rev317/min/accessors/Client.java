package org.rev317.min.accessors;

public interface Client {
	
	public Scene getScene();
	
	public Player getMyPlayer();
	
	public Interface[] getInterfaceCache();
	
	public Npc[] getNpcs();
	
	public Player[] getPlayers();
	
	public int getOpenInterfaceId();
	
	public int getBaseX();
	
	public int getBaseY();
	
	public void setInterface(int id);

	public int[] getCurrentExp();
	
	public Deque[][][] getGroundItems();
	
	public int getLoopCycle();
	
	public int getBackDialogId();
	
	public int getPlane();
	
	public int[] getMenuActionId();
	
	public int[] getMenuAction1();
	
	public int[] getMenuAction2();
	
	public int[] getMenuAction3();
	
	public CollisionMap[] getCollisionMap();
	
	// args switched
	public boolean walkTo(int clickType, int sizeX, int sizeY, int startX, int startY, int destX, int destY, int type, int face, boolean arbitrary, int rotation);
	
	public void doAction(int i);
	

}
