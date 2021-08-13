package com.extp.framework.core.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.extp.framework.core.log.LogAdapter;
import com.extp.framework.core.log.LogAdapterFactory;
import com.extp.framework.core.plugin.model.Plugin;
import com.extp.framework.core.utils.ResourceUtil;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class PluginManager {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    private static final String PLUGIN_CONFIG = "extp-plugin.xml";

    private static final PluginManager INSTANCE = new PluginManager();

    private static volatile boolean initializing = false;

    private static volatile boolean finished = false;

    private List<Plugin> pluginList = new ArrayList<>();

    private PluginManager() {

    }

    public static PluginManager getInstance() {
        return INSTANCE;
    }

    public synchronized void init() {

        synchronized (this) {

            //正在初始化中，循环等待
            while (initializing && !finished) {
                doWait(1000);
            }

            //已经初始化完成直接返回，防止重复初始化
            if (initializing && finished) {
                return;
            }

            if (!initializing) {
                initializing = true;
                doInit();
                finished = true;
            }
        }
    }

    private void doInit() {
        List<URL> pluginUrlList = ResourceUtil.getResources(PLUGIN_CONFIG);
        if (null == pluginUrlList || pluginUrlList.isEmpty()) {
            return;
        }
        for (URL url : pluginUrlList) {

            Plugin plugin = XmlParser.parser(getXmlFromUrl(url));
            pluginList.add(plugin);
        }
    }

    private String getXmlFromUrl(URL url) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            StringBuilder context = new StringBuilder();
            while ((line = br.readLine()) != null) {
                context.append(line);
            }
            return context.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private void doWait(long timeout) {
        try {
            wait(timeout);
        } catch (InterruptedException ie) {
            LOG.error("PluginManager_doWait_InterruptedException. timeout:" + timeout);
        }
    }

    public List<Plugin> getAllPlugin() {
        return pluginList;
    }

}
