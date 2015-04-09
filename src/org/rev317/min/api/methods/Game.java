package org.rev317.min.api.methods;

import org.rev317.min.Loader;

/**
 * @author Everel, JKetelaar
 */
public class Game {

    /**
     * Gets BaseX
     *
     * @return baseX
     */
    public static int getBaseX() {
        return Loader.getClient().getBaseX();
    }

    /**
     * Gets BaseY
     *
     * @return baseY
     */
    public static int getBaseY() {
        return Loader.getClient().getBaseY();
    }

    /**
     * Gets open interface id
     *
     * @return interface id
     */
    public static int getOpenInterfaceId() {
        return Loader.getClient().getOpenInterfaceId();
    }

    /**
     * Get open back dialog id
     *
     * @return back dialog id
     */
    public static int getOpenBackDialogId() {
        return Loader.getClient().getBackDialogId();
    }

    /**
     * Gets loop cycle
     *
     * @return loop cycle
     */
    public static int getLoopCycle() {
        return Loader.getClient().getLoopCycle();
    }

    /**
     * Get collision flags
     *
     * @return collision flags
     */
    public static int[][] getCollisionFlags() {
        return Loader.getClient().getCollisionMap()[Game.getPlane()].getFlags();
    }

    /**
     * Gets current plane
     *
     * @return current plane
     */
    public static int getPlane() {
        return Loader.getClient().getPlane();
    }

    /**
     * Determines whether this client has action 4 hooked
     *
     * @return <code>true</code> if action 4 is hooked
     */
    public static boolean hasAction4() {
        try {
            Loader.getClient().getMenuAction4();
            return true;
        } catch (AbstractMethodError e) {
            return false;
        }
    }

    /**
     * Returns the settings within the client
     *
     * @param index The index of the setting you want to gather
     *
     * @return The specific setting for the given index
     */
    public static int getSetting(int index){
        return Loader.getClient().getSettings()[index];
    }

    /**
     * Returns all the settings within the client
     *
     * @return All settings
     */
    public static int[] getSettings(){
        return Loader.getClient().getSettings();
    }

    /**
     * Determines if the entity is logged in
     *
     * @return <b>true</b> if entity is logged in
     */
    public static boolean isLoggedIn() {
        return Loader.getClient().isLoggedIn();
    }

}
