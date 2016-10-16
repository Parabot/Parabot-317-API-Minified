package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.wrappers.Item;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Everel, JKetelaar, Fryslan
 */
public class Inventory {

    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

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
     * Clears the inventory except for certain item ids
     *
     * @param ids The ids that should not be cleared
     */
    public static void clearExcept(int... ids) {
        for (Item item : getItems()) {
            for (int id : ids) {
                if (item.getId() != id) {
                    item.drop();
                }
            }
        }
    }

    /**
     * Gets inventory interface
     *
     * @return interface of inventory
     */
    public static Interface getInterface() {
        return Loader.getClient().getInterfaceCache()[settings.get("inventory_index")];
    }

    /**
     * Gets the amount of items in inventory, excludes the stack sizes
     *
     * @return amount of items
     */
    public static int getCount() {
        return getCount(false);
    }

    /**
     * Gets the amount of items with given ids in inventory, excludes the stack sizes
     *
     * @param ids
     *
     * @return amount of items
     */
    public static int getCount(int... ids) {
        return getCount(false, ids);
    }

    /**
     * Gets the amount of items in inventory
     *
     * @param includeStack - true for including stack sizes to the counting
     *
     * @return amount of items
     */
    public static int getCount(final boolean includeStack) {
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
    public static int getCount(final boolean includeStack, int... ids) {
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
    public static Item[] getItems() {
        return getItems(ALL_FILTER);
    }

    /**
     * Gets all items with given ids
     *
     * @param ids
     *
     * @return items
     */
    public static Item[] getItems(final int... ids) {
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
    public static Item[] getItems(final Filter<Item> filter) {
        final Interface inventory = getInterface();
        if (inventory == null) {
            return null;
        }
        final int[] items = inventory.getItems();
        final int[] stackSizes = inventory.getStackSizes();
        final ArrayList<Item> invItems = new ArrayList<>(28);
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
    public static boolean isFull() {
        return Inventory.getCount() == 28;
    }

    /**
     * Determines if inventory is empty
     *
     * @return <b>true</b> if inventory is empty, otherwise <b>false</b>
     */
    public static boolean isEmpty() {
        return Inventory.getCount() == 0;
    }

    /**
     * Checks if an item exists in the inventory
     *
     * @param id The item id that will be looked for
     *
     * @return True if the item exists in the inventory
     */
    public static boolean contains(int... id) {
        return getCount(id) > 0;
    }

    @Deprecated
    public static boolean containts(int... id) {
        return contains(id);
    }

    /**
     * Returns the item in the inventory based on the given id
     *
     * @param id The item id that will be used to find the item in the inventory
     *
     * @return First found item in the inventory
     */
    public static Item getItem(int id) {
        for (Item i : getItems(id)) {
            if (i != null) {
                return i;
            }
        }
        return null;
    }

    /**
     * Combines two items by using it on each other
     *
     * @param itemOne The first item id that will be used to combine
     * @param itemTwo The second item id that will be used to combine
     *
     * @return True if nothing unexpected happened
     */
    public static boolean combine(int itemOne, int itemTwo) {
        Item io = getItem(itemOne);
        Item it = getItem(itemTwo);

        if (io != null) {
            if (it != null) {
                io.interact(Items.Option.USE);
                Time.sleep(50, 100);
                it.interact(Items.Option.USE_WITH);
                Time.sleep(50, 100);
                return true;
            }
        }
        return false;
    }

    /**
     * Combines two items by using it on each other
     *
     * @param itemOne The first item id that will be used to combine
     * @param itemTwo The second item id that will be used to combine
     * @param sleepCondition The sleep condition that will be used to check and wait until the condition is valid
     *
     * @return True if the condition was true and nothing unexpected happened
     */
    public static boolean combine(int itemOne, int itemTwo, SleepCondition sleepCondition) {
        Item io = getItem(itemOne);
        Item it = getItem(itemTwo);

        if (io != null) {
            if (it != null) {
                io.interact(Items.Option.USE);
                Time.sleep(50, 100);
                it.interact(Items.Option.USE_WITH);
                Time.sleep(50, 100);
                sleepCondition.isValid();
                return true;
            }
        }
        return false;
    }
}
