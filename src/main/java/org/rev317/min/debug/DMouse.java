package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.environment.input.Mouse;

import java.awt.Graphics;

public class DMouse extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        Context.getInstance().getPaintDebugger().addLine("Mouse: " + Mouse.getInstance().getPoint().toString());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            System.out.println("Mouse: " + Mouse.getInstance().getPoint().toString());
        }
    }
}
