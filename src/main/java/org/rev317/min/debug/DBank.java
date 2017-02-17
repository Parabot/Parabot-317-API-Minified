package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.wrappers.Item;

import java.awt.*;

public class DBank extends AbstractDebugger {

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void toggle() {
        if (!Bank.isOpen()) {
            return;
        }

        Item[] items;
        if ((items = Bank.getBankItems()) != null) {
            for (int i = items.length - 1; i >= 0; i--) {
                System.out.println("ID: " + items[i].getId()
                        + " Stack: " + items[i].getStackSize()
                        + " Slot: " + items[i].getSlot());
            }
        }
    }
}
