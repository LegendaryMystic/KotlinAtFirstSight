package com.sunfly.bus

import com.blankj.rxbus.RxBus
import io.reactivex.Scheduler

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/8
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class RxBusImpl : IBus {

    override fun register(subscriber: Any) {

    }

    override fun unregister(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }

    override fun post(event: Any) {
        RxBus.getDefault().post(event)
    }

    override fun postSticky(event: Any) {
        RxBus.getDefault().postSticky(event)
    }

    fun post(event: Any,tag: String) {
        RxBus.getDefault().post(event,tag)
    }
    fun postSticky(event: Any,tag: String) {
        RxBus.getDefault().postSticky(event, tag)
    }

    fun <T> subscribe(subscriber: Any,callback: RxBus.Callback<T>) {
        RxBus.getDefault().subscribe(subscriber, callback)
    }

    fun <T> subscribe(
        subscriber: Any,
        tag: String,
        scheduler: Scheduler,
        callback: RxBus.Callback<T>
    ) {
        RxBus.getDefault().subscribe(subscriber, tag, scheduler, callback)
    }

}

