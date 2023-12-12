package com.xuan.xutils.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuan
 * @since 2023/11/30
 */
public abstract class BaseConvert<DOMAIN, XO> {

    public DOMAIN toDomain(XO xo) {
        if (null == xo) {
            return null;
        }
        return doToDomain(xo);
    }

    public XO toXo(DOMAIN domain) {
        if (null == domain) {
            return null;
        }

        return doToXo(domain);
    }

    public List<DOMAIN> toDomainList(List<XO> dtoList) {
        if (null == dtoList || dtoList.isEmpty()) {
            return new ArrayList<>();
        }

        List<DOMAIN> domainList = new ArrayList<>();
        for (XO dto : dtoList) {
            DOMAIN domain = toDomain(dto);
            domainList.add(domain);
        }

        return domainList;
    }

    public List<XO> toXoList(List<DOMAIN> domainList) {
        if (null == domainList || domainList.isEmpty()) {
            return new ArrayList<>();
        }

        List<XO> xoList = new ArrayList<>();
        for (DOMAIN domain : domainList) {
            XO xo = toXo(domain);
            xoList.add(xo);
        }

        return xoList;
    }

    protected DOMAIN doToDomain(XO xo) {return null;}

    protected XO doToXo(DOMAIN domain) {return null;}
}
