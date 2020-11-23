package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;

/**
 * @author Everel, JKetelaar, EmmaStone
 */
public class NpcDef {
    private final org.rev317.min.accessors.NpcDef accessor;

    public NpcDef(org.rev317.min.accessors.NpcDef accessor) {
        this.accessor = accessor;
    }

    /**
     * Gets id of this item
     *
     * @return id of this item
     */
    public int getId() {
        return getId(false);
    }

    /**
     * Gets the accessor class
     *
     * @return RefClass of accessor
     */
    public RefClass getRefClass() {
        return new RefClass(this.accessor);
    }

    /**
     * Gets id of this item
     *
     * @param avoidLong defines if it should check if long id exists
     *
     * @return id of this item
     */
    private int getId(boolean avoidLong) {
        if (avoidLong) {
            return accessor.getId();
        } else {
//            try {
//                long id = getLongId();
//                if (id > Integer.MAX_VALUE){
//                    throw new NoSuchMethodException("This server only supports long ids; change NpcDef#getId to NpcDef#getLongId");
//                }
//                return (int) id;
//            } catch (Exception e) {
            return accessor.getId();
//            }
        }
    }

    /**
     * Gets id of this item
     * Meant for servers with longs as ids, instead of ints
     *
     * @return id of this item
     */
    private long getLongId() {
        try {
            return accessor.getLongId();
        } catch (Exception e) {
            return getId(true);
        }
    }
}
