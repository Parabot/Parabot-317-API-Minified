package org.rev317.min.api.wrappers;

import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;

/**
 * @author Everel
 */
public class Item {
    private int id, stackSize;
    private int slot;

    public Item(int id, int stackSize, int slot) {
        this.id = id;
        this.stackSize = stackSize;
        this.slot = slot;
    }

    /**
     * Get id of this item
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets quantity of this item
     *
     * @return stack size
     */
    public int getStackSize() {
        return stackSize;
    }

    /**
     * Item slot
     *
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Drops this item
     */
    public void drop() {
        Menu.drop(this);
    }

    /**
     * Interacts with this item
     *
     * @param option
     */
    public void interact(Items.Option option) {
        Menu.interact(this, option);
    }

    /**
     *
     * @param option
     * @param interfaceParentId
     */
    public void transform(Items.Option option, int interfaceParentId) {
        Menu.transformItem(this, option, interfaceParentId);
    }

    /**
     * Interacts with this item
     *
     * @deprecated
     *
     * @param i
     */
    public void interact(int i) {
        Menu.interact(this, i);
    }

    /**
     * Interacts with this item
     *
     * @deprecated
     *
     * @param s
     */
    public void interact(String s){
        Menu.interact(this, s);
    }

    /**
     * @deprecated
     *
     * @param actionIndex
     * @param interfaceParentId
     */
    public void transform(int actionIndex, int interfaceParentId) {
        Menu.transformItem(this, actionIndex, interfaceParentId);
    }
}
