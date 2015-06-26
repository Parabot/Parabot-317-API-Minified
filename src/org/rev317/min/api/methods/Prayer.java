package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;

/**
 * @author JKetelaar, Fryslan
 */
public enum Prayer {
    THICK_SKIN(83, 5609, "name", 1),
    BURST_OF_STRENGTH(84, 5610, "name", 1),
    CLARITY_OF_THOUGHT(85, 5611, "name", 1),
    SHARP_EYE(700, 19812, "name", 1),
    MYSTIC_WILL(701, 19814, "name", 1),
    ROCK_SKIN(86, 5612, "name", 1),
    SUPERHUMAN_STRENGTH(87, 5613, "name", 1),
    IMPROVED_REFLEXES(88, 5614, "name", 1),
    RAPID_RESTORE(89, 5615, "name", 1),
    RAPID_HEAL(90, 5616, "name", 1),
    PROTECT_ITEM(91, 5617, "name", 1),
    HAWK_EYE(702, 19816, "name", 1),
    MYSTIC_LORE(703, 19818, "name", 1),
    STEEL_SKIN(92, 5618, "name", 1),
    ULTIMATE_STRENGTH(93, 5619, "name", 1),
    INCREDIBLE_REFLEXES(94, 5620, "name", 1),
    PROTECT_FROM_SUMMONING(708, 23105, "name", 1),
    PROTECT_FROM_MAGIC(95, 5621, "name", 1),
    PROTECT_FROM_MISSILES(96, 5622, "name", 1),
    PROTECT_FROM_MELEE(97, 5623, "name", 1),
    EAGLE_EYE(704, 19823, "name", 1),
    MYSTIC_MIGHT(705, 19823, "name", 1),
    RETRIBUTION(98, 683, "name", 1),
    REDEMPTION(99, 684, "name", 1),
    SMITE(100, 685, "name", 1),
    CHIVALRY(706, 19825, "name", 1),
    RAPID_RENEWAL(709, 23109, "name", 1),
    PIETY(707, 19827, "name", 1),
    RIGOUR(710, 23113, "name", 1),
    AUGURY(711, 23116, "name", 1);

    private int setting;
    private int action;
    private String name;
    private int level;

    Prayer(int setting, int action, String name, int level) {
        this.setting = setting;
        this.action = action;
        this.name = name;
        this.level = level;
    }

    /**
     * Returns the required level for the requested prayer action
     *
     * @return Int standing for the required level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the setting ID that can be used for detecting if it's enabled or not
     *
     * @return Int that stands for the setting ID
     */
    public int getSetting() {
        return setting;
    }

    /**
     * The action ID that can be used to perform a direct action within the client
     *
     * @return Int standing for the action ID
     */
    public int getAction() {
        return action;
    }

    /**
     * Returns the name of the prayer action
     *
     * TODO Get the actual string from the variable
     *
     * @return String containing the name of the prayer action
     */
    public String getName() {
        return name().charAt(0)+name().substring(1).toLowerCase().replace("_", " ");
    }

    public int getSettingID() {
        return setting;
    }

    public boolean isEnabled(){
        return Game.getSetting(setting) == 1;
    }

    public void enable() {
        if (!isEnabled()) {
            Menu.sendAction(169, -1, -1, action);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return isEnabled();
                }
            }, 1500);
        }
    }

    public void disable() {
        if (isEnabled()) {
            Menu.sendAction(169, -1, -1, action);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !isEnabled();
                }
            }, 1500);
        }
    }
}
