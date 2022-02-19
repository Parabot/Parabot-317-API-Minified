package org.rev317.min.api.methods;

import org.parabot.api.misc.StringUtils;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;

/**
 * @author Everel, JKetelaar, EmmaStone
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
     *
     * @deprecated Use {@link Interfaces #getOpenInterfaceId()} instead
     */
    @Deprecated
    public static int getOpenInterfaceId() {
        return Loader.getClient().getOpenInterfaceId();
    }

    /**
     * Get open back dialog id
     *
     * @return back dialog id
     *
     * @deprecated Use {@link Interfaces #getBackDialogId()} instead
     */
    @Deprecated
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
     * Gets the friends list long values
     *
     * @return long values of friends list
     */
    public static long[] getFriendsListAsLong() {
        return Loader.getClient().getFriendsListAsLong();
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
    public static int getSetting(int index) {
        return Loader.getClient().getSettings()[index];
    }

    /**
     * Returns all the settings within the client
     *
     * @return All settings
     */
    public static int[] getSettings() {
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

    /**
     * Just simply drops the client
     */
    public static void dropClient() {
        Loader.getClient().dropClient();
    }

    /**
     * Drops the client and returns if the game is logged out or not
     *
     * @return True if game is logged out, false if not
     */
    public static boolean confirmedDropClient() {
        Loader.getClient().dropClient();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !isLoggedIn();
            }
        }, 2500);

        return !isLoggedIn();
    }

    /**
     * Login to a server
     *
     * @param username     String
     * @param password     String
     * @param reconnecting True if it's a retry, false if not
     */
    public static void login(String username, String password, boolean reconnecting) {
        Loader.getClient().login(username, password, reconnecting);
    }

    /**
     * Login to a server
     *
     * @param username String
     * @param password String
     */
    public static void login(String username, String password) {
        login(username, password, false);
    }

    /**
     * Adds friend
     *
     * @param username String
     */
    public static void addFriend(String username) {
        Loader.getClient().addFriend(StringUtils.longForName(StringUtils.fixName(username)));
    }

    /**
     * Deletes friend
     *
     * @param username String
     */
    public static void deleteFriend(String username) {
        Loader.getClient().deleteFriend(StringUtils.longForName(StringUtils.fixName(username)));
    }
}
