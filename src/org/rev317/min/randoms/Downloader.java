package org.rev317.min.randoms;

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
public class Downloader {
    //TODO: Move to parabot itself

    public Downloader(){
        if (downloadRandoms()) {
            Core.verbose("Parsing random(s)...");
            parseRandoms();
        }else{
            Core.verbose("There do not seem to be any randoms for this server...");
        }
    }

    private void parseRandoms(){
        File myJar = new File(Directories.getCachePath() + "/randoms.jar");
        if (!myJar.exists() || !myJar.canRead()){
            return;
        }
        try {
            URL url = myJar.toURI().toURL();
            URL[] urls = new URL[]{url};
            String server = "ikov";
            URLClassLoader child = new URLClassLoader(urls, this.getClass().getClassLoader());
            Class<?> classToLoad = Class.forName("org.parabot.randoms.Core", true, child);
            Method method = classToLoad.getDeclaredMethod("init", String.class);
            Object instance = classToLoad.newInstance();
            Object result = method.invoke(instance, server);
            Core.verbose("Parsed random(s)!");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private boolean downloadRandoms(){
        try {
            File random = new File(Directories.getCachePath() + "/randoms.jar");
            if (random.exists()){
                Core.verbose("Random already exists, no need to download it.");
                return true;
            }
            String downloadLink = "http://sdn.parabot.org/randoms.php";
            WebUtil.downloadFile(new URL(downloadLink), random, new ProgressListener() {
                @Override
                public void onProgressUpdate(double v) {

                }

                @Override
                public void updateDownloadSpeed(double v) {

                }
            });
            return random.exists();
        } catch (Exception e) {
            return false;
        }
    }
}
