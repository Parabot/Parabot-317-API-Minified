package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Filter;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class DSceneObjectsGroundDec extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Ground decorations: " + getGroundDecorations().length);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            SceneObject[] objects = getGroundDecorations();
            if (objects == null || objects.length == 0) {
                Logger.addMessage("There are no GameObjects around you.");
                return;
            }
            java.util.List<SceneObject> objs = Arrays.asList(objects);
            Collections.sort(objs, DSceneObjects.SCENE_OBJECT_COMPARATOR_DISTANCE);
            objects = objs.toArray(new SceneObject[0]);

            for (int i = objects.length - 1; i >= 0; i--) {
                System.out.println(
                        " ID: " + objects[i].getId() +
                                " UID: " + objects[i].getHash() +
                                " Location: " + objects[i].getLocation() +
                                " Distance: " + objects[i].distanceTo());
            }
        }
    }

    private SceneObject[] getGroundDecorations() {
        return SceneObjects.getNearest(new Filter<SceneObject>() {
            @Override
            public boolean accept(SceneObject sceneObject) {
                return sceneObject.getType() == SceneObject.TYPE_GROUNDDECORATION;
            }
        });
    }
}
