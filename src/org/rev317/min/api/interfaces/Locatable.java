package org.rev317.min.api.interfaces;

import org.rev317.min.api.wrappers.Tile;

/**
 * @author Everel
 */
public interface Locatable {

    /**
     * Gets location of this locatable instance
     *
     * @return location
     */
    public Tile getLocation();

    /**
     * Gets distance to this locatable instance
     *
     * @return distance from player to locatable
     */
    public int distanceTo();

}
