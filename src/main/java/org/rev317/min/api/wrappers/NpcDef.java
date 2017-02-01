package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;

/**
 * @author Everel, JKetelaar, EmmaStone
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
        try {
            return (int) getLongId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private long getLongId() {
        if (accessor != null) {
            if (accessor.getId() < Integer.MAX_VALUE) {
                return (long) accessor.getId();
            }
        }

        return -1;
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
