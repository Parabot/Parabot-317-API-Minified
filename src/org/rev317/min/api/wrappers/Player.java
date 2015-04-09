package org.rev317.min.api.wrappers;

/**
 * @author Everel
 */
public class Player extends Character {

    public Player(org.rev317.min.accessors.Player accessor, int index) {
        super(accessor, index);
    }

    public String getName(){
        return this.getName();
    }
}
