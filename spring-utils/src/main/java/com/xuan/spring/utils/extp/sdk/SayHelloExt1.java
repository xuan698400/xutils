package com.xuan.spring.utils.extp.sdk;

import com.xuan.spring.utils.extp.model.BizExt;

/**
 * @author xuan
 * @since 2021/2/25
 */
public interface SayHelloExt1 extends BizExt {

    String getMyName();

    @Override
    default Class getExtClass() {
        return SayHelloExt1.class;
    }

}
