package com.xuan.spring.utils.nodelog.aspect;

import com.xuan.mix.bt.nodelog.LogPrinter;
import com.xuan.mix.bt.nodelog.config.NodeLogConfig;
import com.xuan.mix.bt.nodelog.model.LogLine;
import com.xuan.mix.bt.nodelog.model.NodeLogResult;
import com.xuan.mix.bt.nodelog.spi.ResultSpi;
import com.xuan.mix.bt.nodelog.spi.SpiManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2019/10/30
 */
@Aspect
@Component
public class NodeLogAspect {

    @Pointcut("@annotation(com.xuan.spring.utils.nodelog.aspect.NodeLog)")
    public void nodeLogPointcut() {
    }

    @Around("nodeLogPointcut() && @annotation(nodeLog)")
    public Object process(ProceedingJoinPoint joinPoint, NodeLog nodeLog) throws Throwable {
        LogPrinter logPrinter = LogPrinter.start(buildNodeName(joinPoint), buildConfig(nodeLog));

        Object result = null;
        Throwable throwable = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable t) {
            throwable = t;
            throw t;
        } finally {
            try {
                String status;
                String eCode = null;
                String eMsg = null;
                ResultSpi resultSpi = SpiManager.getResultSpi();

                if (null == resultSpi) {
                    status = LogLine.STATUS_SUCCESS;
                } else {
                    NodeLogResult nodeLogResult = resultSpi.parseResult(result);
                    if (null != nodeLogResult) {
                        status = nodeLogResult.isSuccess() ? LogLine.STATUS_SUCCESS : LogLine.STATUS_FAIL;
                        eCode = nodeLogResult.getResultCode();
                        eMsg = nodeLogResult.getResultMsg();
                    } else {
                        status = LogLine.STATUS_FAIL;
                        eCode = LogLine.ERROR_CODE_RESULT_NULL;
                    }
                }

                //说明业务逻辑没有捕获到异常，这里强制记录这种错误
                if (null != throwable) {
                    status = LogLine.STATUS_FAIL;
                    eCode = LogLine.ERROR_CODE_UNKONW;
                    eMsg = throwable.getMessage();
                }

                logPrinter
                    .setClazzName(joinPoint.getTarget().getClass().getName())
                    .setMethodName(joinPoint.getSignature().getName())
                    .setStatus(status)
                    .setResultCode(eCode)
                    .setResultMsg(eMsg)
                    .setThrowable(throwable)
                    .setParams(joinPoint.getArgs())
                    .setResult(result)
                    .end();

            } catch (Throwable t2) {
                //Ignore
            }
        }
    }

    private String buildNodeName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName() + "#" + joinPoint.getSignature().getName();
    }

    private NodeLogConfig buildConfig(NodeLog nodeLog) {
        NodeLogConfig config = new NodeLogConfig();
        config.setSample(nodeLog.sample());
        config.setPrintParams(nodeLog.printParams());
        config.setPrintResult(nodeLog.printResult());
        config.setPrintIfResultFail(nodeLog.printIfResultFail());
        return config;
    }

}
