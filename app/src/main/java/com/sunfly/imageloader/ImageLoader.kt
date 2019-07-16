package com.sunfly.imageloader

import android.content.Context
import android.media.Image

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

class ImageLoader private constructor(): ImageLoaderProxy<ImageLoaderOptionsImpl> {

    private var mLoaderStrategy: ImageLoaderStrategy<ImageLoaderOptionsImpl>

    init {
        mLoaderStrategy = GlideImageLoaderStrategy()
    }

    companion object{
        val instance = LoaderHolder.INSTANCE
    }

    private object LoaderHolder {
        val INSTANCE = ImageLoader()
    }


    private fun setLoaderStrategy(loaderStrategy: ImageLoaderStrategy<ImageLoaderOptionsImpl>) {
        this.mLoaderStrategy = loaderStrategy
    }

    override fun loadImage(ctx: Context, options: ImageLoaderOptionsImpl) {
        this.mLoaderStrategy.loadImage(ctx, options)
    }

    override fun clear(ctx: Context, options: ImageLoaderOptionsImpl) {

        this.mLoaderStrategy.clear(ctx,options)
    }

}