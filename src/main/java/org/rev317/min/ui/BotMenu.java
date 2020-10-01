package org.rev317.min.ui;

import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.callback.MenuAction;
import org.rev317.min.debug.DActions;
import org.rev317.min.debug.DAnimation;
import org.rev317.min.debug.DBank;
import org.rev317.min.debug.DCollisionFlags;
import org.rev317.min.debug.DGroundItems;
import org.rev317.min.debug.DInterfaces;
import org.rev317.min.debug.DInventory;
import org.rev317.min.debug.DMap;
import org.rev317.min.debug.DMessages;
import org.rev317.min.debug.DMouse;
import org.rev317.min.debug.DNpcs;
import org.rev317.min.debug.DPlayers;
import org.rev317.min.debug.DSceneObjects;
import org.rev317.min.debug.DSceneObjectsGroundDec;
import org.rev317.min.debug.DSceneObjectsGroundItems;
import org.rev317.min.debug.DSceneObjectsInteractiveObj;
import org.rev317.min.debug.DSceneObjectsWallDec;
import org.rev317.min.debug.DSceneObjectsWallObj;
import org.rev317.min.debug.DSkills;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author JKetelaar, Everel
 */
public class BotMenu implements ActionListener {

    public BotMenu(JMenuBar bar) {
        PaintDebugger debugger = Context.getInstance().getPaintDebugger();

        JMenu debug = new JMenu("Debug");

        JMenu actions = new JMenu("Actions");
        JMenuItem enableActions = newItem("Enable Actions");

        JMenuItem animation = newItem("Animation");
        JMenuItem bank = newItem("Bank");
        JMenuItem flags = newItem("Collision flags");
        JMenuItem items = newItem("GroundItems");
        JMenuItem interfaces = newItem("Interfaces");
        JMenuItem inventory = newItem("Inventory");
        JMenuItem map = newItem("Map");
        JMenuItem messages = newItem("Messages");
        JMenuItem mouse = newItem("Mouse");
        JMenuItem npcs = newItem("Npcs");
        JMenu objects = new JMenu("Objects");
        JMenuItem enableAllObject = newItem("Enable All Objects");
        JMenuItem groundDecorations = newItem("Ground Decorations");
        JMenuItem interactiveObjects = newItem("Interactive Objects");
        JMenuItem wallObjects = newItem("Wall Objects");
        JMenuItem wallDecorations = newItem("Wall Decorations");
        JMenuItem groundItems = newItem("Ground Items");
        JMenuItem players = newItem("Players");
        JMenuItem skills = newItem("Skills");

        debugger.addDebugger("Enable Actions", new DActions());
        debugger.addDebugger("Animation", new DAnimation());
        debugger.addDebugger("Bank", new DBank());
        debugger.addDebugger("Collision flags", new DCollisionFlags());
        debugger.addDebugger("GroundItems", new DGroundItems());
        debugger.addDebugger("Interfaces", new DInterfaces());
        debugger.addDebugger("Inventory", new DInventory());
        debugger.addDebugger("Map", new DMap());
        debugger.addDebugger("Messages", new DMessages());
        debugger.addDebugger("Mouse", new DMouse());
        debugger.addDebugger("Npcs", new DNpcs());
        debugger.addDebugger("Enable All Objects", new DSceneObjects());
        debugger.addDebugger("Ground Decorations", new DSceneObjectsGroundDec());
        debugger.addDebugger("Interactive Objects", new DSceneObjectsInteractiveObj());
        debugger.addDebugger("Wall Objects", new DSceneObjectsWallObj());
        debugger.addDebugger("Wall Decorations", new DSceneObjectsWallDec());
        debugger.addDebugger("Ground Items", new DSceneObjectsGroundItems());
        debugger.addDebugger("Players", new DPlayers());
        debugger.addDebugger("Skills", new DSkills());

        debug.add(actions);
        actions.add(enableActions);
        debug.add(animation);
        debug.add(bank);
        debug.add(flags);
        debug.add(items);
        debug.add(interfaces);
        debug.add(inventory);
        debug.add(map);
        debug.add(messages);
        debug.add(mouse);
        debug.add(npcs);
        debug.add(objects);
        objects.add(enableAllObject);
        objects.addSeparator();
        objects.add(groundDecorations);
        objects.add(groundItems);
        objects.add(wallDecorations);
        objects.add(wallObjects);
        objects.add(interactiveObjects);
        debug.add(players);
        debug.add(skills);

        actions.addSeparator();

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < MenuAction.getOutputs().length; i++) {
            final int index = i;
            JMenuItem debugOutput = new JRadioButtonMenuItem("Output: " + index);
            debugOutput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuAction.setCurrentOutputIndex(index);

                }
            });
            group.add(debugOutput);

            if (index == 0) {
                group.setSelected(debugOutput.getModel(), true);
            }

            actions.add(debugOutput);
        }

        bar.add(debug);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Context.getInstance().getPaintDebugger().toggle(e.getActionCommand());
    }

    private JMenuItem newItem(String name) {
        JMenuItem item = new JCheckBoxMenuItem(name);
        item.addActionListener(this);

        return item;
    }
}
