package com.xuan.xutils.tools.cmd.core.common;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * IP工具类
 *
 * @author xuan
 * @since 2023/8/24
 */
public class IpUtils {

    private final static String UNKNOWN_HOST = "unknown-host";

    /**
     * 获取本机IP
     *
     * @return 本机IP
     */
    public static String getLocalIp() {
        try {
            InetAddress ip = Inet4Address.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UNKNOWN_HOST;
    }

}
