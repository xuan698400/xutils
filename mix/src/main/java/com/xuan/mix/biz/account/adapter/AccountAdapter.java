package com.xuan.mix.biz.account.adapter;

import com.xuan.mix.biz.account.Account;

/**
 * @author xuan
 * @since 2022/10/18
 */
public interface AccountAdapter {

    Account getAccount(String loginName);

    void addAccount(Account account);

    void saveToken(String loginName, String token);

    String getToken(String loginName);
}
