package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import java.awt.Graphics;
import java.util.Comparator;

public class DSceneObjects extends AbstractDebugger {

    public static final Comparator<SceneObject> SCENE_OBJECT_COMPARATOR_DISTANCE = new Comparator<SceneObject>() {
        @Override
        public int compare(SceneObject o1, SceneObject o2) {
            return Integer.compare(o1.distanceTo(), o2.distanceTo());
        }
    };
    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Close SceneObjects: " + SceneObjects.getNearest().length);
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
