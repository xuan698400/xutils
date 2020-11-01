package com.xuan.mix.utils;

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
 * @since 2020/10/22
 */
public class JarUtils {

    public static JarInfo getJarInfo(String jarFileName) throws IOException {

        JarFile jarFile = new JarFile(new File(jarFileName));

        //查找Jar文件里面的pom.properties文件
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
            //未找到pom.properties文件
            return new JarInfo();
        }
        JarEntry pomJarEntry = jarEntryList.get(0);

        //读取pom信息
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

    public static class JarInfo {

        private String groupId;
        private String artifactId;
        private String version;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public void setArtifactId(String artifactId) {
            this.artifactId = artifactId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static void main(String[] args) throws Exception {
        JarInfo jarInfo = JarUtils.getJarInfo(
            "/Users/xuan/.m2/repository/apache-httpclient/commons-httpclient/3.1/commons-httpclient-3.1.jar");
        System.out.println(jarInfo.getArtifactId());
        System.out.println(jarInfo.getGroupId());
        System.out.println(jarInfo.getVersion());
    }

}
