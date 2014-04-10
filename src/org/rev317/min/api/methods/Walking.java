package org.rev317.min.api.methods;

import org.rev317.min.Loader;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * 
 * @author Everel
 *
 */
public class Walking {
	
	public static void walkTo(Tile from, Tile to) {
		Loader.getClient().walkTo(0, 0, 0, 0, from.getRegionY(), 0, 0, to.getRegionY(), from.getRegionX(), true, to.getRegionX());
	}
	
	/**
	 * 
	 * @param tilePath
	 * @return <b>true</b> if destination reached, otherwise <b>false</b>
	 */
	public static boolean walkDown(TilePath tilePath) {
		if(tilePath.hasReached()) {
			return true;
		}
		tilePath.traverse();
		return false;
	}

}

