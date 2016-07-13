package org.rev317.min.accessors;

public interface Client {

    Scene getScene();

    Player getMyPlayer();

    Interface[] getInterfaceCache();

    Npc[] getNpcs();

    Player[] getPlayers();

    int getOpenInterfaceId();

    int getBaseX();

    int getBaseY();

    void setInterface(int id);

    int[] getCurrentExp();

    Deque[][][] getGroundItems();

    int getLoopCycle();

    int getBackDialogId();

    int getPlane();

    int[] getMenuActionId();

    int[] getMenuAction1();

    int[] getMenuAction2();

    int[] getMenuAction3();

    int[] getMenuAction4();

    CollisionMap[] getCollisionMap();

    // args switched
    boolean walkTo(int clickType, int sizeX, int sizeY, int startX, int startY, int destX, int destY, int type, int face, boolean arbitrary, int rotation);

    void doAction(int i);

    void dropClient();

    int[] getCurrentStats();

    int[] getSettings();

    boolean isLoggedIn();
}
