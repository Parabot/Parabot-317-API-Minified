package org.rev317.min.randoms;

import org.parabot.core.Context;
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

    public Downloader(){
        parseRandoms();
    }

    private void parseRandoms(){
        File myJar = new File(Directories.getCachePath() + "/" + /* Context.getInstance().getServerProviderInfo().getCRC32() + "-randoms.jar"*/ "randoms.jar");
        try {
            URL url = myJar.toURI().toURL();
            URL[] urls = new URL[]{url};
            URLClassLoader child = new URLClassLoader(urls, this.getClass().getClassLoader());
            Class<?> classToLoad = Class.forName("org.parabot.randoms.Core", true, child);
            Method method = classToLoad.getDeclaredMethod("init");
            Object instance = classToLoad.newInstance();
            Object result = method.invoke(instance);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void downloadRandoms(){
        try {
            WebUtil.downloadFile(new URL("http://sdn.parabot.org/providers/getRandom.php?id=" + Context.getInstance().getServerProviderInfo().getCRC32()), new File(Directories.getCachePath() + "/" + Context.getInstance().getServerProviderInfo().getCRC32() + "-randoms.jar"), new ProgressListener() {
                @Override
                public void onProgressUpdate(double v) {

                }

                @Override
                public void updateDownloadSpeed(double v) {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
