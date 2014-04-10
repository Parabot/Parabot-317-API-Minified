package org.rev317.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;

public class DActions extends AbstractDebugger {
	private static boolean enabled;

	@Override
	public void paint(Graphics g) {
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		enabled = !enabled;
	}
	
	public static boolean debugActions() {
		return enabled;
	}

}
