package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

import java.awt.*;

public class DNpcs extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Close NPCs: " + Npcs.getNearest().length);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            if (Npcs.getNearest().length == 0) {
                Logger.addMessage("There are no NPCs close to you.");
                return;
            }
            for (Npc n : Npcs.getNearest()) {
                System.out.println("ID: " + n.getDef().getId() + " Distance: " + n.distanceTo() + " Location: " + n.getLocation().toString() + " Index: " + n.getIndex());
            }
        }
    }
}
