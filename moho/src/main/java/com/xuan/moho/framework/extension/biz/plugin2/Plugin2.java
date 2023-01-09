package com.xuan.moho.framework.extension.biz.plugin2;

import com.xuan.moho.framework.extension.framework.model.Plugin;
import com.xuan.moho.framework.extension.platform.sdk.AgeExtension;
import com.xuan.moho.framework.extension.platform.sdk.NameExtension;

/**
 * @author xuan
 * @since 2021/2/25
 */
@Plugin(supportBizCodes = "plugin2")
public class Plugin2 {

    public NameExtension getNameExtension() {
        return new NameExtension() {
            @Override
            public String getName() {
                return "名字_plugin2";
            }
        };
    }

    public AgeExtension getAgeExtension() {
        return new AgeExtension() {
            @Override
            public String getAge() {
                return "年龄_plugin2_24";
            }
        };
    }

}
