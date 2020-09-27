package com.dididi.rxjava.impl

import java.lang.NullPointerException

/**
 * author: yechao
 * desc:
 * createTime:2020-09-25
 */

fun main(){
    Observable.create<String> {
        it.onNext("hello")
        it.onCompleted()
        it.onError(NullPointerException())
    }.subscribe({
        println(it)
    },{
        println("completed")
    },{
        println("$it")
    })
}