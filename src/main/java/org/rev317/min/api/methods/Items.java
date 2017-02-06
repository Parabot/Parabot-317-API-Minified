package org.rev317.min.api.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.parabot.core.Configuration;
import org.parabot.core.Context;
import org.parabot.environment.api.utils.WebUtil;

import java.net.MalformedURLException;
import java.util.HashMap;

/**
 * @author JKetelaar
 */
public class Items {
    /**
     * TODO Cache results
     */
    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();
    private static JSONParser jsonParser = new JSONParser();

    private static HashMap<Integer, Long> prices = new HashMap<>();
    private static HashMap<Integer, String> names = new HashMap<>();

    public static String getName(int id) {
        String name;
        if ((name = names.get(id)) != null) {
            return name;
        } else {
            try {
                String content = WebUtil.getContents(Configuration.ITEM_API + id);
                if (content.length() > 0) {
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                    JSONObject itemInformation = (JSONObject) jsonObject.get("result");
                    if (itemInformation.get("name") != null && !((String) itemInformation.get("name")).equalsIgnoreCase("null")) {
                        name = (String) itemInformation.get("name");
                        names.put(id, name);
                        return name;
                    }
                }
            } catch (MalformedURLException | ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static int[] getIds(String name) {
        try {
            String content = WebUtil.getContents(Configuration.ITEM_API + name);
            if (content.length() > 0) {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                JSONArray array = (JSONArray) jsonObject.get("result");
                if (array.size() > 0) {
                    int[] ids = new int[array.size()];
                    for (int i = 0; i < array.size(); i++) {
                        ids[i] = Integer.parseInt((String) array.get(i));
                    }
                    return ids;
                }
            }
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    /**
     * Returns an int array based on the string it starts with. getIdsStart(dragon) will return an int array with all items starting with dragon
     *
     * @param name
     * @return
     */
    public static int[] getIdsStart(String name) {
        try {
            String content = WebUtil.getContents(Configuration.ITEM_API + "starts/" + name);
            if (content.length() > 0) {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                JSONArray array = (JSONArray) jsonObject.get("result");
                if (array.size() > 0) {
                    int[] ids = new int[array.size()];
                    for (int i = 0; i < array.size(); i++) {
                        ids[i] = Integer.parseInt((String) array.get(i));
                    }
                    return ids;
                }
            }
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    /**
     * Returns an int array based on the string it contains. getIdsContain(dragon) will return an int array with all items containingdragon
     *
     * @param name
     * @return
     */
    public static int[] getIdsContain(String name) {
        try {
            String content = WebUtil.getContents(Configuration.ITEM_API + "contains/" + name);
            if (content.length() > 0) {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                JSONArray array = (JSONArray) jsonObject.get("result");
                if (array.size() > 0) {
                    int[] ids = new int[array.size()];
                    for (int i = 0; i < array.size(); i++) {
                        ids[i] = Integer.parseInt((String) array.get(i));
                    }
                    return ids;
                }
            }
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    public static long getPrice(int id) {
        if (prices.containsKey(id)) {
            return prices.get(id);
        } else {
            try {
                String content = WebUtil.getContents(Configuration.ITEM_API + id + "/" + Context.getInstance().getServerProviderInfo().getServerName());
                if (content.length() > 0) {
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                    JSONObject itemInformation = (JSONObject) jsonObject.get("result");
                    if (itemInformation.get("price") != null && !((String) itemInformation.get("price")).equalsIgnoreCase("null")) {
                        long price = Long.parseLong((String) itemInformation.get("price"));
                        prices.put(id, price);
                        return price;
                    }
                }
            } catch (MalformedURLException | ParseException e) {
                e.printStackTrace();
            }
        }
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
