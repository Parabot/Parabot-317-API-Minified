package org.rev317.min.api.wrappers;

/**
 * @author Everel
 */
public class TilePath {

    private Tile[] tiles;

    public TilePath(Tile[] tiles) {
        this.tiles = tiles;
    }

    /**
     * Gets this path's tiles
     *
     * @return path tiles
     */
    public final Tile[] getTiles() {
        return tiles;
    }

    /**
     * Gets next tile to walk to
     *
     * @return tile
     */
    public final Tile getNextTile() {
        Tile next = null;
        for (int x = 0; x < tiles.length; x++) {
            if (tiles[x].isOnMinimap()) {
                next = tiles[x];
            }
        }
        return next;
    }

    /**
     * Determines if this path can be walked down
     *
     * @return <b>true</b> if path is valid
     */
    public final boolean isValid() {
        return getNextTile() != null;
    }

    /**
     * Determines if player has reached end of path
     *
     * @return <b>true</b> if player has reached end of path
     */
    public final boolean hasReached() {
        return tiles[tiles.length - 1].distanceTo() < 5;
    }

    /**
     * Walks down the path
     */
    public final void traverse() {
        final Tile next = getNextTile();
        if (next == null) {
            return;
        }
        next.walkTo();
    }

    /**
     * Reverses the current path
     *
     * @return The reversed path
     */
    public final TilePath reverse() {
        Tile[] newTiles = new Tile[tiles.length];
        for (int i = 0; i < tiles.length; i++)
            newTiles[i] = tiles[tiles.length - i - 1];
        return new TilePath(newTiles);
    }

}

