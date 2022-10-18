package com.xuan.mix.biz.account.adapter;

import java.util.HashMap;
import java.util.Map;

import com.xuan.mix.biz.account.Account;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class DefaultAccountAdapter implements AccountAdapter {

    private final static Map<String, Account> DATA_MAP = new HashMap<>();

    @Override
    public void addAccount(Account account) {
        DATA_MAP.put(account.getLoginName(), account);
    }

    @Override
    public String getToken(String loginName) {
        Account account = DATA_MAP.get(loginName);
        if (null != account) {
            return account.getToken();
        }
        return null;
    }

    @Override
    public Account getAccount(String loginName) {
        return DATA_MAP.get(loginName);
    }

    @Override
    public void saveToken(String loginName, String token) {
        Account account = DATA_MAP.get(loginName);
        if (null != account) {
            account.setToken(token);
        }
    }

}
