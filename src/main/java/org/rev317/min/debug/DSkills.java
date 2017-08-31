package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Skill;

import java.awt.*;

public class DSkills extends AbstractDebugger {

    @Override
    public void toggle() {
        System.out.println("Agility level: " + Skill.AGILITY.getLevel() + " / " + Skill.AGILITY.getRealLevel());
        System.out.println("Attack level: " + Skill.ATTACK.getLevel() + " / " + Skill.ATTACK.getRealLevel());
        System.out.println("Constuction level: " + Skill.CONSTRUCTION.getLevel() + " / " + Skill.CONSTRUCTION.getRealLevel());
        System.out.println("Cooking level: " + Skill.COOKING.getLevel() + " / " + Skill.COOKING.getRealLevel());
        System.out.println("Crafting level: " + Skill.CRAFTING.getLevel() + " / " + Skill.CRAFTING.getRealLevel());
        System.out.println("Defence level: " + Skill.DEFENSE.getLevel() + " / " + Skill.DEFENSE.getRealLevel());
        System.out.println("Dungeoneering level: " + Skill.DUNGEONEERING.getLevel() + " / " + Skill.DUNGEONEERING.getRealLevel());
        System.out.println("Farming level: " + Skill.FARMING.getLevel() + " / " + Skill.FARMING.getRealLevel());
        System.out.println("Firemaking level: " + Skill.FIREMAKING.getLevel() + " / " + Skill.FIREMAKING.getRealLevel());
        System.out.println("Fishing level: " + Skill.FISHING.getLevel() + " / " + Skill.FISHING.getRealLevel());
        System.out.println("Fletching level: " + Skill.FLETCHING.getLevel() + " / " + Skill.FLETCHING.getRealLevel());
        System.out.println("Herblore level: " + Skill.HERBLORE.getLevel() + " / " + Skill.HERBLORE.getRealLevel());
        System.out.println("Hitpoints level: " + Skill.HITPOINTS.getLevel() + " / " + Skill.HITPOINTS.getRealLevel());
        System.out.println("Hunter level: " + Skill.HUNTER.getLevel() + " / " + Skill.HUNTER.getRealLevel());
        System.out.println("Magic level: " + Skill.MAGIC.getLevel() + " / " + Skill.MAGIC.getRealLevel());
        System.out.println("Mining level: " + Skill.MINING.getLevel() + " / " + Skill.MINING.getRealLevel());
        System.out.println("Prayer level: " + Skill.PRAYER.getLevel() + " / " + Skill.PRAYER.getRealLevel());
        System.out.println("Range level: " + Skill.RANGE.getLevel() + " / " + Skill.RANGE.getRealLevel());
        System.out.println("Runecrafting level: " + Skill.RUNECRAFTING.getLevel() + " / " + Skill.RUNECRAFTING.getRealLevel());
        System.out.println("Slayer level: " + Skill.SLAYER.getLevel() + " / " + Skill.SLAYER.getRealLevel());
        System.out.println("Smithing level: " + Skill.SMITHING.getLevel() + " / " + Skill.SMITHING.getRealLevel());
        System.out.println("Strength level: " + Skill.STRENGTH.getLevel() + " / " + Skill.STRENGTH.getRealLevel());
        System.out.println("Summoning level: " + Skill.SUMMONING.getLevel() + " / " + Skill.SUMMONING.getRealLevel());
        System.out.println("Thieving level: " + Skill.THIEVING.getLevel() + " / " + Skill.THIEVING.getRealLevel());
        System.out.println("Woodcutting level: " + Skill.WOODCUTTING.getLevel() + " / " + Skill.WOODCUTTING.getRealLevel());
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
