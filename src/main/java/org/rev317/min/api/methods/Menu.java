package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.wrappers.Character;
import org.rev317.min.api.wrappers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author JKetelaar, Everel
 */
public class Menu {

    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

    /**
     * Interacts with a sceneobject
     *
     * @param object
     * @param action
     */
    public static void interact(SceneObject object, SceneObjects.Option action) {
        if (Game.hasAction4()) {
            sendAction(action.getActionId(), object.getHash(), object.getLocalRegionX(), object.getLocalRegionY(), object.getId(), 0);
        } else {
            sendAction(action.getActionId(), object.getHash(), object.getLocalRegionX(), object.getLocalRegionY());
        }
    }

    /**
     * Interacts with a sceneobject
     *
     * @deprecated
     *
     * @param object
     * @param actionIndex
     */
    public static void interact(SceneObject object, int actionIndex) {
        int actionId = SceneObjects.Option.FIRST.getActionId();
        switch (actionIndex) {
            case 0:
                actionId = SceneObjects.Option.FIRST.getActionId();
                break;
            case 1:
                actionId = SceneObjects.Option.SECOND.getActionId();
                break;
            case 2:
                actionId = SceneObjects.Option.THIRD.getActionId();
                break;
            case 3:
                actionId = SceneObjects.Option.FOURTH.getActionId();
                break;
            case 4:
                actionId = SceneObjects.Option.FIFTH.getActionId();
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
     * @param action
     */
    public static void interact(Npc character, Npcs.Option action) {
        sendAction(action.getActionId(), character.getIndex(), 0, 0);
    }

    /**
     * Interacts with a character
     *
     * @param character
     * @param action
     */
    public static void interact(Player character, Players.Option action) {
        sendAction(action.getActionId(), character.getIndex(), 0, 0);
    }

    /**
     * Interacts with a character
     *
     * @deprecated
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
     * @param action
     * @param interfaceParentId
     */
    public static void transformItem(Item item, Items.Option action,
                                     int interfaceParentId) {
        sendAction(action.getActionId(), item.getId() - 1, item.getSlot(),
                interfaceParentId);
    }

    /**
     * Interacts with an item when it has the following menu Transform-1 Transform-5 Transform-10 etc..
     *
     * @deprecated
     *
     * @param item
     * @param actionIndex
     * @param interfaceParentId
     */
    public static void transformItem(Item item, int actionIndex,
                                     int interfaceParentId) {
        int actionId = Items.Option.TRANSFORM_FIRST.getActionId();
        switch (actionIndex) {
            case 0:
                actionId = Items.Option.TRANSFORM_FIRST.getActionId();
                break;
            case 1:
                actionId = Items.Option.TRANSFORM_SECOND.getActionId();
                break;
            case 2:
                actionId = Items.Option.TRANSFORM_THIRD.getActionId();
                break;
            case 3:
                actionId = Items.Option.TRANSFORM_FOURTH.getActionId();
                break;
            case 4:
                actionId = Items.Option.TRANSFORM_FIFTH.getActionId();
                break;
        }
        sendAction(actionId, item.getId() - 1, item.getSlot(),
                interfaceParentId);
    }

    /**
     * Takes grounditem from the ground
     *
     * @param item
     */
    public static void take(GroundItem item) {
        sendAction(settings.get("button_take_item"), item.getId(), item.getX(), item.getY());
    }

    /**
     * Interacts with a ground item
     *
     * @param item
     * @param action
     */
    public static void interact(GroundItem item, GroundItems.Option action) {
        sendAction(action.getActionId(), item.getId(), item.getX(), item.getY());
    }

    /**
     * Interacts with a ground item
     *
     * @deprecated
     *
     * @param item
     * @param action
     */
    public static void interact(GroundItem item, int action) {
        int actionId = GroundItems.Option.FIRST.getActionId();
        switch (action) {
            case 0:
                actionId = GroundItems.Option.FIRST.getActionId();
                break;
            case 1:
                actionId = GroundItems.Option.SECOND.getActionId();
                break;
            case 2:
                actionId = GroundItems.Option.THIRD.getActionId();
                break;
            case 3:
                actionId = GroundItems.Option.FOURTH.getActionId();
                break;
            case 4:
                actionId = GroundItems.Option.FIFTH.getActionId();
                break;
        }
        sendAction(actionId, item.getId(), item.getX(), item.getY());
    }

    public static void interact(Item item, Items.Option action){
        sendAction(action.getActionId(), item.getId() - 1, item.getSlot(), 3214);
    }

    /**
     * @deprecated
     */
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
        sendAction(actionId, item.getId() - 1, item.getSlot(), 3214);
    }

    /**
     * @deprecated
     */
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
            case "wear":
                actionId = 454;
                break;
            case "use with":
                actionId = 870;
                break;
        }
        sendAction(actionId, item.getId() - 1, item.getSlot(), 3214);
    }

    /**
     * Drops an item
     *
     * @param item
     */
    public static void drop(Item item) {
        sendAction(settings.get("button_drop_item"), item.getId() - 1, item.getSlot(),
                settings.get("inventory_index"));
    }

    /**
     * Clicks a button
     *
     * @param id
     */
    public static void clickButton(int id) {
        sendAction(settings.get("button_action_click"), 0, 0, id);
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

        try {
            Method doAction = client.getClass().getDeclaredMethod("doAction", int.class);
            doAction.setAccessible(true);

            doAction.invoke(client, 0);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


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
