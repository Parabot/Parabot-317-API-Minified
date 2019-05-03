package org.rev317.min.api.wrappers;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefMethod;
import org.rev317.min.accessors.SceneObjectTile;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;

/**
 * @author Everel
 */
public class SceneObject implements Locatable {
    public static final int TYPE_WALL             = 0; // object1
    public static final int TYPE_WALLDECORATION   = 1; // object2
    public static final int TYPE_GROUNDDECORATION = 2; // object3
    public static final int TYPE_GROUNDITEM       = 3; // object4
    public static final int TYPE_INTERACTIVE      = 4; // object5

    public  SceneObjectTile accessor;
    private int             type;

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
     * Resolves the hash depending on the API's inner SceneObjectTile getHash() methods' type.
     * <br>This is strictly to be used only by Debug classes such as {@code DSceneObjects} due to the overhead of Reflection.
     * In cases of high usage, classes should be duplicated as usual in a custom API with the required changed type.
     * @return An object, casted to either Long or Int
     */
    public final Object resolveHash() {
        Object    hash       = (int) 0;
        try {
            RefMethod hashMethod = new RefClass(Context.getInstance().getASMClassLoader().loadClass("org.rev317.min.accessors.SceneObjectTile"), accessor).getMethod("getHash");
            if (hashMethod.getReturnType() == int.class) {
                hash = (int) hashMethod.invoke();
            } else {
                hash = (long) hashMethod.invoke();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * Gets location of this tile
     *
     * @return location
     */
    public final Tile getLocation() {
        return new Tile(Game.getBaseX() + getLocalRegionX(), Game.getBaseY() + getLocalRegionY());
    }

    /**
     * Gets region X
     *
     * @return region X
     */
    public final int getLocalRegionX() {
        return accessor.getHash() & 0x7f;
    }

    /**
     * Gets region Y
     *
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
        return accessor.getHash() >> 14 & 0x7FFF;
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
     *
     * @return distance
     */
    public final int distanceTo() {
        return (int) Calculations.distanceTo(getLocation());
    }

    /**
     * Interacts with this object
     *
     * @param option
     */
    public void interact(SceneObjects.Option option) {
        Menu.interact(this, option);
    }

    /**
     * Interacts with this object
     *
     * @param actionIndex
     *
     * @deprecated
     */
    public void interact(int actionIndex) {
        Menu.interact(this, actionIndex);
    }

    /**
     * Gets the accessor class
     *
     * @return RefClass of accessor
     */
    public RefClass getRefClass() {
        return new RefClass(this.accessor);
    }

    @Override
    public String toString() {
        return String.format("[ID: %d, X: %d, Y: %d]", getId(), getLocalRegionX(), getLocalRegionY());
    }
}
