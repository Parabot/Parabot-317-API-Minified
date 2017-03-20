package org.rev317.min.ui;

import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.callback.MenuAction;
import org.rev317.min.debug.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author JKetelaar, Everel
 */
public class BotMenu implements ActionListener {

    public BotMenu(JMenuBar bar) {
        PaintDebugger debugger = Context.getInstance().getPaintDebugger();

        JMenu debug = new JMenu("Debug");

        JMenu     actions       = new JMenu("Actions");
        JMenuItem enableActions = newItem("Enable Actions");

        JMenuItem animation  = newItem("Animation");
        JMenuItem bank       = newItem("Bank");
        JMenuItem flags      = newItem("Collision flags");
        JMenuItem items      = newItem("GroundItems");
        JMenuItem interfaces = newItem("Interfaces");
        JMenuItem inventory  = newItem("Inventory");
        JMenuItem map        = newItem("Map");
        JMenuItem messages   = newItem("Messages");
        JMenuItem mouse      = newItem("Mouse");
        JMenuItem npcs       = newItem("Npcs");
        JMenuItem objects    = newItem("Objects");
        JMenuItem players    = newItem("Players");

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
        debugger.addDebugger("Objects", new DSceneObjects());
        debugger.addDebugger("Players", new DPlayers());

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
        debug.add(players);

        actions.addSeparator();

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < MenuAction.getOutputs().length; i++) {
            final int index       = i;
            JMenuItem debugOutput = new JRadioButtonMenuItem("Output: " + index);
            debugOutput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuAction.setCurrentOutputIndex(index);

                }
            });
            group.add(debugOutput);
            actions.add(debugOutput);
        }

        bar.add(debug);
    }

    private JMenuItem newItem(String name) {
        JMenuItem item = new JCheckBoxMenuItem(name);
        item.addActionListener(this);

        return item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Context.getInstance().getPaintDebugger().toggle(e.getActionCommand());
    }
}
