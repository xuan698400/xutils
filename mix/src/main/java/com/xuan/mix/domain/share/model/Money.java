package com.xuan.mix.domain.share.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class Money {

    /**
     * 默认银行家算法：四舍六入，五看前一位是偶数舍，奇数进位
     * 5.54 5.5
     * 2.55 2.6
     * 1.66 1.7
     * 1.25 1.2
     * 1.06 1.1
     * -1.06 -1.1
     * -1.11 -1.1
     * -1.65 -1.6
     * -2.55 -2.6
     * -5.54 -5.5
     */
    private static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("CNY");

    private long cent;

    private Currency currency;

    public Money(Money money) {
        this(money.getCent(), money.getCurrency());
    }

    public Money(long cent, Currency currency) {
        checkNotNull(currency);
        this.currency = currency;
        this.cent = cent;
    }

    public Money(long cent) {
        this(cent, DEFAULT_CURRENCY);
    }

    public Money(BigDecimal yuan, Currency currency) {
        checkNotNull(yuan);
        checkNotNull(currency);
        this.currency = currency;
        this.cent = rounding(yuan.movePointRight(currency.getDefaultFractionDigits()));
    }

    public Money(String yuan, Currency currency) {
        this(new BigDecimal(yuan), currency);
    }

    public Money(String yuan) {
        this(yuan, DEFAULT_CURRENCY);
    }

    public BigDecimal getYuan() {
        int fractionDigits = Math.max(0, currency.getDefaultFractionDigits());
        return BigDecimal.valueOf(cent, fractionDigits);
    }

    public String getYuanString() {
        return getYuan().toString();
    }

    public String getFriendlyYuanString() {
        String amount = this.getYuan().toString();
        if (amount.endsWith(".00")) {
            return amount.substring(0, amount.length() - 3);
        } else {
            return amount;
        }
    }

    public long getCent() {
        return cent;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public Money add(Money other) {
        checkArgument(other.currency == this.currency || other.currency.equals(this.currency));
        return new Money(cent + other.cent, this.currency);
    }

    public Money subtract(Money other) {
        checkArgument(other.currency == this.currency || other.currency.equals(this.currency));
        return new Money(cent - other.cent, this.currency);
    }

    public Money multiply(BigDecimal val) {
        long newCent = BigDecimal.valueOf(this.cent).multiply(val).setScale(0, DEFAULT_ROUNDING_MODE).longValue();
        return new Money(newCent, currency);
    }

    public Money divide(BigDecimal val) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, DEFAULT_ROUNDING_MODE);
        return new Money(newCent.longValue(), currency);
    }

    public String format() {
        return currency.getSymbol() + getYuan();
    }

    private String format(String format) {
        return String.format(format, getYuan());
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Money other = (Money)obj;
        return this.cent == other.cent && this.currency.equals(other.currency);
    }

    @Override
    public String toString() {
        return format();
    }

    private static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    private static void checkArgument(boolean expr) {
        if (!expr) {
            throw new IllegalArgumentException();
        }
    }

    private long rounding(BigDecimal val) {
        return val.setScale(0, DEFAULT_ROUNDING_MODE).longValue();
    }

    public static void main(String[] args) {
        System.out.println(new Money(1000).format());
    }

}
