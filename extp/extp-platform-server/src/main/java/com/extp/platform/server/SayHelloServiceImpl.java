package com.extp.platform.server;

import com.extp.framework.core.ExtInvoker;
import com.extp.framework.model.BizInstance;
import com.extp.platform.sdk.NameExt;
import com.extp.platform.sdk.AgeExt;

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
        ExtInvoker<NameExt> ext1ExtInvoker = new ExtInvoker<>(NameExt.class);
        String bizMyName = ext1ExtInvoker.executeFirst(bizInstance, ext -> ext.getName());
        result = result + "我叫：" + bizMyName + "...";

        //执行扩展点2
        ExtInvoker<AgeExt> ext2ExtInvoker = new ExtInvoker<>(AgeExt.class);
        String bizMyAge = ext2ExtInvoker.executeFirst(bizInstance, ext -> ext.getAge());
        result = result + "我的年龄：" + bizMyAge + "...";

        return result;
    }

}
