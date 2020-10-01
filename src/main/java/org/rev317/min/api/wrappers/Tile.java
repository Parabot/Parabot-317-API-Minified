package org.rev317.min.api.wrappers;

import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.interfaces.TileFlags;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;

/**
 * Class which holds a world location
 *
 * @author Everel
 */
public final class Tile implements TileFlags, Locatable {
    private final int x;
    private final int y;
    private int z;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Gets x
     *
     * @return x
     */
    public final int getX() {
        return x;
    }

    /**
     * Gets y
     *
     * @return y
     */
    public final int getY() {
        return y;
    }

    /**
     * Gets region x
     *
     * @return region x
     */
    public final int getRegionX() {
        return x - Game.getBaseX();
    }

    /**
     * Gets region y
     *
     * @return region y
     */
    public final int getRegionY() {
        return y - Game.getBaseY();
    }

    /**
     * Gets z/plane
     *
     * @return plane
     */
    public final int getPlane() {
        return z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int distanceTo() {
        return (int) Calculations.distanceTo(this);
    }

    /**
     * Determines if this tile is on minimap
     *
     * @return whether this tile is on minimap
     */
    public final boolean isOnMinimap() {
        return distanceTo() < 16;
    }

    @Override
    public String toString() {
        return "Tile: [" + getX() + ", " + getY() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        final Tile t = (Tile) obj;
        return t.getX() == this.getX() && t.getY() == this.getY()
                && t.getPlane() == this.getPlane();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        hash = 31 * hash + this.z;

        return hash;
    }

    /**
     * Walks to this tile
     */
    public void walkTo() {
        Walking.walkTo(Players.getMyPlayer().getLocation(), this);
    }

    /**
     * Determines if this tile is walkable
     *
     * @return <code>true</code> if this tile is walkable, otherwise <code>false</code>
     */
    public boolean isWalkable() {
        return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & 256) == 0;
    }

    /**
     * Determines if this tile is reachable
     *
     * @param isObject whether this tile is an object tile
     *
     * @return <code>true</code> if this tile is reachable, otherwise <code>false</code>
     */
    public boolean isReachable(boolean isObject) {
        Tile current = Players.getMyPlayer().getLocation();
        return Calculations.dijkstraDist(current.getRegionX(), current.getRegionY(), getRegionX(), getRegionY(), isObjectTile()) > -1;
    }

    /**
     * Determines if this tile is reachable
     *
     * @return <code>true</code> if this tile is reachable, otherwise <code>false</code>
     */
    public boolean isReachable() {
        return isReachable(isObjectTile());
    }

    /**
     * Determines if this tile is an object tile
     *
     * @return <code>true</code> if this tile is an object tile, otherwise <code>false</code>
     */
    public boolean isObjectTile() {
        return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & OBJECT_TILE) != 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile getLocation() {
        return this;
    }
}
