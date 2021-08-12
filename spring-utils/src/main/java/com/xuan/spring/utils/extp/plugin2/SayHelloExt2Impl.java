package com.xuan.spring.utils.extp.plugin2;

import java.util.HashSet;
import java.util.Set;

import com.xuan.spring.utils.extp.sdk.SayHelloExt2;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/7/27
 */
@Component
public class SayHelloExt2Impl implements SayHelloExt2 {
    @Override
    public String getMyAge() {
        return "plugin2:年龄_23";
    }

    @Override
    public Set<String> supportedBizCodes() {
        Set<String> bizCodeSet = new HashSet<>();
        bizCodeSet.add("plugin2");
        return bizCodeSet;
    }
}
