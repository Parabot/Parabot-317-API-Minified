package org.rev317;

import java.applet.Applet;
import java.io.File;
import java.net.URL;

import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.accessors.Client;

/**
 * 
 * @author Everel
 *
 */
@ServerManifest(author = "Everel", name = "Local Client", type = Type.INJECTION, version = 0.1)
public class Loader extends ServerProvider {
	private Applet applet;

	@Override
	public Applet fetchApplet() {
		try {
			final Context context = Context.getInstance();
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass("client");
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
		try {
			return new File("D:/317client.jar").toURI().toURL();
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
	
	public static Client getClient() {
		return (Client) Context.getInstance().getClient();
	}

}
