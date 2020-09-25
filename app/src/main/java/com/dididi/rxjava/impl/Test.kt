package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc:
 * createTime:2020-09-25
 */

fun main(){
    Observable.create<String> {
        it.onNext("hello")
    }.subscribe(object :Observer<String>{
        override fun onNext(t: String) {
            println(t)
        }
    })
}