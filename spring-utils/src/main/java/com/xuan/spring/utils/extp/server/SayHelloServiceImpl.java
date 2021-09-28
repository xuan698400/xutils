package com.xuan.spring.utils.extp.server;

import java.util.List;

import javax.annotation.Resource;

import com.xuan.spring.utils.extp.core.BizExtMgr;
import com.xuan.spring.utils.extp.model.BizExt;
import com.xuan.spring.utils.extp.sdk.SayHelloExt1;
import com.xuan.spring.utils.extp.sdk.SayHelloExt2;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Resource
    private BizExtMgr bizExtMgr;

    @Override
    public String sayHello(String fromName, String bizCode) {
        String result = "收到...";

        List<BizExt> bizExtList1 = bizExtMgr.getBizExts(bizCode, SayHelloExt1.class);
        for (BizExt be : bizExtList1) {
            String bizMyName = ((SayHelloExt1)be).getMyName();
            result = result + "我叫：" + bizMyName + "...";
        }

        List<BizExt> bizExtList2 = bizExtMgr.getBizExts(bizCode, SayHelloExt2.class);
        for (BizExt be : bizExtList2) {
            String bizMyName = ((SayHelloExt2)be).getMyAge();
            result = result + "我叫：" + bizMyName + "...";
        }
        return result;
    }

}
