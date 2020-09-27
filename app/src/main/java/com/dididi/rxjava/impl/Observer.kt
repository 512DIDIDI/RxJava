package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 观察者
 * createTime:2020-09-25
 */

/**
 * 设置了单独方法的回调接口，可以不必要实现[Observer]的所有方法。
 */
typealias onNextAction<T> = (t:T) -> Unit

typealias onCompletedAction = () -> Unit

typealias onErrorAction = (t:Throwable) -> Unit

interface Observer<in T>{
    /**
     * 回调结果
     */
    fun onNext(t:T)

    /**
     * 事件序列完成后，回调
     */
    fun onCompleted()

    /**
     * 事件序列出现异常，终止序列，并回调
     */
    fun onError(t:Throwable)
}

