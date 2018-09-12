package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

import java.awt.*;

/**
 * @author JKetelaar
 */
public class DPlayers extends AbstractDebugger {

    private boolean enabled = false;

    @Override
    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            if (Players.getNearest().length == 0) {
                Logger.addMessage("There are no Players close to you.");
                return;
            }
            for (Player player : Players.getNearest()) {
                System.out.println("Username: " + player.getName() + " Index: " + player.getIndex() + " Distance: " + player.distanceTo() + " Location: " + player.getLocation().toString());
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void paint(Graphics graphics) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        p.addLine("Close Players: " + Players.getNearest().length);
    }
}
