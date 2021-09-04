package com.xuan.spring.utils.nodelog;

import javax.annotation.PostConstruct;

import com.xuan.mix.bt.nodelog.spi.SpiManager;
import com.xuan.spring.utils.nodelog.spi.BizResultSpi;
import com.xuan.spring.utils.nodelog.spi.JsonSerializeSpi;
import com.xuan.spring.utils.nodelog.spi.Slf4jLoggerSpi;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/4
 */
@Component
public class NodeLogInitBean {

    @PostConstruct
    public void init() {
        SpiManager.registerLoggerSpi(new Slf4jLoggerSpi());
        SpiManager.registerResultSpi(new BizResultSpi());
        SpiManager.registerSerializeSpi(new JsonSerializeSpi());
        System.out.println("NodeLogInitBean_init");
    }

}
