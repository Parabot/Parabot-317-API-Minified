package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;

import java.awt.*;

public class DActions extends AbstractDebugger {
    private static boolean enabled;

    public static boolean debugActions() {
        return enabled;
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void toggle() {
        enabled = !enabled;
    }
}
