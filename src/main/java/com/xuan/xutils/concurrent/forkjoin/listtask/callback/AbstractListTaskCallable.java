package com.xuan.xutils.concurrent.forkjoin.listtask.callback;

/**
 * 预留适配扩展，使用者最好继承这个抽象类来实现自己的业务逻辑，而非直接去实现ListTaskCallable接口
 * 万一要扩展方法什么的，也好在这里默认实现做兼容
 *
 * Created by xuan on 17/8/26.
 */
public abstract class AbstractListTaskCallable<T, R> implements ListTaskCallable<T, R> {

}
