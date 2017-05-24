package org.rev317.min.debug;

import org.parabot.core.Core;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.environment.input.Mouse;

import java.awt.*;

public class DMouse extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        Core.getInjector().getInstance(PaintDebugger.class).addLine("Mouse: " + Core.getInjector().getInstance(Mouse.class).getPoint().toString());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;
    }
}
