package org.rev317.api.wrappers;

import org.rev317.api.interfaces.TileFlags;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.Walking;

/**
 * 
 * Class which holds a world location
 * 
 * @author Everel
 * 
 */
public final class Tile implements TileFlags {
	private int x;
	private int y;
	private int z;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets x
	 * 
	 * @return x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Gets y
	 * 
	 * @return y
	 */
	public final int getY() {
		return y;
	}

	public final int getRegionX() {
		return x - Game.getBaseX();
	}
	
	public final int getRegionY() {
		return y - Game.getBaseY();
	}
	
	/**
	 * Gets z/plane
	 * 
	 * @return plane
	 */
	public final int getPlane() {
		return z;
	}

	public final int distanceTo() {
		return (int) Calculations.distanceTo(this);
	}

	/**
	 * Determines if this tile is on minimap
	 * 
	 * @return whether this tile is on minimap
	 */
	public final boolean isOnMinimap() {
		return distanceTo() < 16;
	}

	@Override
	public String toString() {
		return "Tile: [" + getX() + ", " + getY() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		final Tile t = (Tile) obj;
		return t.getX() == this.getX() && t.getY() == this.getY()
				&& t.getPlane() == this.getPlane();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + this.x;
		hash = 31 * hash + this.y;
		hash = 31 * hash + this.z;
		return hash;
	}

	/**
	 * Walks to this tile
	 */
	public void walkTo() {
		Walking.walkTo(Players.getMyPlayer().getLocation(), this);
	}
	
	/**
	 * Determines if this tile is walkable
	 * @return <b>true</b> if this tile is walkable, otherwise <b>false</b>.
	 */
	public boolean isWalkable() {
		return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & 256) == 0;
	}
	
	/**
	 * 
	 * @param isObject
	 * @return
	 */
	public boolean isReachable(boolean isObject) {
		Tile current = Players.getMyPlayer().getLocation();
		return Calculations.dijkstraDist(current.getRegionX(), current.getRegionY(), getRegionX(), getRegionY(), isObjectTile()) > -1;
	}
	
	public boolean isReachable() {
		return isReachable(isObjectTile());
	}
	
	public boolean isObjectTile() {
		return (Game.getCollisionFlags()[getRegionX()][getRegionY()] & OBJECT_TILE) != 0;
	}

}
