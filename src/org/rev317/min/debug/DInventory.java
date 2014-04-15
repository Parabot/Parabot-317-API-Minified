package org.rev317.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

public class DInventory extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		for(Item i : Inventory.getItems()) {
			System.out.println("ID: " + i.getId() + " Stack: " + i.getStackSize() + " Slot: " + i.getSlot());
		}
	}

}
