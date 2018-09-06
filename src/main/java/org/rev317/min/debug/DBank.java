package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.ui.Logger;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.wrappers.Item;

import java.awt.*;

public class DBank extends AbstractDebugger {

    private boolean enabled;

    @Override
    public void paint(Graphics g) {

        if (!Bank.isOpen()) {
            return;
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
            if (!Bank.isOpen()) {
                Logger.addMessage("The bank is not open, cannot debug.");
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
}
