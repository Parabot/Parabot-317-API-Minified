package org.rev317.api.wrappers;

import org.rev317.accessors.SceneObjectTile;
import org.rev317.api.interfaces.Locatable;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Menu;

/**
 * 
 * @author Everel
 *
 */
public class SceneObject implements Locatable {
	public static final int TYPE_WALL = 0; // object1
	public static final int TYPE_WALLDECORATION = 1; // object2
	public static final int TYPE_GROUNDDECORATION = 2; // object3
	public static final int TYPE_GROUNDITEM = 3; // object4
	public static final int TYPE_INTERACTIVE = 4; // object5

	public SceneObjectTile accessor;
	private int type;

	public SceneObject(SceneObjectTile accessor, int type) {
		this.accessor = accessor;
		this.type = type;
	}

	/**
	 * Gets this object's hash
	 * 
	 * @return hash
	 */
	public final int getHash() {
		return accessor.getHash();
	}

	/**
	 * Gets location of this tile
	 * @return location
	 */
	public final Tile getLocation() {
		return new Tile(Game.getBaseX() + getLocalRegionX(), Game.getBaseY() + getLocalRegionY());
	}

	/**
	 * Gets region X
	 * @return region X
	 */
	public final int getLocalRegionX() {
		return accessor.getHash() & 0x7f;
	}

	/**
	 * Gets region Y
	 * @return region Y
	 */
	public final int getLocalRegionY() {
		return accessor.getHash() >> 7 & 0x7f;
	}

	/**
	 * Gets this object's id
	 * 
	 * @return object id
	 */
	public final int getId() {
		// TODO
		return -1;
	}

	/**
	 * Gets this object's type
	 * 
	 * @return type of object
	 */
	public final int getType() {
		return type;
	}

	/**
	 * Calculates distance to this object
	 * @return distance
	 */
	public final int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}
	
	/**
	 * Interacts with this object
	 * @param actionIndex
	 */
	public void interact(int actionIndex) {
		Menu.interact(this, actionIndex);
	}
	
	@Override
	public String toString() {
		return String.format("[ID: %d, X: %d, Y: %d]", getId(), getLocalRegionX(), getLocalRegionY());
	}

}
