package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Filter;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.wrappers.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Everel
 */
public class Players {

    private static final Comparator<Player> NEAREST_SORTER = new Comparator<Player>() {

        @Override
        public int compare(Player p1, Player p2) {
            return p1.distanceTo() - p2.distanceTo();
        }

    };

    private static final Filter<Player> ALL_FILTER = new Filter<Player>() {

        @Override
        public boolean accept(Player p) {
            return true;
        }

    };

    /**
     * Gets all players except local player
     *
     * @param filter
     *
     * @return all players
     */
    public static final Player[] getPlayers(final Filter<Player> filter) {
        final Client client = Loader.getClient();
        ArrayList<Player> playerList = new ArrayList<Player>();
        final org.rev317.min.accessors.Player[] accPlayers = client.getPlayers();
        for (int i = 0; i < accPlayers.length; i++) {
            if (accPlayers[i] == null) {
                continue;
            }
            final Player player = new Player(accPlayers[i], i);
            if (filter.accept(player)) {
                playerList.add(player);
            }
        }
        return playerList.toArray(new Player[playerList.size()]);
    }

    /**
     * Gets all players except local player
     *
     * @return all players
     */
    public static final Player[] getPlayers() {
        return getPlayers(ALL_FILTER);
    }

    /**
     * Returns array with the first index to be the nearest player
     *
     * @param filter
     *
     * @return nearest players
     */
    public static final Player[] getNearest(final Filter<Player> filter) {
        final Player[] players = getPlayers(filter);
        Arrays.sort(players, NEAREST_SORTER);
        return players;
    }

    /**
     * Returns array with the first index to be the nearest player
     *
     * @return nearest players
     */
    public static final Player[] getNearest() {
        return getNearest(ALL_FILTER);
    }

    /**
     * Gets local player
     *
     * @return local player
     */
    public static Player getMyPlayer() {
        return new Player(Loader.getClient().getMyPlayer(), -1);
    }

}