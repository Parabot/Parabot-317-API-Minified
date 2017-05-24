package org.rev317.min.debug;

import org.parabot.core.Core;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.api.methods.Players;

import java.awt.*;

public class DAnimation extends AbstractDebugger {
    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        Core.getInjector().getInstance(PaintDebugger.class).addLine("Animation: " + Players.getMyPlayer().getAnimation());
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
