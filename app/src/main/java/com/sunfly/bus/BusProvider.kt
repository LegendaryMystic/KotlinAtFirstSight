package com.sunfly.bus

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/8
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

class BusProvider private constructor(){

    companion object {
        val instance = BusHolder.INSTANCE
    }

    private object BusHolder {
        val INSTANCE = RxBusImpl()
    }

}