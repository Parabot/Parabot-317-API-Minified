package org.rev317.min.callback;

import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.events.GameActionEvent;
import org.rev317.min.api.methods.Game;
import org.rev317.min.debug.DActions;
import org.rev317.min.script.ScriptEngine;

/**
 * @author Everel
 * @author Matt123337
 */
public class MenuAction {

    public static void intercept(int index) {
        Client client = Loader.getClient();
        int action1 = client.getMenuAction1()[index];
        int action2 = client.getMenuAction2()[index];
        int action3 = client.getMenuAction3()[index];
        int action4 = 0;
        int actionId = client.getMenuActionId()[index];
        if (DActions.debugActions()) {
            if (Game.hasAction4()) {
                action4 = client.getMenuAction4()[index];
                System.out.println(String.format("[index: %d, action1: %d, action2: %d, action3: %d, action4: %d, id: %d]", index, action1, action2, action3, action4, actionId));
            } else {
                System.out.println(String.format("[index: %d, action1: %d, action2: %d, action3: %d, id: %d]", index, action1, action2, action3, actionId));
            }
        }

        final GameActionEvent actionEvent = new GameActionEvent(actionId, action1, action2, action3, action4, index);
        ScriptEngine.getInstance().dispatch(actionEvent);
    }

}
