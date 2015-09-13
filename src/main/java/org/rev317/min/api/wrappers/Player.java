package org.rev317.min.api.wrappers;

import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

/**
 * @author Everel
 */
public class Player extends Character {

    public Player(org.rev317.min.accessors.Player accessor, int index) {
        super(accessor, index);
    }

    public String getName(){
        return getAccessor().getName();
    }

    /**
     * Interacts with this character
     *
     * @param option
     */
    public void interact(Players.Option option) {
        Menu.interact(this, option);
    }
}
