package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

import java.awt.Graphics;

public class DInventory extends AbstractDebugger {

    private final int startX = 560, startY = 225, xMultiplier = 42, yMultiplier = 38;
    private boolean enabled;

    @Override
    public void paint(Graphics g) {
        int slot = 0;
        for (int i = 0; i < 28; i++) {
            int item;
            if ((item = getSlotItemID(i)) > 0) {
                if (i % 4 == 0 && i != 0) {
                    slot++;
                }

                int x = startX + (xMultiplier * ((i % 4)));
                int y = startY + (yMultiplier * slot);
                g.drawString("" + item, x, y);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            for (Item i : Inventory.getItems()) {
                System.out.println("ID: " + i.getId() + " Stack: " + i.getStackSize() + " Slot: " + i.getSlot());
            }
        }
    }

    public int getSlotItemID(int slot) {
        for (Item item : Inventory.getItems()) {
            if (item.getSlot() == slot) {
                return item.getId();
            }
        }

        return 0;
    }
}
