package com.extp.plugin1;

import com.extp.framework.model.BizInstance;
import com.extp.framework.model.Plugin;
import com.extp.platform.sdk.SayHelloExt1;
import com.extp.platform.sdk.SayHelloExt2;

/**
 * @author xuan
 * @since 2021/2/25
 */
@Plugin(supportBizCodes = "abc", supportScenarios = BizInstance.DEFAULT_SCENARIO)
public class Plugin1 {

    public SayHelloExt1 getSayHelloExt1() {
        return new SayHelloExt1() {
            @Override
            public String getMyName() {
                return "名字_abc";
            }
        };
    }

    public SayHelloExt2 getSayHelloExt2() {
        return new SayHelloExt2() {
            @Override
            public String getMyAge() {
                return "年龄_19";
            }
        };
    }

}
