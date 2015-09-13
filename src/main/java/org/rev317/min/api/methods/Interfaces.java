package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;

/**
 * @author JKetelaar, Empathy
 */
public class Interfaces {

    private static final int optionAction = 2494;

    /**
     * Clicks an option from the given back dialog
     * @param index Index of the requested option whereas you start at 0
     */
    private static void clickOption(int index) {
        Menu.sendAction(315, 0, 0, optionAction + index, 0);
        Time.sleep(1500);
    }
}
