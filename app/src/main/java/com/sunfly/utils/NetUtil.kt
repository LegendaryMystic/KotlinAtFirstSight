package com.sunfly.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/11
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

object NetUtil {


    /**
     * 判断网络是否连接
     *
     * @param context Context
     * @return 网络是否连接
     */
    fun isConnected(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return !(null == info || !info.isConnected)
    }


}