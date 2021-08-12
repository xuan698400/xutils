package com.xuan.spring.utils.extp.sdk;

import com.xuan.spring.utils.extp.model.BizExt;

/**
 * @author xuan
 * @since 2021/2/25
 */
public interface SayHelloExt2 extends BizExt {
    String getMyAge();

    @Override
    default Class getExtClass() {
        return SayHelloExt1.class;
    }

}
