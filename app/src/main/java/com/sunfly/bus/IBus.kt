package com.sunfly.bus

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/5
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

interface IBus {
    fun register(subscriber: Any)

    fun unregister(subscriber: Any)

    fun post(event: Any)

    fun postSticky(event: Any)
}