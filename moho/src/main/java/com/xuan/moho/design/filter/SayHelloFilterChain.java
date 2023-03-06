package com.xuan.moho.design.filter;

/**
 * @author xuan
 * @since 2023/3/6
 */
public class SayHelloFilterChain {

    private BaseSayHelloFilter head;

    public SayHelloFilterChain() {
        ChineseSayHelloFilter chineseSayHelloFilter = new ChineseSayHelloFilter();
        EnglishSayHelloFiter englishSayHelloFiter = new EnglishSayHelloFiter();
        JapaneseSayHelloFilter japaneseSayHelloFilter = new JapaneseSayHelloFilter();
        chineseSayHelloFilter.setNext(englishSayHelloFiter);
        englishSayHelloFiter.setNext(japaneseSayHelloFilter);
        head = chineseSayHelloFilter;
    }

    public BaseSayHelloFilter getHead() {
        return head;
    }

}
