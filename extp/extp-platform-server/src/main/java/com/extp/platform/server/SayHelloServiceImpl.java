package com.extp.platform.server;

import com.extp.framework.core.ExtInvoker;
import com.extp.framework.model.BizInstance;
import com.extp.platform.sdk.SayHelloExt1;
import com.extp.platform.sdk.SayHelloExt2;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String fromName, String bizCode) {
        String result = "收到...";

        //需要决定是哪个业务
        BizInstance bizInstance = new BizInstance();
        bizInstance.setBizCode(bizCode);

        //执行扩展点1
        ExtInvoker<SayHelloExt1> ext1ExtInvoker = new ExtInvoker<>(SayHelloExt1.class);
        String bizMyName = ext1ExtInvoker.executeFirst(bizInstance, ext -> ext.getMyName());
        result = result + "我叫：" + bizMyName + "...";

        //执行扩展点2
        ExtInvoker<SayHelloExt2> ext2ExtInvoker = new ExtInvoker<>(SayHelloExt2.class);
        String bizMyAge = ext2ExtInvoker.executeFirst(bizInstance, ext -> ext.getMyAge());
        result = result + "我的年龄：" + bizMyAge + "...";

        return result;
    }

}
