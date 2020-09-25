package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 被观察者
 * createTime:2020-09-25
 */

class Observable<T> private constructor(val onSubscribe: OnSubscribe<T>){
    
    companion object {
        /**
         * 创建被观察对象，OnSubscribe
         */
        fun <T> create(onSubscribe: OnSubscribe<T>):Observable<T>{
            return Observable(onSubscribe)
        }
    }

    fun subscribe(observer: Observer<T>):Observable<T>{
        onSubscribe(observer)
        return this
    }
    
}

/**
 *
 */
typealias OnSubscribe<T> = (Observer<T>) -> Unit