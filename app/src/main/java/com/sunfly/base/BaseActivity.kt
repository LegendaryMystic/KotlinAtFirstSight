package com.sunfly.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sunfly.bus.BusProvider

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/5
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

abstract class BaseActivity : AppCompatActivity(),IActivity {
    val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutResId = contentViewLayoutId()
        if (layoutResId != 0){
            setContentView(layoutResId)

            if (useEventBus()){
                BusProvider.instance.register(this)
            }
        }

        initData()
        initView()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            BusProvider.instance.unregister(this)
        }
    }

    override fun useEventBus(): Boolean {
        return false
    }

}