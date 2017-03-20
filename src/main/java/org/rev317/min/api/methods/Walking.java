package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.rev317.min.Loader;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * @author Everel
 */
public class Walking {

    /**
     * Walks from tile to tile
     *
     * @param from Tile to Walk From.
     * @param to   Tile to Walk To.
     */
    public static void walkTo(Tile from, Tile to) {
        if (Context.getInstance().getServerProviderInfo().getServerName().equalsIgnoreCase("pkhonor")) {
            Loader.getClient().walkToPKH(false, false, 0, 0, 0, 0, from.getLocation().getRegionY(), 0, 0, to.getRegionY(), from.getLocation().getRegionX(), true, to.getRegionX());
        } else {
            Loader.getClient().walkTo(0, 0, 0, 0, from.getRegionY(), 0, 0, to.getRegionY(), from.getRegionX(), true, to.getRegionX());
        }
    }

    /**
     * Walks from the Player's Location to tile.
     *
     * @param to Destination Tile.
     */
    public static void walkTo(Tile to) {
        walkTo(Players.getMyPlayer().getLocation(), to);
    }

    /**
     * @param tilePath
     *
     * @return <b>true</b> if destination reached, otherwise <b>false</b>
     */
    public static boolean walkDown(TilePath tilePath) {
        if (tilePath.hasReached()) {
            return true;
        }

        tilePath.traverse();
        return false;
    }

    /**
     * Gets nearest reachable tile on minimap to given tile
     *
     * @param tile
     *
     * @return nearest reachable tile on minimap
     */
    public static Tile getNearestTileTo(Tile tile) {
        Tile loc = Players.getMyPlayer().getLocation();
        for (int i = 0; i < 1000; ++i) {
            if (tile.distanceTo() < 16 && tile.isWalkable()) {
                return tile;
            }
            tile = new Tile((loc.getX() + tile.getX()) / 2,
                    (loc.getY() + tile.getY()) / 2);
        }

        return null;
    }
}
