package org.rev317.min.api.methods;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.wrappers.Item;

import java.util.ArrayList;


/**
 * @author Everel
 */
public class Inventory {
    public static final int INVENTORY_INDEX = 3214;

    private static final Filter<Item> ALL_FILTER = new Filter<Item>() {

        @Override
        public boolean accept(Item i) {
            return true;
        }

    };

    /**
     * Clears the inventory
     */
    public static void clear() {
        for (Item item : Inventory.getItems()) {
            item.drop();
            Time.sleep(60, 80);
        }
    }

    /**
     * Gets inventory interface
     *
     * @return interface of inventory
     */
    public static Interface getInterface() {
        return Loader.getClient().getInterfaceCache()[INVENTORY_INDEX];
    }

    /**
     * Gets the amount of items in inventory, excludes the stack sizes
     *
     * @return amount of items
     */
    public static final int getCount() {
        return getCount(false);
    }

    /**
     * Gets the amount of items with given ids in inventory, excludes the stack sizes
     *
     * @param ids
     *
     * @return amount of items
     */
    public static final int getCount(int... ids) {
        return getCount(false, ids);
    }

    /**
     * Gets the amount of items in inventory
     *
     * @param includeStack - true for including stack sizes to the counting
     *
     * @return amount of items
     */
    public static final int getCount(final boolean includeStack) {
        final Interface inventory = getInterface();
        if (inventory == null) {
            return -1;
        }
        int count = 0;
        final int[] items = inventory.getItems();
        final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
        for (int i = 0; i < items.length; i++) {
            if (items[i] > 0) {
                count += includeStack ? stackSizes[i] : 1;
            }
        }
        return count;
    }

    /**
     * Gets the amount of items with given ids in inventory
     *
     * @param includeStack - true for including stack sizes to the counting
     * @param ids
     *
     * @return amount of items
     */
    public static final int getCount(final boolean includeStack, int... ids) {
        final Interface inventory = getInterface();
        if (inventory == null) {
            return -1;
        }
        int count = 0;
        final int[] items = inventory.getItems();
        final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
        for (int i = 0; i < items.length; i++) {
            final int itemId = items[i];
            if (itemId > 0) {
                for (final int id : ids) {
                    if (id == itemId) {
                        count += includeStack ? stackSizes[i] : 1;
                        break;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Gets every item in inventory
     *
     * @return items
     */
    public static final Item[] getItems() {
        return getItems(ALL_FILTER);
    }

    /**
     * Gets all items with given ids
     *
     * @param ids
     *
     * @return items
     */
    public static final Item[] getItems(final int... ids) {
        return getItems(new Filter<Item>() {

            @Override
            public boolean accept(Item e) {
                for (int id : ids) {
                    if (e.getId() == id) {
                        return true;
                    }
                }
                return false;
            }

        });
    }

    /**
     * Gets all items accepted by filter
     *
     * @param filter
     *
     * @return items
     */
    public static final Item[] getItems(final Filter<Item> filter) {
        final Interface inventory = getInterface();
        if (inventory == null) {
            return null;
        }
        final int[] items = inventory.getItems();
        final int[] stackSizes = inventory.getStackSizes();
        final ArrayList<Item> invItems = new ArrayList<Item>(28);
        for (int i = 0; i < items.length; i++) {
            final int itemId = items[i];
            if (itemId < 1) {
                continue;
            }
            final int stackSize = stackSizes[i];
            final Item item = new Item(itemId, stackSize, i);
            if (filter.accept(item)) {
                invItems.add(item);
            }
        }
        return invItems.toArray(new Item[invItems.size()]);
    }

    /**
     * Determines if inventory is full
     *
     * @return <b>true</b> if inventory is full, otherwise <b>false</b>
     */
    public static final boolean isFull() {
        return Inventory.getCount() == 28;
    }

    /**
     * Determines if inventory is empty
     *
     * @return <b>true</b> if inventory is empty, otherwise <b>false</b>
     */
    public static final boolean isEmpty() {
        return Inventory.getCount() == 0;
    }

}

