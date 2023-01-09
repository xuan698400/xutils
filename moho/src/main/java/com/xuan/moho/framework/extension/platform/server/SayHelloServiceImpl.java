package com.xuan.moho.framework.extension.platform.server;

import com.xuan.moho.framework.extension.framework.core.ExtensionInvoker;
import com.xuan.moho.framework.extension.framework.model.BizInstance;
import com.xuan.moho.framework.extension.platform.sdk.AgeExtension;
import com.xuan.moho.framework.extension.platform.sdk.NameExtension;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String fromName, String bizCode) {
        String result = "收到...fromName:[" + fromName + "]的打招呼";

        //需要决定是哪个业务，影响后续扩展点实现的寻找
        BizInstance bizInstance = new BizInstance();
        bizInstance.setBizCode(bizCode);

        result = result + "我是业务：" + bizCode + "...";

        //执行扩展点1
        ExtensionInvoker<NameExtension> nameExtensionInvoker = new ExtensionInvoker<>(NameExtension.class);
        String bizName = nameExtensionInvoker.executeFirst(bizInstance, ext -> ext.getName());
        result = result + "我叫：" + bizName + "...";

        //执行扩展点2
        ExtensionInvoker<AgeExtension> ageExtensionInvoker = new ExtensionInvoker<>(AgeExtension.class);
        String bizAge = ageExtensionInvoker.executeFirst(bizInstance, ext -> ext.getAge());
        result = result + "我的年龄：" + bizAge + "...";

        return result;
    }

}
