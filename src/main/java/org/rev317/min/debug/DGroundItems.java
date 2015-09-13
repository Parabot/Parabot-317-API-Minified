package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.wrappers.GroundItem;

import java.awt.*;

public class DGroundItems extends AbstractDebugger {

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void toggle() {
        GroundItem[] items = GroundItems.getNearest();
        if (items == null || items.length == 0) {
            return;
        }
        for (GroundItem item : items) {
            System.out.println("ID: " + item.getId() + " Location: " + item.getLocation());
        }
    }

}
