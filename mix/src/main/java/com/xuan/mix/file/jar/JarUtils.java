package com.xuan.mix.file.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class JarUtils {
    public static JarInfo getJarInfo(String jarFileName) throws IOException {

        JarFile jarFile = new JarFile(new File(jarFileName));

        List<JarEntry> jarEntryList = new ArrayList<>();
        for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements(); ) {
            JarEntry jarEntry = e.nextElement();
            if (jarEntry.isDirectory()) {
                continue;
            }
            if (jarEntry.getName().contains("pom.properties")) {
                jarEntryList.add(jarEntry);
            }
        }
        if (jarEntryList.isEmpty()) {
            return new JarInfo();
        }
        JarEntry pomJarEntry = jarEntryList.get(0);

        InputStream in = jarFile.getInputStream(pomJarEntry);
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        JarInfo jarInfo = new JarInfo();
        jarInfo.setGroupId((String)properties.get("groupId"));
        jarInfo.setGroupId((String)properties.get("artifactId"));
        jarInfo.setGroupId((String)properties.get("version"));
        return jarInfo;
    }

}
