package org.rev317.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.rev317.debug.*;

import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;


public class BotMenu implements ActionListener {
	
	public BotMenu(JMenuBar bar) {
		PaintDebugger debugger = Context.getInstance().getPaintDebugger();
		
		JMenu debug = new JMenu("Debug");
	
		JMenuItem map = newItem("Map");
		JMenuItem animation = newItem("Animation");
		JMenuItem objects = newItem("Objects");
		JMenuItem npcs = newItem("Npcs");
		JMenuItem items = newItem("GroundItems");
		JMenuItem interfaces = newItem("Interfaces");
		JMenuItem flags = newItem("Collision flags");
		JMenuItem actions = newItem("Actions");
		
		debugger.addDebugger("Actions", new DActions());
		debugger.addDebugger("Animation", new DAnimation());
		debugger.addDebugger("Map", new DMap());
		debugger.addDebugger("Objects", new DSceneObjects());
		debugger.addDebugger("Npcs", new DNpcs());
		debugger.addDebugger("GroundItems", new DGroundItems());
		debugger.addDebugger("Interfaces", new DInterfaces());
		debugger.addDebugger("Collision flags", new DCollisionFlags());
		
		debug.add(actions);
		debug.add(map);
		debug.add(animation);
		debug.add(objects);
		debug.add(npcs);
		debug.add(items);
		debug.add(interfaces);
		debug.add(flags);
		
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

