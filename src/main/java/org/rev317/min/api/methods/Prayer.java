package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;

import java.util.ArrayList;

/**
 * @author JKetelaar, Fryslan
 *         TODO Set the actual level requirements
 *         TODO Set curses setting and action ids.
 */
public class Prayer {

    public static boolean isEnabled(Book book) {
        return Game.getSetting(book.getSetting()) == 1;
    }

    public static void enable(final Book book) {
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

    public static void disable(final Book book) {
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

    public static Book[] getActivePrayers() {
        ArrayList<Book> prayers = new ArrayList<>();
        for (Book normal : Normal.values()) {
            if (isEnabled(normal)) {
                prayers.add(normal);
            }
        }
        for (Book curse : Curse.values()) {
            if (isEnabled(curse)) {
                prayers.add(curse);
            }
        }
        return prayers.toArray(new Book[prayers.size()]);
    }

    public static void setActivePrayers(Book[] prayers) {
        for (Book book : prayers) {
            for (Book normal : Normal.values()) {
                if (!isEnabled(normal) && normal.equals(book)) {
                    enable(normal);
                }
            }
            for (Book curse : Curse.values()) {
                if (!isEnabled(curse) && curse.equals(book)) {
                    enable(curse);
                }
            }
        }
    }

    public enum Normal implements Book {
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

    public enum Curse implements Book {
        PROTECT_ITEM_CURSE(601, 22503, 50),
        SAP_WARRIOR(611, 22505, 50),
        SAP_RANGER(612, 22507, 52),
        SAP_MAGE(613, 22509, 54),
        SAP_SPIRIT(614, 22511, 56),
        BERSERKER(615, 22513, 59),
        DEFLECT_SUMMONING(616, 22515, 62),
        DEFLECT_MAGIC(617, 22517, 65),
        DEFLECT_MISSILE(618, 22519, 68),
        DEFLECT_MELEE(619, 22521, 71),
        LEECH_ATTACK(620, 22523, 74),
        LEECH_RANGE(621, 22525, 76),
        LEECH_MAGIC(622, 22527, 78),
        LEECH_DEFENCE(623, 22529, 80),
        LEECH_STRENGTH(624, 22531, 82),
        LEECH_ENERGY(625, 22533, 84),
        LEECH_SPECIAL_ATTACK(626, 22535, 86),
        WRATH(627, 22537, 89),
        SOUL_SPLIT(628, 22539, 92),
        TURMOIL(629, 22541, 95);

        private int setting;
        private int action;
        private int level;

        Curse(int setting, int action, int level) {
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

    public interface Book {
        public int getSetting();

        public int getAction();

        public int getLevel();

        public String getName();
    }
}
