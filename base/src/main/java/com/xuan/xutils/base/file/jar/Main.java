package com.xuan.xutils.base.file.jar;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JarInfo jarInfo = JarUtils.getJarInfo(
            "/Users/xuan/.m2/repository/apache-httpclient/commons-httpclient/3.1/commons-httpclient-3.1.jar");
        System.out.println(jarInfo.getArtifactId());
        System.out.println(jarInfo.getGroupId());
        System.out.println(jarInfo.getVersion());
    }
}
