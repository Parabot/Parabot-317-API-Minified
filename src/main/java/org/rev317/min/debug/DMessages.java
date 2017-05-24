package org.rev317.min.debug;

import org.parabot.core.Core;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.script.ScriptEngine;

import java.awt.*;

/**
 * @author JKetelaar
 */
public class DMessages extends AbstractDebugger implements MessageListener {
    private boolean enabled;

    private String message;
    private String sender;
    private int    type;

    @Override
    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            ScriptEngine.getInstance().addMessageListener(this);
        } else {
            ScriptEngine.getInstance().removeMessageListener(this);
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void paint(Graphics graphics) {
        PaintDebugger p = Core.getInjector().getInstance(PaintDebugger.class);
        p.addLine("Message: " + message);
        p.addLine("Sender: " + sender);
        p.addLine("Type: " + type);
    }

    @Override
    public void messageReceived(MessageEvent event) {
        message = event.getMessage();
        sender = event.getSender();
        type = event.getType();
        System.out.printf("\"%s\" from \"%s\" with type %d\n", message, sender, type);
    }
}
