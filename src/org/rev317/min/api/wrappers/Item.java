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
        Menu.interact(this, option.getActionId());
    }

    /**
     *
     * @param option
     * @param interfaceParentId
     */
    public void transform(Items.Option option, int interfaceParentId) {
        Menu.transformItem(this, option.getActionId(), interfaceParentId);
    }

}
