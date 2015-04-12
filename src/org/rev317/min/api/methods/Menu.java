package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.wrappers.Character;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.Properties;

/**
 * @author JKetelaar, Everel
 */
public class Menu {

    private static Properties settings = Context.getInstance().getServerProviderInfo().getSettings();

    /**
     * Interacts with a sceneobject
     *
     * @param object
     * @param actionId
     */
    public static void interact(SceneObject object, int actionId) {
        if (Game.hasAction4()) {
            sendAction(actionId, object.getHash(), object.getLocalRegionX(), object.getLocalRegionY(), object.getId(), 0);
        } else {
            sendAction(actionId, object.getHash(), object.getLocalRegionX(), object.getLocalRegionY());
        }
    }

    /**
     * Interacts with a character
     *
     * @param character
     * @param actionId
     */
    public static void interact(Character character, int actionId) {
        sendAction(actionId, character.getIndex(), 0, 0);
    }

    /**
     * Interacts with an item when it has the following menu Transform-1 Transform-5 Transform-10 etc..
     *
     * @param item
     * @param actionId
     * @param interfaceParentId
     */
    public static void transformItem(Item item, int actionId,
                                     int interfaceParentId) {
        sendAction(actionId, item.getId() - 1, item.getSlot(),
                interfaceParentId);
    }

    /**
     * Takes grounditem from the ground
     *
     * @param item
     */
    public static void take(GroundItem item) {
        sendAction(Integer.parseInt(settings.getProperty("button_take_item")), item.getId(), item.getX(), item.getY());
    }

    /**
     * Interacts with a ground item
     *
     * @param item
     * @param actionId
     */
    public static void interact(GroundItem item, int actionId) {
        sendAction(actionId, item.getId(), item.getX(), item.getY());
    }

    public static void interact(Item item, int actionId){
        sendAction(actionId, item.getId() - 1, item.getSlot(), 3214);
    }

    /**
     * Drops an item
     *
     * @param item
     */
    public static void drop(Item item) {
        sendAction(Integer.parseInt(settings.getProperty("button_drop_item")), item.getId() - 1, item.getSlot(),
                Integer.parseInt(settings.getProperty("inventory_index")));
    }

    /**
     * Clicks a button
     *
     * @param id
     */
    public static void clickButton(int id) {
        sendAction(Integer.parseInt(settings.getProperty("button_action_click")), 0, 0, id);
    }

    /**
     * Sends an action to the client
     *
     * @param action
     * @param cmd1
     * @param cmd2
     * @param cmd3
     */
    public static void sendAction(int action, int cmd1, int cmd2, int cmd3) {
        sendAction(action, cmd1, cmd2, cmd3, 1);
    }

    /**
     * Sends an action to the client
     *
     * @param action
     * @param cmd1
     * @param cmd2
     * @param cmd3
     * @param index
     */
    public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int index) {
        sendAction(action, cmd1, cmd2, cmd3, 0, index);
    }

    /**
     * Sends an action to the client
     *
     * @param action
     * @param cmd1
     * @param cmd2
     * @param cmd3
     * @param cmd4
     * @param index
     */
    public static void sendAction(int action, int cmd1, int cmd2, int cmd3, int cmd4, int index) {

        Client client = Loader.getClient();

        client.getMenuAction1()[index] = cmd1;
        client.getMenuAction2()[index] = cmd2;
        client.getMenuAction3()[index] = cmd3;
        if (Game.hasAction4()) {
            client.getMenuAction4()[index] = cmd4;
        }
        client.getMenuActionId()[index] = action;


        client.doAction(index);
    }
}
