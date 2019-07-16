package com.sunfly.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sunfly.utils.Preconditions
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class GlideImageLoaderStrategy: ImageLoaderStrategy<ImageLoaderOptionsImpl> {


    @SuppressLint("CheckResult")
    override fun loadImage(ctx: Context, options: ImageLoaderOptionsImpl) {
        Preconditions.checkNotNull(options.imageView,"ImageView is required!")

        val request = GlideApp.with(ctx)

        val glideRequest = request.load(options.url)

        //缓存策略
        when(options.cacheStrategy) {
            CacheStrategy.ALL -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
            CacheStrategy.NONE -> glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE)
            CacheStrategy.RESOURCE -> glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            CacheStrategy.DATA -> glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA)
            CacheStrategy.AUTOMATIC -> glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            else -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        if (options.isCrossFade) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade())
        }

        if (options.isCenterCrop) {
            glideRequest.centerCrop()
        }

        if (options.isCircle) {
            glideRequest.circleCrop()
        }

        if (options.isImageRadius()) {
            glideRequest.transform(RoundedCorners(options.imageRadius))
        }

//        if (options.isImageBlur()) {
//            glideRequest.transform()
//        }

        if (options.placeholder != 0) {
            glideRequest.placeholder(options.placeholder)
        }

        if (options.errorPic != 0) {
            glideRequest.placeholder(options.errorPic)
        }

        if (options.fallback != 0) {
            //设置url 为 null 时的占位图
            glideRequest.placeholder(options.fallback)
        }

        glideRequest.into(options.imageView)

    }

    override fun clear(ctx: Context, options: ImageLoaderOptionsImpl) {

        GlideApp.get(ctx).requestManagerRetriever.get(ctx).clear(options.imageView)

        if (options.isClearDiskCache) {
            //清除本地磁盘缓存
            Completable.fromAction{ Glide.get(ctx).clearDiskCache()}.subscribeOn(Schedulers.io()).subscribe()
        }

        if (options.isClearMemoryCache) {
            //清除内存缓存
            Completable.fromAction{ Glide.get(ctx).clearMemory()}.subscribeOn(Schedulers.io()).subscribe()
        }

    }
}