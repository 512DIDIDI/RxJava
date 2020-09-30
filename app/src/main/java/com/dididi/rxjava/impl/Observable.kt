package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 被观察者
 * createTime:2020-09-25
 */

/**
 * Observer订阅列表
 */
typealias OnSubscribe<T> = (Observer<T>) -> Unit

/**
 * 被观察者。
 */
class Observable<T> private constructor(private val subscribeList: List<OnSubscribe<T>>) {

    companion object {
        /**
         * 创建被观察对象，OnSubscribe
         */
        fun <T> create(onSubscribe: OnSubscribe<T>): Observable<T> {
            return Observable(listOf(onSubscribe))
        }

        /**
         * from:将传入的数组或iterable拆分成具体对象并依次发送
         */
        fun <T> from(iterable: Iterable<T>): Observable<T> {
            return Observable(iterable.map<T, OnSubscribe<T>> { element ->
                { observer ->
                    observer.onNext(element)
                }
            }.toMutableList().apply {
                add { observer ->
                    observer.onCompleted()
                }
            })
        }

        fun <T> from(arrays: Array<T>): Observable<T> {
            return just(*arrays)
        }

        /**
         * 同from作用相似，不过作用于可变长参数
         */
        fun <T> just(vararg t: T): Observable<T> {
            return from(t.asList())
        }
    }

    /**
     * 被观察者的行为处于什么线程(耗时操作的线程)
     */
    fun subscribeOn(scheduler: Scheduler): Observable<T> {
        return this
    }

    /**
     * 观察者回调处于什么线程(回调操作的线程)
     */
    fun observeOn(scheduler: Scheduler): Observable<T> {
        return this
    }

    fun subscribe(observer: Observer<T>): Observable<T> {
        subscribeList.forEach { it(observer) }
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
        subscribeList.forEach { it(observer) }
        return this
    }

}

