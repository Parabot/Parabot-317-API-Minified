package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;

/**
 * @author Everel
 */
public final class Npc extends Character {
    private org.rev317.min.accessors.Npc accessor;

    public Npc(org.rev317.min.accessors.Npc accessor, int index) {
        super(accessor, index);
        this.accessor = accessor;
    }


    /**
     * Gets the definition of this npc
     *
     * @return npc definitions
     */
    public final NpcDef getDef() {
        return new NpcDef(this.accessor.getDef());
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
     * Interacts with this character
     *
     * @param option
     */
    public void interact(Npcs.Option option) {
        Menu.interact(this, option.getActionId());
    }

}
