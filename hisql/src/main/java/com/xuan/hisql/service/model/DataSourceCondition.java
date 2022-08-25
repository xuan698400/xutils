package com.xuan.hisql.service.model;

import java.util.List;

/**
 * @author xuan
 * @since 2022/8/25
 */
public class DataSourceCondition {

    private List<Long> idList;

    private String connectStatus;

    private String searchKey;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(String connectStatus) {
        this.connectStatus = connectStatus;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
