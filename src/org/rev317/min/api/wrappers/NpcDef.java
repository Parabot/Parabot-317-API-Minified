package org.rev317.min.api.wrappers;

/**
 * 
 * @author Everel
 *
 */
public class NpcDef {
	private org.rev317.min.accessors.NpcDef accessor;
	
	public NpcDef(org.rev317.min.accessors.NpcDef accessor) {
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

