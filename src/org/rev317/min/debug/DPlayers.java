package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

import java.awt.*;

/**
 * @author JKetelaar
 */
public class DPlayers extends AbstractDebugger {
    @Override
    public void toggle() {
        for (Player player : Players.getNearest()){
            System.out.println("Username" + player.getName() + " Index: " + player.getIndex() + " Distance: " + player.distanceTo() + " Location: " + player.getLocation().toString());
        }
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
