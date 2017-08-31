package org.rev317.min.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.min.api.methods.Skill;

import java.awt.*;

public class DSkills extends AbstractDebugger {

    @Override
    public void toggle() {
        for (Skill skill : Skill.values()){
            System.out.println(String.format("%s level: %d / %d", skill.getName(), skill.getLevel(), skill.getRealLevel()));
        }
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
