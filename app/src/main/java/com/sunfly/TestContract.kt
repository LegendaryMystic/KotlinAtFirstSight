package com.sunfly

import com.sunfly.base.BaseModel
import com.sunfly.base.BaseView

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

interface TestContract{

    interface View: BaseView{
        fun onGetResult()
    }

    interface Model: BaseModel{
        fun getResult()
    }
}