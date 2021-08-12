package com.xuan.spring.utils.extp.plugin2;

import java.util.HashSet;
import java.util.Set;

import com.xuan.spring.utils.extp.sdk.SayHelloExt1;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/7/27
 */
@Component
public class SayHelloExt1Impl implements SayHelloExt1 {

    @Override
    public String getMyName() {
        return "plugin2:名字_123";
    }

    @Override
    public Set<String> supportedBizCodes() {
        Set<String> bizCodeSet = new HashSet<>();
        bizCodeSet.add("plugin2");
        return bizCodeSet;
    }
}
