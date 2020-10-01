package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;

import java.awt.Graphics;

public class DMap extends AbstractDebugger {
    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Location: " + Players.getMyPlayer().getLocation());
        p.addLine("Plane: " + Game.getPlane());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            System.out.println("Location: " + Players.getMyPlayer().getLocation());
            System.out.println("Plane: " + Game.getPlane());
        }
    }
}
