package com.xuan.xutils.base.framework.extension.biz.plugin1;

import com.xuan.xutils.base.framework.extension.framework.model.Plugin;
import com.xuan.xutils.base.framework.extension.platform.sdk.AgeExtension;
import com.xuan.xutils.base.framework.extension.platform.sdk.NameExtension;

/**
 * @author xuan
 * @since 2021/2/25
 */
@Plugin(supportBizCodes = {"plugin1", "plugin1_abc"})
public class Plugin1 {

    public NameExtension getNameExtension() {
        return new NameExtension() {
            @Override
            public String getName() {
                return "名字_plugin1";
            }
        };
    }

    public AgeExtension getAgeExtension() {
        return new AgeExtension() {
            @Override
            public String getAge() {
                return "年龄_plugin1_19";
            }
        };
    }

}
