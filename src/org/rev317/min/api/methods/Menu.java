package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.wrappers.Character;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.HashMap;

/**
 * @author Everel
 */
public class Menu {
    public static final int ACTION_CLICK_BUTTON = 646;
    public static final int ACTION_DROP_ITEM = 847;
    public static final int ACTION_TAKE_ITEM = 234;

    private static HashMap<String, String> constants;

    /**
     * Interacts with a sceneobject
     *
     * @param object
     * @param actionIndex
     */
    public static void interact(SceneObject object, int actionIndex) {
        int actionId = 502;
        switch (actionIndex) {
            case 0:
                actionId = 502;
                break;
            case 1:
                actionId = 900;
                break;
            case 2:
                actionId = 113;
                break;
            case 3:
                actionId = 872;
                break;
            case 4:
                actionId = 1062;
                break;
        }
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
     * @param actionIndex
     */
    public static void interact(Character character, int actionIndex) {
        int actionId = 20;
        switch (actionIndex) {
            case 0:
                actionId = 20;
                break;
            case 1:
                actionId = 412;
                break;
            case 2:
                actionId = 225;
                break;
            case 3:
                actionId = 965;
                break;
            case 4:
                actionId = 478;
                break;
        }
        sendAction(actionId, character.getIndex(), 0, 0);
    }

    /**
     * Interacts with an item when it has the following menu Transform-1 Transform-5 Transform-10 etc..
     *
     * @param item
     * @param actionIndex
     * @param interfaceParentId
     */
    public static void transformItem(Item item, int actionIndex,
                                     int interfaceParentId) {
        int actionId = 632;
        switch (actionIndex) {
            case 0:
                actionId = 632;
                break;
            case 1:
                actionId = 78;
                break;
            case 2:
                actionId = 867;
                break;
            case 3:
                actionId = 431;
                break;
            case 4:
                actionId = 53;
                break;
        }
        sendAction(actionId, (int) item.getId() - 1, item.getSlot(),
                interfaceParentId);
    }

    /**
     * Takes grounditem from the ground
     *
     * @param item
     */
    public static void take(GroundItem item) {
        sendAction(ACTION_TAKE_ITEM, item.getId(), item.getX(), item.getY());
    }

    /**
     * Interacts with a ground item
     *
     * @param item
     * @param action
     */
    public static void interact(GroundItem item, int action) {
        int actionId = 652;
        switch (action) {
            case 0:
                actionId = 652;
                break;
            case 1:
                actionId = 567;
                break;
            case 2:
                actionId = 234;
                break;
            case 3:
                actionId = 244;
                break;
            case 4:
                actionId = 213;
                break;
        }
        sendAction(actionId, item.getId(), item.getX(), item.getY());
    }

    public static void interact(Item item, int action){
        int actionId = 447;
        switch (action){
            case 0:
                actionId = 447;
                break;
            case 1:
                actionId = 847;
                break;
            case 2:
                actionId = 1125;
                break;
            case 3:
                actionId = 1107;
                break;
        }
        sendAction(actionId, item.getSlot(), item.getId(), 3214);
    }

    public static void interact(Item item, String action){
        int actionId = 447;
        switch (action.toLowerCase()){
            case "use":
                actionId = 447;
                break;
            case "drop":
                actionId = 847;
                break;
            case "examine":
                actionId = 1125;
                break;
            case "cancel":
                actionId = 1107;
                break;
        }
        sendAction(actionId, item.getSlot(), item.getId(), 3214);
    }

    /**
     * Drops an item
     *
     * @param item
     */
    public static void drop(Item item) {
        sendAction(ACTION_DROP_ITEM, (int) item.getId() - 1, item.getSlot(),
                Inventory.INVENTORY_INDEX);
    }

    /**
     * Clicks a button
     *
     * @param id
     */
    public static void clickButton(int id) {
        sendAction(ACTION_CLICK_BUTTON, 0, 0, id);
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
        if (constants == null) {
            constants = Context.getInstance().getHookParser().getConstants();
        }

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

