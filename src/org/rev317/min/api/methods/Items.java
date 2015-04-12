package org.rev317.min.api.methods;

import org.parabot.core.Context;

import java.util.Properties;

/**
 * @author JKetelaar
 */
public class Items {
    private static Properties settings = Context.getInstance().getServerProviderInfo().getSettings();

    public enum Option{
        TRANSFORM_FIRST(Integer.parseInt(settings.getProperty("menu_transform_one_interaction"))),
        VALUE(Integer.parseInt(settings.getProperty("menu_transform_one_interaction"))),
        TRANSFORM_ONE(Integer.parseInt(settings.getProperty("menu_transform_one_interaction"))),

        TRANSFORM_SECOND(Integer.parseInt(settings.getProperty("menu_transform_five_interaction"))),
        TRANSFORM_FIVE(Integer.parseInt(settings.getProperty("menu_transform_five_interaction"))),

        TRANSFORM_THIRD(Integer.parseInt(settings.getProperty("menu_transform_ten_interaction"))),
        TRANSFORM_TEN(Integer.parseInt(settings.getProperty("menu_transform_ten_interaction"))),

        TRANSFORM_FOURTH(Integer.parseInt(settings.getProperty("menu_transform_all_interaction"))),
        TRANSFORM_ALL(Integer.parseInt(settings.getProperty("menu_transform_all_interaction"))),

        TRANSFORM_FIFTH(Integer.parseInt(settings.getProperty("menu_transform_x_interaction"))),
        TRANSFORM_X(Integer.parseInt(settings.getProperty("menu_transform_x_interaction"))),
        TRANSFORM_HUNDRED(Integer.parseInt(settings.getProperty("menu_transform_x_interaction"))),

        TRANSFORM_SIXTH(Integer.parseInt(settings.getProperty("menu_transform_all_but_one_interaction"))),
        TRANSFORM_ALL_BUT_ONE(Integer.parseInt(settings.getProperty("menu_transform_all_but_one_interaction"))),

        TRANSFORM_EXAMINE(Integer.parseInt(settings.getProperty("menu_transform_examine_interaction"))),

        FIRST(Integer.parseInt(settings.getProperty("menu_item_first_interaction"))),
        WEAR(Integer.parseInt(settings.getProperty("menu_item_first_interaction"))),

        SECOND(Integer.parseInt(settings.getProperty("menu_item_second_interaction"))),
        CONSUME(Integer.parseInt(settings.getProperty("menu_item_second_interaction"))),

        THIRD(Integer.parseInt(settings.getProperty("menu_item_third_interaction"))),
        EMPTY(Integer.parseInt(settings.getProperty("menu_item_third_interaction"))),

        FOURTH(Integer.parseInt(settings.getProperty("menu_item_fourth_interaction"))),
        USE(Integer.parseInt(settings.getProperty("menu_item_fourth_interaction"))),

        FIFTH(Integer.parseInt(settings.getProperty("menu_item_fifth_interaction"))),
        DROP(Integer.parseInt(settings.getProperty("menu_item_fifth_interaction"))),

        SIXTH(Integer.parseInt(settings.getProperty("menu_item_sixth_interaction"))),
        EXAMINE(Integer.parseInt(settings.getProperty("menu_item_sixth_interaction"))),

        SEVENTH(Integer.parseInt(settings.getProperty("menu_item_seventh_interaction"))),
        USE_WITH(Integer.parseInt(settings.getProperty("menu_item_seventh_interaction")));

        int actionId;

        Option(int actionId) {
            this.actionId = actionId;
        }

        public int getActionId() {
            return actionId;
        }
    }
}
