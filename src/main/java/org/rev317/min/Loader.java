package org.rev317.min;

import org.parabot.api.io.Directories;
import org.parabot.api.io.WebUtil;
import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.environment.api.utils.StringUtils;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.min.accessors.Client;
import org.rev317.min.script.ScriptEngine;
import org.rev317.min.ui.BotMenu;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Everel, JKetelaar
 */
@ServerManifest(author = "Everel & JKetelaar", name = "Server name here", type = Type.INJECTION, version = 2.1)
public class Loader extends ServerProvider {

    public static Client getClient() {
        return (Client) Core.getInjector().getInstance(Context.class).getClient();
    }

    @Override
    public Applet fetchApplet() {
        try {
            final Context        context     = Core.getInjector().getInstance(Context.class);
            final ASMClassLoader classLoader = Core.getInjector().getInstance(ASMClassLoader.class);
            final Class<?>       clientClass = classLoader.loadClass(context.getServerProvider().getServerDescription().getDetail("client"));
            Object               instance    = clientClass.newInstance();

            return (Applet) instance;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public URL getJar() {
        ServerProvider provider = Core.getInjector().getInstance(Context.class).getServerProvider();
        File           target   = new File(Directories.getCachePath(), StringUtils.toMD5(provider.getServerDescription().getServerName()) + ".jar");
        if (!target.exists()) {
            try {
                WebUtil.downloadFile(new URL(""), target);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return WebUtil.toURL(target);
    }

    @Override
    public void addMenuItems(JMenuBar bar) {
        new BotMenu(bar);
    }

    @Override
    public void injectHooks() {
        AddInterfaceAdapter.setAccessorPackage("org/rev317/min/accessors/");
        try {
            super.injectHooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initScript(Script script) {
        ScriptEngine.getInstance().setScript(script);
        ScriptEngine.getInstance().init();
    }

    @Override
    public HookFile getHookFile() {
        try {
            return new HookFile(new File(""), HookFile.TYPE_XML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void unloadScript(Script script) {
        ScriptEngine.getInstance().unload();
    }

    @Override
    public void init() {
    }
}
