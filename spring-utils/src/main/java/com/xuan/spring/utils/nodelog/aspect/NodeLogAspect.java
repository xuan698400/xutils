package com.xuan.spring.utils.nodelog.aspect;

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
        //LogPrinter logPrinter = LogPrinter.start(nodeLog.value());
        //
        //Object result = null;
        //Throwable throwable = null;
        //try {
        //    result = joinPoint.proceed();
        //    return result;
        //} catch (Throwable t) {
        //    throwable = t;
        //    throw t;
        //} finally {
        //    try {
        //        String status;
        //        String eCode = null;
        //        String eMsg = null;
        //        NodeLogResultParser resultParser = NodeLogConfigMgr.getResultParser();
        //        if (null == resultParser) {
        //            status = LogLine.STATUS_SUCCESS;
        //        } else {
        //            NodeLogResult nodeLogResult = resultParser.parser(result);
        //            if (null != nodeLogResult) {
        //                status = nodeLogResult.isSuccess() ? LogLine.STATUS_SUCCESS : LogLine.STATUS_FAIL;
        //                eCode = nodeLogResult.getErrorCode();
        //                eMsg = nodeLogResult.getErrorMsg();
        //            } else {
        //                status = LogLine.STATUS_FAIL;
        //                eCode = LogLine.ERROR_CODE_RESULT_NULL;
        //            }
        //        }
        //
        //        //说明业务逻辑没有捕获到异常，这里强制记录这种错误
        //        if (null != throwable) {
        //            status = LogLine.STATUS_FAIL;
        //            eCode = LogLine.ERROR_CODE_UNKONW;
        //            eMsg = throwable.getMessage();
        //        }
        //        logPrinter.end(status, eCode, eMsg, throwable, joinPoint.getArgs(), result);
        //    } catch (Throwable t2) {
        //        //Ignore
        //    }
        //}
        return null;
    }

}
