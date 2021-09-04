package com.xuan.spring.utils.extp.plugin1;

import java.util.HashSet;
import java.util.Set;

import com.xuan.spring.utils.extp.sdk.SayHelloExt1;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/7/27
 */
//@Component
public class SayHelloExt1Impl implements SayHelloExt1 {

    @Override
    public String getMyName() {
        return "plugin1:名字_abc";
    }

    @Override
    public Set<String> supportedBizCodes() {
        Set<String> bizCodeSet = new HashSet<>();
        bizCodeSet.add("plugin1");
        return bizCodeSet;
    }
    
}
