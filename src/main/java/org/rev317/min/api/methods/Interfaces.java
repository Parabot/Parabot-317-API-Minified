package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;

/**
 * @author JKetelaar, Empathy, Fryslan
 */
public class Interfaces {

    private static final int optionAction = 2494;

    /**
     * Get's the interfaces loaded in the interface Cache.
     *
     * @return Interfaces in the Interface Cache.
     */
    public static Interface[] getInterfaces() {
        return Loader.getClient().getInterfaceCache();
    }

    /**
     * Get's the Interface from the Interface Cache using the given ID.
     *
     * @param id Interface ID.
     *
     * @return Interface from the cache by the given ID.
     */
    public static Interface getInterface(int id) {
        return getInterfaces()[id];
    }

    /**
     * Opens the Interface by the given ID.
     *
     * @param id ID of the Interface to Open.
     */
    public static void openInterface(int id) {
        Loader.getClient().setInterface(id);
    }

    /**
     * Sets the int by the given Amount
     *
     * @param amount Amount to set
     */
    public static void setAmountOrNameInput(int amount) {
        Loader.getClient().setAmountOrNameInput(amount);
    }

    /**
     * Get's the Open Interface ID.
     *
     * @return The ID of the Open Interface , will be -1 if all Interfaces are closed.
     */
    public static int getOpenInterfaceId() {
        return Loader.getClient().getOpenInterfaceId();
    }

    /**
     * Get's the Open Back Dialog ID.
     *
     * @return The ID of the Open Back Dialog , will be -1 if all Back Dialogs are closed.
     */
    public static int getBackDialogId() {
        return Loader.getClient().getBackDialogId();
    }

    /**
     * Get current input dialog state
     *
     * @return input dialog state
     */
    public static int getInputDialogState() {
        return Loader.getClient().getInputDialogState();
    }

    /**
     * Checks if the Interface or Back Dialog by the given ID is Open.
     *
     * @param id ID of the Interface or Back Dialog to check for.
     *
     * @return True is the Interface or Back Dialog is Open else will return false.
     */
    public static boolean isOpen(int id) {
        return getOpenInterfaceId() == id || getBackDialogId() == id;
    }

    /**
     * Checks if the Interface by the given ID is Open.
     *
     * @param id         ID of the Interface or Back Dialog to check for depending on the backDialog boolean.
     * @param backDialog When tru it will check the Bank Dialog ID else it will check for the Interface ID.
     *
     * @return True is the Interface or Back Dialog is Open else will return false.
     */
    public static boolean isOpen(int id, boolean backDialog) {
        if (backDialog) {
            return getBackDialogId() == id;
        } else {
            return getOpenInterfaceId() == id;
        }
    }

    /**
     * Clicks an option from the given back dialog
     *
     * @param index Index of the requested option whereas you start at 0
     */
    public static void clickBackDialogOption(int index) {
        Menu.sendAction(315, 0, 0, optionAction + index, 0);
        Time.sleep(1000);
    }
}
