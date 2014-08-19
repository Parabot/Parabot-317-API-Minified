package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

import java.awt.*;

public class DNpcs extends AbstractDebugger {

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void toggle() {
        for (Npc n : Npcs.getNearest()) {
            System.out.println("ID: " + n.getDef().getId() + " Distance: " + n.distanceTo() + " Location: " + n.getLocation().toString());
        }

    }

}
