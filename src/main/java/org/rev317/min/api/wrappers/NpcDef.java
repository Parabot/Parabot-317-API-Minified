package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;

/**
 * @author Everel
 */
public class NpcDef {
    private org.rev317.min.accessors.NpcDef accessor;

    public NpcDef(org.rev317.min.accessors.NpcDef accessor) {
        this.accessor = accessor;
    }

    /**
     * Gets id of this item
     *
     * @return id of this item
     */
    public int getId() {
        return accessor.getId();
    }

    /**
     * Gets the accessor class
     *
     * @return RefClass of accessor
     */
    public RefClass getRefClass() {
        return new RefClass(this.accessor);
    }

}

