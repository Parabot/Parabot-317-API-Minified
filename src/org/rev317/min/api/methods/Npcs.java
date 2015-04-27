package org.rev317.min.api.methods;

import org.parabot.core.Context;
import org.parabot.environment.api.utils.Filter;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.wrappers.Npc;

import java.util.*;

/**
 * @author Everel, JKetelaar
 */
public class Npcs {

    private static final Comparator<Npc> NEAREST_SORTER = new Comparator<Npc>() {

        @Override
        public int compare(Npc n1, Npc n2) {
            return n1.distanceTo() - n2.distanceTo();
        }

    };

    private static final Filter<Npc> ALL_FILTER = new Filter<Npc>() {

        @Override
        public boolean accept(Npc n) {
            return true;
        }

    };
    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

    /**
     * Gets all Npcs except local Npc
     *
     * @param filter
     *
     * @return all Npcs
     */
    public static final Npc[] getNpcs(final Filter<Npc> filter) {
        final Client client = Loader.getClient();
        ArrayList<Npc> npcList = new ArrayList<>();
        final org.rev317.min.accessors.Npc[] accNpcs = client.getNpcs();
        for (int i = 0; i < accNpcs.length; i++) {
            if (accNpcs[i] == null) {
                continue;
            }
            final Npc npc = new Npc(accNpcs[i], i);
            if (filter.accept(npc)) {
                npcList.add(npc);
            }
        }
        return npcList.toArray(new Npc[npcList.size()]);
    }

    /**
     * Gets all Npcs
     *
     * @return all Npcs
     */
    public static final Npc[] getNpcs() {
        return getNpcs(ALL_FILTER);
    }

    /**
     * Gets the closest npc which matches the given filter
     *
     * @param filter
     *
     * @return closest npc
     */
    public static final Npc getClosest(final Filter<Npc> filter) {
        Npc[] npcs = getNearest(filter);
        if (npcs == null || npcs.length == 0) {
            return null;
        }
        return npcs[0];
    }

    /**
     * Gets the closest npc which matches the given ids
     *
     * @param ids
     *
     * @return closest npc
     */
    public static final Npc getClosest(int... ids) {
        Npc[] npcs = getNearest(ids);
        if (npcs == null || npcs.length == 0) {
            return null;
        }
        return npcs[0];
    }

    /**
     * Returns array with the first index to be the nearest Npc
     *
     * @param filter
     *
     * @return nearest Npcs
     */
    public static final Npc[] getNearest(final Filter<Npc> filter) {
        final Npc[] npcs = getNpcs(filter);
        Arrays.sort(npcs, NEAREST_SORTER);
        return npcs;
    }

    /**
     * Gets nearest npcs which hold given id(s)
     *
     * @param ids
     *
     * @return array of npcs with the first index to be the nearest
     */
    public static final Npc[] getNearest(final int... ids) {
        final Npc[] npcs = getNpcs(new Filter<Npc>() {

            @Override
            public boolean accept(Npc npc) {
                for (final int id : ids) {
                    if (id == npc.getDef().getId()) {
                        return true;
                    }
                }
                return false;
            }

        });
        Arrays.sort(npcs, NEAREST_SORTER);
        return npcs;
    }

    /**
     * Returns array with the first index to be the nearest Npc
     *
     * @return nearest Npcs
     */
    public static final Npc[] getNearest() {
        return getNearest(ALL_FILTER);
    }

    public enum Option{
        FIRST(settings.get("menu_character_first_interaction")),
        ATTACK(settings.get("menu_character_first_interaction")),

        SECOND(settings.get("menu_character_second_interaction")),
        TALK_TO(settings.get("menu_character_second_interaction")),
        INTERACT(settings.get("menu_character_second_interaction")),
        BAIT(settings.get("menu_character_second_interaction")),
        CAGE(settings.get("menu_character_second_interaction")),
        NET(settings.get("menu_character_second_interaction")),

        THIRD(settings.get("menu_character_third_interaction")),
        TRADE(settings.get("menu_character_third_interaction")),
        BANK(settings.get("menu_character_third_interaction")),
        PICKPOCKET(settings.get("menu_character_third_interaction")),
        HARPOON(settings.get("menu_character_third_interaction")),
        GET_TASK(settings.get("menu_character_third_interaction")),

        FOURTH(settings.get("menu_character_fourth_interaction")),
        COLLECT(settings.get("menu_character_fourth_interaction")),
        CHANGE_CLOTHES(settings.get("menu_character_fourth_interaction")),

        FIFTH(settings.get("menu_character_fifth_interaction")),
        REWARD(settings.get("menu_character_fifth_interaction")),

        EXAMINE(settings.get("menu_character_examine"));

        int actionId;

        Option(int actionId) {
            this.actionId = actionId;
        }

        public int getActionId() {
            return actionId;
        }
    }
}
