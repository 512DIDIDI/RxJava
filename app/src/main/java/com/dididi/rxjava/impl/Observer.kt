package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 观察者
 * createTime:2020-09-25
 */

interface Observer<in T>{
    /**
     *
     */
    fun onNext(t:T){}

    /**
     * 事件序列完成后，回调
     */
    fun onCompleted(){}

    /**
     * 事件序列出现异常，终止序列，并回调
     */
    fun onError(t:Throwable){}
}

