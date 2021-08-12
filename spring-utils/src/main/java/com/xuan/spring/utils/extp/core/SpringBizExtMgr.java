package com.xuan.spring.utils.extp.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.xuan.spring.utils.extp.model.BizExt;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 使用Spring实现扩展点管理
 *
 * @author xuan
 * @since 2019/11/19
 */
@Component("springBizExtMgr")
public class SpringBizExtMgr implements ApplicationContextAware, BizExtMgr {
    private ApplicationContext applicationContext;

    /**
     * 所有扩展点实例，其中key规则是：扩展点Class的SimpleName + bizCode（例如：StoreInsertExt_bizCode）, value是对应实例列表
     */
    private Map<String, List<BizExt>> bizExtMap;

    @PostConstruct
    private void init() {
        bizExtMap = new ConcurrentHashMap<>();

        //获取所有扩展点实例
        Map<String, BizExt> exts = applicationContext.getBeansOfType(BizExt.class);
        if (null == exts) {
            return;
        }

        for (Entry<String, BizExt> entry : exts.entrySet()) {
            BizExt bizExt = entry.getValue();

            Class extClass = bizExt.getExtClass();
            if (null == extClass) {
                throw new IllegalArgumentException(
                    "SpringBizExtMgr_getExtInterface. extClass is null. clazz:" + bizExt.getClass()
                        .getSimpleName());
            }

            Set<String> supportedBizCodes = bizExt.supportedBizCodes();
            if (null == supportedBizCodes || supportedBizCodes.isEmpty()) {
                continue;
            }

            for (String bizCode : supportedBizCodes) {
                String bizKey = buildExtKey(extClass, bizCode);
                List<BizExt> bizExtList = bizExtMap.computeIfAbsent(bizKey, k -> new ArrayList<>());
                bizExtList.add(bizExt);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public List<BizExt> getBizExts(String bizCode, Class extInterface) {
        return bizExtMap.get(buildExtKey(extInterface, bizCode));
    }

    private String buildExtKey(Class extClass, String bizCode) {
        return extClass.getSimpleName() + "_" + bizCode;
    }

}
