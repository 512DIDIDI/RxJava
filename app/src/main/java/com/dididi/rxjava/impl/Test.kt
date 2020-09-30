package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc:
 * createTime:2020-09-25
 */

fun main() {
    Observable.create<String> {
        it.onNext("create")
        it.onCompleted()
        it.onError(NullPointerException())
    }.subscribeOn(Scheduler.io())
        .observeOn(Scheduler.immediate())
        .subscribe({
            println(it)
        }, {
            println("create complete")
        }, {
            println("$it")
        })
    Observable.just("just1", "just2")
        .subscribe({
            println(it)
        }, {
            println("just complete")
        })
}