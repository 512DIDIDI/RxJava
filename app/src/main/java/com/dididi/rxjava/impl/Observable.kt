package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 被观察者
 * createTime:2020-09-25
 */

/**
 *
 */
typealias OnSubscribe<T> = (Observer<T>) -> Unit

class Observable<T> private constructor(val onSubscribe: OnSubscribe<T>) {

    companion object {
        /**
         * 创建被观察对象，OnSubscribe
         */
        fun <T> create(onSubscribe: OnSubscribe<T>): Observable<T> {
            return Observable(onSubscribe)
        }
    }

    fun subscribe(observer: Observer<T>): Observable<T> {
        onSubscribe(observer)
        return this
    }

    /**
     * 可以处理单独行为，不必实现[Observer]的所有接口
     */
    fun subscribe(
        nextAction: onNextAction<T>? = null,
        completedAction: onCompletedAction? = null,
        errorAction: onErrorAction? = null
    ): Observable<T> {
        val observer = object : Observer<T> {
            override fun onNext(t: T) {
                nextAction?.invoke(t)
            }

            override fun onCompleted() {
                completedAction?.invoke()
            }

            override fun onError(t: Throwable) {
                errorAction?.invoke(t)
            }
        }
        onSubscribe(observer)
        return this
    }

}

