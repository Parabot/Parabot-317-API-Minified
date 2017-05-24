package org.rev317.min.api.methods;

import org.json.simple.parser.JSONParser;

import java.util.HashMap;

/**
 * @author JKetelaar
 */
public class Items {
    /**
     * TODO Cache results
     */
    private static HashMap<String, Integer> settings   = new HashMap<>();
    private static JSONParser               jsonParser = new JSONParser();

    private static HashMap<Integer, Long>   prices = new HashMap<>();
    private static HashMap<Integer, String> names  = new HashMap<>();

    /**
     * @param id
     *
     * @return
     *
     * @deprecated
     */
    public static String getName(int id) {
        return null;
    }

    /**
     * @param name
     *
     * @return
     *
     * @deprecated
     */
    public static int[] getIds(String name) {
        return new int[0];
    }

    /**
     * @param name
     *
     * @return
     *
     * @deprecated Returns an int array based on the string it starts with. getIdsStart(dragon) will return an int array with all items starting with dragon
     */
    public static int[] getIdsStart(String name) {
        return new int[0];
    }

    /**
     * @param name
     *
     * @return
     *
     * @deprecated Returns an int array based on the string it contains. getIdsContain(dragon) will return an int array with all items containingdragon
     */
    public static int[] getIdsContain(String name) {
        return new int[0];
    }

    /**
     * @param id
     *
     * @return
     *
     * @deprecated
     */
    public static long getPrice(int id) {
        return 0;
    }

    public enum Option {
        TRANSFORM_FIRST(settings.get("menu_transform_one_interaction")),
        VALUE(settings.get("menu_transform_one_interaction")),
        TRANSFORM_ONE(settings.get("menu_transform_one_interaction")),

        TRANSFORM_SECOND(settings.get("menu_transform_five_interaction")),
        TRANSFORM_FIVE(settings.get("menu_transform_five_interaction")),

        TRANSFORM_THIRD(settings.get("menu_transform_ten_interaction")),
        TRANSFORM_TEN(settings.get("menu_transform_ten_interaction")),

        TRANSFORM_FOURTH(settings.get("menu_transform_all_interaction")),
        TRANSFORM_ALL(settings.get("menu_transform_all_interaction")),

        TRANSFORM_FIFTH(settings.get("menu_transform_x_interaction")),
        TRANSFORM_X(settings.get("menu_transform_x_interaction")),
        TRANSFORM_HUNDRED(settings.get("menu_transform_x_interaction")),

        TRANSFORM_SIXTH(settings.get("menu_transform_all_but_one_interaction")),
        TRANSFORM_ALL_BUT_ONE(settings.get("menu_transform_all_but_one_interaction")),

        TRANSFORM_EXAMINE(settings.get("menu_transform_examine_interaction")),

        FIRST(settings.get("menu_item_first_interaction")),
        WEAR(settings.get("menu_item_first_interaction")),

        SECOND(settings.get("menu_item_second_interaction")),
        CONSUME(settings.get("menu_item_second_interaction")),
        DRINK(settings.get("menu_item_second_interaction")),

        THIRD(settings.get("menu_item_third_interaction")),
        USE(settings.get("menu_item_third_interaction")),

        FOURTH(settings.get("menu_item_fourth_interaction")),
        EMPTY(settings.get("menu_item_fourth_interaction")),

        FIFTH(settings.get("menu_item_fifth_interaction")),
        DROP(settings.get("menu_item_fifth_interaction")),

        SIXTH(settings.get("menu_item_sixth_interaction")),
        EXAMINE(settings.get("menu_item_sixth_interaction")),

        SEVENTH(settings.get("menu_item_seventh_interaction")),
        USE_WITH(settings.get("menu_item_seventh_interaction"));

        private int actionId;

        Option(int actionId) {
            this.actionId = actionId;
        }

        public int getActionId() {
            return actionId;
        }
    }
}
