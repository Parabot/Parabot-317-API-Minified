package org.rev317.min.api.methods.utils;

/**
 * @author EmmaStone
 */
public enum Emote {
    YES(168),
    NO(169),
    BOW(164),
    ANGRY(165),
    THINK(162),
    WAVE(163),
    SHRUG(13370),
    CHEER(171),
    BECKON(167),
    LAUGH(170),
    JUMP_FOR_JOY(13366),
    YAWN(13368),
    DANCE(166),
    JIG(13363),
    SPIN(13364),
    HEADBANG(13365),
    CRY(161),
    BLOW_KISS(11100),
    PANIC(13362),
    RASPBERRY(13367),
    CLAP(172),
    SALUTE(13369),
    GOBLIN_BOW(13383),
    GOBLIN_SALUTE(13384),
    GLASS_BOX(667),
    CLIMB_ROPE(6503),
    LEAN_ON_AIR(6506),
    GLASS_WALL(666),
    ZOMBIE_WALK(18464),
    ZOMBIE_DANCE(18465),
    SCARED(15166),
    RABBIT_HOP(18686),
    SKILLCAPE_EMOTE(154);

    private final int action3;

    Emote(int action3) {
        this.action3 = action3;
    }

    public int getAction3() {
        return action3;
    }
}