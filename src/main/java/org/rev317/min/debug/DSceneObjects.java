package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import java.awt.*;

public class DSceneObjects extends AbstractDebugger {

    private boolean enabled;
    private long lastCheck = System.currentTimeMillis();
    private int cached;

    @Override
    public void paint(Graphics g) {
        if (System.currentTimeMillis() - lastCheck > 1000L) {
            lastCheck = System.currentTimeMillis();
            cached = SceneObjects.getNearest().length;
        }
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Close SceneObjects: " + cached);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            SceneObject[] objects = SceneObjects.getNearest();
            if (objects == null || objects.length == 0) {
                Logger.addMessage("There are no GameObjects around you.");
                return;
            }

            for (int i = objects.length - 1; i >= 0; i--) {
                System.out.println(
                        " ID: " + objects[i].getId() +
                                " UID: " + objects[i].getHash() +
                                " Location: " + objects[i].getLocation() +
                                " Distance: " + objects[i].distanceTo());
            }
        }
    }
}
