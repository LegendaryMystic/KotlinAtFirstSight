package com.sunfly.base

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/5
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

interface IActivity {

    fun contentViewLayoutId():Int

    fun initView()

    fun initData()

    fun useEventBus():Boolean
}