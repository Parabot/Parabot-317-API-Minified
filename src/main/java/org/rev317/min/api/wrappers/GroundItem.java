package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.*;

/**
 * @author Everel
 */
public class GroundItem implements Locatable {
    private org.rev317.min.accessors.Item accessor;
    private int x;
    private int y;

    public GroundItem(org.rev317.min.accessors.Item accessor, int x, int y) {
        this.accessor = accessor;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets location of this ground item
     *
     * @return location
     */
    public Tile getLocation() {
        return new Tile(Game.getBaseX() + x, Game.getBaseY() + y);
    }

    /**
     * Gets region X
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets region Y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Interacts with this ground item
     *
     * @param option
     */
    public void interact(GroundItems.Option option) {
        Menu.interact(this, option);
    }

    /**
     * Interacts with this ground item
     *
     * @deprecated
     *
     * @param actionIndex
     */
    public void interact(int actionIndex) {
        Menu.interact(this, actionIndex);
    }

    /**
     * Takes this item from the ground
     */
    public void take() {
        Menu.take(this);
    }

    /**
     * Gets distance between you and this ground item
     *
     * @return distance
     */
    public int distanceTo() {
        return (int) Calculations.distanceTo(getLocation());
    }

    /**
     * Get's ID of this item
     *
     * @return ID
     */
    public int getId() {
        return accessor.getId();
    }

    /**
     * Gets the accessor class
     *
     * @return RefClass of accessor
     */
    public RefClass getRefClass() {
        return new RefClass(this.accessor);
    }

}
