package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;

/**
 * @author JKetelaar, Fryslan
 * TODO Set the actual level requirements
 * TODO Set curses setting and action ids.
 */
public class Prayer {

    public interface Book{
        public int getSetting();
        public int getAction();
        public int getLevel();
        public String getName();
    }

    public enum Normal implements Book{
        THICK_SKIN(83, 5609, 1),
        BURST_OF_STRENGTH(84, 5610, 1),
        CLARITY_OF_THOUGHT(85, 5611, 1),
        SHARP_EYE(700, 19812, 1),
        MYSTIC_WILL(701, 19814, 1),
        ROCK_SKIN(86, 5612, 1),
        SUPERHUMAN_STRENGTH(87, 5613, 1),
        IMPROVED_REFLEXES(88, 5614, 1),
        RAPID_RESTORE(89, 5615, 1),
        RAPID_HEAL(90, 5616, 1),
        PROTECT_ITEM(91, 5617, 1),
        HAWK_EYE(702, 19816, 1),
        MYSTIC_LORE(703, 19818, 1),
        STEEL_SKIN(92, 5618, 1),
        ULTIMATE_STRENGTH(93, 5619, 1),
        INCREDIBLE_REFLEXES(94, 5620, 1),
        PROTECT_FROM_SUMMONING(708, 23105, 1),
        PROTECT_FROM_MAGIC(95, 5621, 1),
        PROTECT_FROM_MISSILES(96, 5622, 1),
        PROTECT_FROM_MELEE(97, 5623, 1),
        EAGLE_EYE(704, 19823, 1),
        MYSTIC_MIGHT(705, 19823, 1),
        RETRIBUTION(98, 683, 1),
        REDEMPTION(99, 684, 1),
        SMITE(100, 685, 1),
        CHIVALRY(706, 19825, 1),
        RAPID_RENEWAL(709, 23109, 1),
        PIETY(707, 19827, 1),
        RIGOUR(710, 23113, 1),
        AUGURY(711, 23116, 1);

        private int setting;
        private int action;
        private int level;

        Normal(int setting, int action, int level) {
            this.setting = setting;
            this.action = action;
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
         * <p/>
         * TODO Get the actual string from the variable
         *
         * @return String containing the name of the prayer action
         */
        public String getName() {
            return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
        }

    }

    public enum Curses implements Book{
        PROTECT_ITEM_CURSE(0, 0, 50),
        SAP_WARRIOR(0, 0, 50),
        SAP_RANGER(0, 0, 52),
        SAP_MAGE(0, 0, 54),
        SAP_SPIRIT(0, 0, 56),
        BERSERKER(0, 0, 59),
        DEFLECT_SUMMONING(0, 0, 62),
        DEFLECT_MAGIC(0, 0, 65),
        DEFLECT_MISSILE(0, 0, 68),
        DEFLECT_MELEE(0, 0, 71),
        LEECH_ATTACK(0, 0, 74),
        LEECH_RANGE(0, 0, 76),
        LEECH_MAGIC(0, 0, 78),
        LEECH_DEFENCE(0, 0, 80),
        LEECH_STRENGTH(0, 0, 82),
        LEECH_ENERGY(0, 0, 84),
        LEECH_SPECIAL_ATTACK(0, 0, 86),
        WRATH(0, 0, 89),
        SOUL_SPLIT(0, 0, 92),
        TURMOIL(0, 0, 95);

        private int setting;
        private int action;
        private int level;

        Curses(int setting, int action, int level) {
            this.setting = setting;
            this.action = action;
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
         * <p/>
         * TODO Get the actual string from the variable
         *
         * @return String containing the name of the prayer action
         */
        public String getName() {
            return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
        }

    }


    public boolean isEnabled(Book book){
        return Game.getSetting(book.getSetting()) == 1;
    }

    public void enable(final Book book) {
        if (!isEnabled(book)) {
            Menu.sendAction(169, -1, -1, book.getAction());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return isEnabled(book);
                }
            }, 1500);
        }
    }

    public void disable(final Book book) {
        if (isEnabled(book)) {
            Menu.sendAction(169, -1, -1, book.getAction());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !isEnabled(book);
                }
            }, 1500);
        }
    }
}
