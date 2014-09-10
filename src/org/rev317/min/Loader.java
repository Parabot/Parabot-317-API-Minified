package org.rev317.min;

import org.parabot.core.Context;
import org.parabot.core.Directories;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.min.accessors.Client;
import org.rev317.min.randoms.Downloader;
import org.rev317.min.script.ScriptEngine;
import org.rev317.min.ui.BotMenu;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Everel
 */
@ServerManifest(author = "Everel & Paradox", name = "Server name here", type = Type.INJECTION, version = 2.1)
public class Loader extends ServerProvider {
    private Applet applet;
    private HookFile hookFile; //= new HookFile(new File(Directories.getCachePath() + "/ikov-hooks.xml"), HookFile.TYPE_XML);

    public static Client getClient() {
        return (Client) Context.getInstance().getClient();
    }

    @Override
    public Applet fetchApplet() {
        try {
            final Context context = Context.getInstance();
            final ASMClassLoader classLoader = context.getASMClassLoader();
            final Class<?> clientClass = classLoader.loadClass("b");
            Object instance = clientClass.newInstance();
            applet = (Applet) instance;
            return applet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URL getJar() {
        //ServerProviderInfo serverProvider = Context.getInstance().getServerProviderInfo();

        File target = new File(Directories.getCachePath(), "3672181877.jar");
        if (!target.exists()) {
            //WebUtil.downloadFile(serverProvider.getClient(), target, VerboseLoader.get());
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
            try {
                this.hookFile = new HookFile(new File(Directories.getCachePath() + "/ikov-hooks.xml"), HookFile.TYPE_XML);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
            super.injectHooks();
        }
    }

    @Override
    public void initScript(Script script) {
        ScriptEngine.getInstance().setScript(script);
        ScriptEngine.getInstance().init();
    }

    @Override
    public HookFile getHookFile() {
        return this.hookFile;
    }

    public void unloadScript(Script script) {
        ScriptEngine.getInstance().unload();
    }

    @Override
    public void init() {
        new Downloader();
    }
}
