package org.rev317.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Bank;

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
		if (!Bank.isOpen())
			return;

		for (int i = Bank.getBankItems().length - 1; i >= 0; i--) {
			System.out.println("ID: " + Bank.getBankItems()[i].getId()
					+ " Stack: " + Bank.getBankItems()[i].getStackSize()
					+ " Slot: " + Bank.getBankItems()[i].getSlot());
		}
	}

}
