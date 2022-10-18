package com.xuan.mix.biz.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.xuan.mix.biz.account.adapter.AccountAdapter;
import com.xuan.mix.biz.account.utils.PasswordHash;
import com.xuan.mix.biz.account.utils.SecurityUtils;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class AccountLogin {
    private final static long LOGIN_TIMEOUT = 24 * 60 * 60 * 1000;

    private AccountAdapter accountAdapter;

    private Account account;

    public static AccountLogin of(AccountAdapter accountAdapter, Account account) {
        checkNull(accountAdapter, "账号适配器[accountAdapter]不能为NULL");
        checkNull(account, "账号[account]不能为NULL");
        checkEmpty(account.getLoginName(), "用户名[account.loginName]不能为空");
        checkEmpty(account.getPassword(), "密码[password.password]不能为空");

        AccountLogin accountLogin = new AccountLogin();
        accountLogin.accountAdapter = accountAdapter;
        accountLogin.account = account;
        return accountLogin;
    }

    public void register() {
        accountAdapter.addAccount(account);
    }

    public void login() {
        Account oldAccount = accountAdapter.getAccount(account.getLoginName());
        if (null == oldAccount) {
            throw new AccountException(AccountException.CODE_DO_LOGIN_FAIL, "登录用户[" + account.getLoginName() + "]不存在");
        }

        if (!PasswordHash.validatePassword(account.getPassword(), oldAccount.getPassword())) {
            throw new AccountException(AccountException.CODE_DO_LOGIN_FAIL, "账号密码不匹配");
        }

        accountAdapter.saveToken(account.getLoginName(), UUID.randomUUID().toString());
    }

    public void checkLogin(Map<String, String> paramMap) {
        checkNull(paramMap, "参数[paramMap]不能为空");

        Account oldAccount = accountAdapter.getAccount(account.getLoginName());
        if (null == oldAccount) {
            throw new AccountException(AccountException.CODE_CHECK_LOGIN_FAIL, "用户[" + account.getLoginName() + "]不存在");
        }

        String token = oldAccount.getToken();
        if (null == token || token.trim().length() == 0) {
            throw new AccountException(AccountException.CODE_CHECK_LOGIN_FAIL,
                "用户[" + account.getLoginName() + "]登录token不存在");
        }

        String sign = paramMap.get("signature");
        checkNull(paramMap, "参数[paramMap.sign]不能为空");

        String timestamp = paramMap.get("timestamp");
        checkNull(paramMap, "参数[paramMap.timestamp]不能为空");

        //登录超时校验
        long newtimestamp = System.currentTimeMillis();
        if (Math.abs(newtimestamp - Long.parseLong(timestamp)) > LOGIN_TIMEOUT) {
            throw new AccountException(AccountException.CODE_CHECK_LOGIN_FAIL, "登陆超时");
        }

        List<String> paramList = new ArrayList<>();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if ("signature".equals(name)) {
                continue;
            }
            paramList.add(value);
        }
        paramList.add(token);
        Collections.sort(paramList);
        StringBuilder builder = new StringBuilder();
        for (String str : paramList) {
            builder.append(str);
        }

        String signNow = SecurityUtils.encodeByMD5(builder.toString());
        if (!signNow.equals(sign)) {
            throw new AccountException(AccountException.CODE_CHECK_LOGIN_FAIL, "token校验失败");
        }
    }

    private static void checkNull(Object obj, String msg) {
        if (null == obj) {
            throw new AccountException(AccountException.CODE_PARAM_INVALID, msg);
        }
    }

    private static void checkEmpty(String str, String msg) {
        if (null == str || str.trim().length() == 0) {
            throw new AccountException(AccountException.CODE_PARAM_INVALID, msg);
        }
    }

}
