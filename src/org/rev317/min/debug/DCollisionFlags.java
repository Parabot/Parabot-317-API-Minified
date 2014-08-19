package org.rev317.min.debug;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;

public class DCollisionFlags extends AbstractDebugger {
    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        PaintDebugger p = Context.getInstance().getPaintDebugger();
        Tile location = Players.getMyPlayer().getLocation();
        Tile north = new Tile(location.getX(), location.getY() + 1);
        Tile south = new Tile(location.getX(), location.getY() - 1);
        Tile west = new Tile(location.getX() - 1, location.getY());
        Tile east = new Tile(location.getX() + 1, location.getY());
        int flag = Game.getCollisionFlags()[location.getRegionX()][location.getRegionY()];
        p.addLine("Collision flag: 0x" + String.format("%X", flag));
        p.addLine("Reachable: [ cur: " + location.isReachable() + ", north: " + north.isReachable() + ", south: " + south.isReachable() + ", east: " + east.isReachable() + ", west: " + west.isReachable() + " ]");
        p.addLine("Walkable: [ cur: " + location.isWalkable() + ", north: " + north.isWalkable() + ", south: " + south.isWalkable() + ", east: " + east.isWalkable() + ", west: " + west.isWalkable() + " ]");
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
