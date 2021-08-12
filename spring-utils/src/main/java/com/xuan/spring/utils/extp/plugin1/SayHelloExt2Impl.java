package com.xuan.spring.utils.extp.plugin1;

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
        return "plugin1:年龄_19";
    }

    @Override
    public Set<String> supportedBizCodes() {
        Set<String> bizCodeSet = new HashSet<>();
        bizCodeSet.add("plugin1");
        return bizCodeSet;
    }

}
