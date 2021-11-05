package com.xuan.spring.utils.extp.model;

import java.util.Set;

/**
 * ҵ����չ��ģ��
 *
 * @author xuan
 * @since 2019/11/5
 */
public interface BizExt {

    String SUPPORTED_BIZ_CODE_ALL = "_ALL_";
    
    /**
     * ִ�����ȼ�
     *
     * @return �ο���com.xuan.spring.sql.extp.model.PriorityEnum
     */
    default int priority() {
        return PriorityEnum.MIDDLE.getValue();
    }

    /**
     * ��֧�ֵ�ҵ��SUPPORTED_BIZ_CODE_ALL��ʾ֧������ҵ��
     *
     * @return set
     */
    Set<String> supportedBizCodes();

    /**
     * ������չ����Class
     *
     * @return ��չ��ӿ�Class
     */
    Class getExtClass();

}
