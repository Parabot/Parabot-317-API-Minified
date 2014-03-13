package org.rev317.api.wrappers;

/**
 * 
 * @author Everel
 *
 */
public class NpcDef {
	private org.rev317.accessors.NpcDef accessor;
	
	public NpcDef(org.rev317.accessors.NpcDef accessor) {
		this.accessor = accessor;
	}
	
	/**
	 * Gets id of this item
	 * @return id of this item
	 */
	public int getId() {
		return accessor.getId();
	}

}

