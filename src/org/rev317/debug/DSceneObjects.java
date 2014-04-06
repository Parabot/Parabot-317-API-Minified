package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.SceneObject;

public class DSceneObjects extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		SceneObject[] objects = SceneObjects.getNearest();
		for(SceneObject object : objects) {
			System.out.println("ID: " + object.getId() + " UID: " + object.getHash() + " Location: " + object.getLocation() + " Distance: " + object.distanceTo());
		}
	}

}
