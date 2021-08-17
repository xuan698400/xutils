package com.extp.plugin1;

import com.extp.platform.sdk.NameExt;

/**
 * @author xuan
 * @since 2021/7/27
 */
public class NameExtImplByPlugin1 implements NameExt {
    @Override
    public String getName() {
        return "plugin1_名字";
    }

}
