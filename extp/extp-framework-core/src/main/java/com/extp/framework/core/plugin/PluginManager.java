package com.extp.framework.core.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.extp.framework.core.plugin.model.Plugin;
import com.extp.framework.core.utils.ResourceUtil;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class PluginManager {

    private static final String PLUGIN_CONFIG = "extp-plugin.xml";

    private static final PluginManager INSTANCE = new PluginManager();

    private List<Plugin> pluginList = new ArrayList<>();

    private PluginManager() {

    }

    public static PluginManager getInstance() {
        return INSTANCE;
    }

    public synchronized void init() {
        doInit();
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

    public List<Plugin> getAllPlugin() {
        return pluginList;
    }

}
