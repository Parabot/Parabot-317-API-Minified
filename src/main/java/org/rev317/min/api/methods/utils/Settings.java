package org.rev317.min.api.methods.utils;

/**
 * @author EmmaStone
 */
public enum Settings {
    BANK_INTERFACE_ID("bank_interface_id", 5292),
    ITEM_INTERFACE_ID("item_interface_id", 5382),
    BUTTON_CLOSE_BANK("button_close_bank", 5384),
    BUTTON_DEPOSIT_ALL("button_deposit_all", 5386),
    BUTTON_ACTION_CLICK("button_deposit_all", 646),
    BUTTON_DROP_ITEM("button_drop_item", 847),
    BUTTON_TAKE_ITEM("button_take_item", 234),
    INVENTORY_PARENT_ID("inventory_parent_id", 5064),
    BANK_OPEN_ID("bank_open_index", 1),
    INVENTORY_INDEX("inventory_index", 3214),
    MY_OFFER_INTERFACE_ID("my_offer_interface_id", 3415),
    OPPONENT_OFFER_INTERFACE_ID("opponent_offer_interface_id", 3416),
    FIRST_TRADE_INTERFACE_ID("first_trade_interface_id", 3323),
    SECOND_TRADE_INTERFACE_ID("second_trade_interface_id", 3443),
    MENU_SCENE_OBJECT_FIRST_INTERACTION("menu_scene_object_first_interaction", 502),
    MENU_SCENE_OBJECT_SECOND_INTERACTION("menu_scene_object_second_interaction", 900),
    MENU_SCENE_OBJECT_THIRD_INTERACTION("menu_scene_object_third_interaction", 113),
    MENU_SCENE_OBJECT_FOURTH_INTERACTION("menu_scene_object_fourth_interaction", 872),
    MENU_SCENE_OBJECT_FIFTH_INTERACTION("menu_scene_object_fifth_interaction", 1062),
    MENU_SCENE_OBJECT_EXAMINE("menu_scene_object_examine", 1226),
    MENU_CHARACTER_FOLLOW("menu_character_follow", 2779),
    MENU_CHARACTER_TRADE("menu_character_trade", 2027),
    MENU_CHARACTER_ATTACK("menu_character_attack", 2561),
    MENU_CHARACTER_FIRST_INTERACTION("menu_character_first_interaction", 412),
    MENU_CHARACTER_SECOND_INTERACTION("menu_character_second_interaction", 20),
    MENU_CHARACTER_THIRD_INTERACTION("menu_character_third_interaction", 225),
    MENU_CHARACTER_FOURTH_INTERACTION("menu_character_fourth_interaction", 965),
    MENU_CHARACTER_FIFTH_INTERACTION("menu_character_fifth_interaction", 478),
    MENU_CHARACTER_EXAMINE("menu_character_examine", 1025),
    MENU_TRANSFORM_ONE_INTERACTION("menu_transform_one_interaction", 632),
    MENU_TRANSFORM_FIVE_INTERACTION("menu_transform_five_interaction", 78),
    MENU_TRANSFORM_TEN_INTERACTION("menu_transform_ten_interaction", 867),
    MENU_TRANSFORM_ALL_INTERACTION("menu_transform_all_interaction", 431),
    MENU_TRANSFORM_X_INTERACTION("menu_transform_x_interaction", 53),
    MENU_TRANSFORM_ALL_BUT_ONE_INTERACTION("menu_transform_all_but_one_interaction", 775),
    MENU_TRANSFORM_EXAMINE_INTERACTION("menu_transform_examine_interaction", 1125),
    MENU_GROUND_ITEM_FIRST_INTERACTION("menu_ground_item_first_interaction", 652),
    MENU_GROUND_ITEM_SECOND_INTERACTION("menu_ground_item_second_interaction", 567),
    MENU_GROUND_ITEM_THIRD_INTERACTION("menu_ground_item_third_interaction", 234),
    MENU_GROUND_ITEM_FOURTH_INTERACTION("menu_ground_item_fourth_interaction", 244),
    MENU_GROUND_ITEM_FIFTH_INTERACTION("menu_ground_item_fifth_interaction", 213),
    MENU_GROUND_ITEM_EXAMINE_INTERACTION("menu_ground_item_examine_interaction", 1448),
    MENU_ITEM_FIRST_INTERACTION("menu_item_first_interaction", 454),
    MENU_ITEM_SECOND_INTERACTION("menu_item_second_interaction", 74),
    MENU_ITEM_THIRD_INTERACTION("menu_item_third_interaction", 447),
    MENU_ITEM_FOURTH_INTERACTION("menu_item_fourth_interaction", 493),
    MENU_ITEM_FIFTH_INTERACTION("menu_item_fifth_interaction", 847),
    MENU_ITEM_SIXTH_INTERACTION("menu_item_sixth_interaction", 1125),
    MENU_ITEM_SEVENTH_INTERACTION("menu_item_seventh_interaction", 870);

    private String name;
    private int    id;

    Settings(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getActionByName(String name) {
        for (Settings settings : Settings.values()) {
            if (name.equalsIgnoreCase(settings.getName())) {
                return settings.getId();
            }
        }

        return 0;
    }
}
