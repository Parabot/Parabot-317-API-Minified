package org.rev317.min.randoms;

import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.Directories;
import org.parabot.core.io.ProgressListener;
import org.parabot.environment.api.utils.WebUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author JKetelaar
 */
public class Executer {
    /* TODO Move to client */

    public void getRandoms() {
        Core.verbose("Downloading randoms");
        downloadRandoms();
        Core.verbose("Parsing random(s)");
        parseRandoms();
    }

    private void parseRandoms() {
        File myJar = new File(Directories.getCachePath() + "/randoms.jar");
        if (!myJar.exists() || !myJar.canRead()) {
            return;
        }
        try {
            URL url = myJar.toURI().toURL();
            URL[] urls = new URL[]{url};
            String server = Context.getInstance().getServerProviderInfo().getServerName();

            URLClassLoader child = new URLClassLoader(urls, this.getClass().getClassLoader());
            Class<?> classToLoad = Class.forName("org.parabot.randoms.Core", true, child);
            Method method = classToLoad.getDeclaredMethod("init", String.class);
            Object instance = classToLoad.newInstance();
            Object result = method.invoke(instance, server);
            Core.verbose("Parsed random(s)!");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
            Core.verbose("Failed to parse random(s)...");
        }
    }

    private void downloadRandoms() {
        try {
            File random = new File(Directories.getCachePath() + "/randoms.jar");
            if (random.exists()) {
                Core.verbose("Random already exists, no need to download it.");
                return;
            }
            String downloadLink = "http://bdn.parabot.org/api/get.php?action=randoms";
            WebUtil.downloadFile(new URL(downloadLink), random, new ProgressListener() {
                @Override
                public void onProgressUpdate(double v) {

                }

                @Override
                public void updateDownloadSpeed(double v) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
