package com.sunfly.imageloader

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

@GlideModule
class SunflyAppGlideModule: AppGlideModule() {

    /**
     * 通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     *
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {

        //重新设置内存限制
        builder.setMemoryCache(LruResourceCache(10 * 1024 * 1024))

    }


    /**
     * 清单解析的开启
     *
     *
     * 这里不开启，避免添加相同的modules两次
     *
     * @return
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}