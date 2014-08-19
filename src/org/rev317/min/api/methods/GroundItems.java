package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Filter;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.accessors.Deque;
import org.rev317.min.accessors.Node;
import org.rev317.min.api.wrappers.GroundItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Everel
 */
public class GroundItems {
    private static final Comparator<GroundItem> NEAREST_SORTER = new Comparator<GroundItem>() {

        @Override
        public int compare(GroundItem n1, GroundItem n2) {
            return n1.distanceTo() - n2.distanceTo();
        }

    };
    private static final Filter<GroundItem> ALL_FILTER = new Filter<GroundItem>() {

        @Override
        public boolean accept(GroundItem item) {
            return true;
        }

    };
    private static Client client;

    /**
     * Gets all loaded ground items
     *
     * @return ground item array
     */
    public final static GroundItem[] getGroundItems(
            final Filter<GroundItem> filter) {
        if (client == null) {
            client = Loader.getClient();
        }
        final ArrayList<GroundItem> items = new ArrayList<GroundItem>();
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                final GroundItem[] groundItemsAtTile = getGroundItemsAt(x, y);
                if (groundItemsAtTile != null) {
                    for (final GroundItem item : groundItemsAtTile) {
                        if (filter.accept(item)) {
                            items.add(item);
                        }
                    }
                }
            }
        }
        return items.toArray(new GroundItem[items.size()]);
    }

    /**
     * Gets a ground item
     *
     * @param x - local region x
     * @param y - local region y
     *
     * @return ground item array
     */
    public static final GroundItem[] getGroundItemsAt(final int x, final int y) {
        try {
            if (client == null) {
                client = Loader.getClient();
            }
            final Deque deque = client.getGroundItems()[Game.getPlane()][x][y];
            if (deque == null) {
                return null;
            }
            ArrayList<GroundItem> list = null;
            final Node holder = deque.getHead();
            Node curNode = holder.getNext();
            while (curNode != null && curNode != holder
                    && curNode != deque.getHead()) {
                if (list == null) {
                    list = new ArrayList<GroundItem>();
                }
                final org.rev317.min.accessors.Item groundItem = (org.rev317.min.accessors.Item) curNode;
                list.add(new GroundItem(groundItem, x, y));
                curNode = curNode.getNext();
            }
            return list.toArray(new GroundItem[list.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all ground items in game
     *
     * @return ground items
     */
    public static final GroundItem[] getGroundItems() {
        return getGroundItems(ALL_FILTER);
    }

    /**
     * Returns array of GroundItems with the first index to be the nearest
     *
     * @param filter
     *
     * @return GroundItems
     */
    public static final GroundItem[] getNearest(Filter<GroundItem> filter) {
        final GroundItem[] objects = getGroundItems(filter);
        Arrays.sort(objects, NEAREST_SORTER);
        return objects;
    }

    /**
     * Returns array of GroundItems with the first index to be the nearest
     *
     * @return GroundItems
     */
    public static final GroundItem[] getNearest() {
        return getNearest(ALL_FILTER);
    }

    /**
     * Returns nearest ground items with given id
     *
     * @param ids
     *
     * @return GroundItems
     */
    public static final GroundItem[] getNearest(final int... ids) {
        return getNearest(new Filter<GroundItem>() {

            @Override
            public boolean accept(GroundItem object) {
                for (final int id : ids) {
                    if (id == object.getId()) {
                        return true;
                    }
                }
                return false;
            }

        });
    }

}