package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.wrappers.GroundItem;

import java.awt.Graphics;

public class DGroundItems extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Close Ground Items: " + GroundItems.getGroundItems().length);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            GroundItem[] items = GroundItems.getNearest();
            if (items == null || items.length == 0) {
                Logger.addMessage("There are no Ground Items close to you.");
                return;
            }
            for (GroundItem item : items) {
                System.out.println("ID: " + item.getId() + " Location: " + item.getLocation());
            }
        }
    }
}
