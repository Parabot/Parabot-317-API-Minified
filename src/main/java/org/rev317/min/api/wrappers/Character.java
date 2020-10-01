package org.rev317.min.api.wrappers;

import org.parabot.core.reflect.RefClass;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.interfaces.Locatable;
import org.rev317.min.api.methods.Calculations;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.utils.Emote;

/**
 * @author Everel, EmmaStone
 */
public class Character implements Locatable {

    private final org.rev317.min.accessors.Character accessor;
    private final int index;

    public Character(org.rev317.min.accessors.Character accessor, int index) {
        this.accessor = accessor;
        this.index = index;
    }

    /**
     * Gets local region x
     *
     * @return x
     */
    public int getX() {
        return accessor.getX() >> 7;
    }

    /**
     * Gets local region y
     *
     * @return y
     */
    public int getY() {
        return accessor.getY() >> 7;
    }

    /**
     * Index of the character array
     *
     * @return index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Animation of this character
     *
     * @return animation
     */
    public int getAnimation() {
        return accessor.getAnimation();
    }

    /**
     * Location of this character
     *
     * @return location
     */
    public Tile getLocation() {
        return new Tile(Game.getBaseX() + getX(), Game.getBaseY() + getY(), Game.getPlane());
    }

    /**
     * Distance to this character
     *
     * @return distance
     */
    public int distanceTo() {
        return (int) Calculations.distanceTo(getLocation());
    }

    /**
     * Gets current health
     *
     * @return health
     */
    public final int getHealth() {
        return this.accessor.getCurrentHealth();
    }

    /**
     * Gets maximum health of this character
     *
     * @return max health
     */
    public final int getMaxHealth() {
        return this.accessor.getMaxHealth();
    }

    /**
     * Fetches loop cycle status
     *
     * @return loop cycle status
     */
    public final int getLoopCycleStatus() {
        return this.accessor.getLoopCycleStatus();
    }

    /**
     * Determines if entity is in combat
     *
     * @return <b>true</b> if entity is in combat
     */
    public boolean isInCombat() {
        return accessor.getLoopCycleStatus() > Loader.getClient()
                .getLoopCycle();
    }

    /**
     * Interacts with this character
     *
     * @param i
     *
     * @deprecated
     */
    public void interact(int i) {
        Menu.interact(this, i);
    }

    /**
     * Returns the character this character is interacting with
     *
     * @return interacting character
     */
    public final Character getInteractingCharacter() {
        int index = this.accessor.getInteractingEntity();
        if (index != -1 && index < 32768) {
            return new Npc(Loader.getClient().getNpcs()[index], index);
        } else if (index >= 32768) {
            index -= 32768;
            try {
                if (Loader.getClient().getPlayers()[index] == null) {
                    return Players.getMyPlayer();
                }
                return new Player(Loader.getClient().getPlayers()[index], index);
            } catch (Throwable t) {
                return Players.getMyPlayer();
            }
        }

        return null;
    }

    /**
     * Performs an emote
     *
     * @param emote
     */
    public void performEmote(Emote emote) {
        Menu.clickButton(emote.getAction3());
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() != -1;
            }
        }, 1500);
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accessor == null) ? 0 : accessor.hashCode());
        result = prime * result + index;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Character other = (Character) obj;
        if (accessor == null) {
            if (other.accessor != null) {
                return false;
            }
        } else if (!accessor.equals(other.accessor)) {
            return false;
        }

        return index == other.index;
    }

    public org.rev317.min.accessors.Character getAccessor() {
        return accessor;
    }
}
