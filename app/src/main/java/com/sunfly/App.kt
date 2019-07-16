package com.sunfly

import android.app.Application
import kotlin.properties.Delegates

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/9
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {

        var context: App by Delegates.notNull()
            private set
    }
}
