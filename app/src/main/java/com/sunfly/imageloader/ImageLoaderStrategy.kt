package com.sunfly.imageloader

import android.content.Context
import androidx.annotation.NonNull

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

interface ImageLoaderStrategy<T: LoaderOptions>{

    /**
     *  加载图片
     *  @param ctx [Context]
     *  @param options 图片加载配置信息
     */
    fun loadImage(ctx: Context,options: T)



    /**
     *  停止加载或清理缓存
     *  @param ctx [Context]
     *  @param options 图片加载配置信息
     */
    fun clear(ctx: Context,options: T)


}