package com.sunfly.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc : Builder设计模式 图片加载配置
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class ImageLoaderOptionsImpl private constructor(builder: Builder): LoaderOptions() {

    init {
        this.url = builder.url
        this.imageView = builder.imageView
        this.placeholder = builder.placeholder
        this.errorPic = builder.errorPic
    }

    @DrawableRes
    val fallback = builder.fallback

    @CacheStrategy.Strategy
    val cacheStrategy = builder.cacheStrategy

    /**
     * 设置图片四周圆角，每个圆角的大小
     */
     @get:JvmName("imageRadius")val imageRadius : Int = builder.imageRadius

    /**
     * 高斯模糊值，值越大模糊效果越大
     */
    val blurValue = builder.blurValue

    /**
     * 是否使用淡入淡出过渡动画
     */
    val isCrossFade = builder.isCrossFade


    val isCenterCrop = builder.isCenterCrop

    /**
     *  是否将图片剪切为圆形
     */
    val isCircle = builder.isCircle

    /**
     *  清理内存缓存
     */
    val isClearMemoryCache = builder.isClearMemoryCache

    /**
     * 清理本地磁盘缓存
     */
    val isClearDiskCache = builder.isClearDiskCache

    fun isImageBlur(): Boolean{
        return blurValue > 0
    }

    fun isImageRadius(): Boolean{
        return imageRadius > 0
    }

    class Builder{
        internal var url: String? = null
        internal lateinit var imageView: ImageView
        internal var placeholder = 0
        internal var errorPic = 0
        internal var fallback = 0
        
        @CacheStrategy.Strategy
        internal var cacheStrategy = 0
        
        internal var imageRadius = 0
        
        internal var blurValue = 0

        /**
         * 是否使用淡入淡出过渡动画
         */
        internal var isCrossFade = false


        internal var isCenterCrop = false

        /**
         *  是否将图片剪切为圆形
         */
        internal var isCircle = false

        /**
         *  清理内存缓存
         */
        internal var isClearMemoryCache = false

        /**
         * 清理本地磁盘缓存
         */
        internal var isClearDiskCache = false


        fun url(url: String) = apply {
            this.url = url
        }

        fun placeholder(placeholder: Int) = apply {
            this.placeholder = placeholder
        }

        fun errorPic(errorPic: Int) = apply {
            this.errorPic = errorPic
        }

        fun imageView(imageView: ImageView) = apply {
            this.imageView = imageView
        }

        fun fallback(fallback: Int) = apply {
            this.fallback = fallback
        }

        fun cacheStrategy(@CacheStrategy.Strategy cacheStrategy: Int) = apply {
            this.cacheStrategy = cacheStrategy
        }

        fun imageRadius(imageRadius: Int) = apply {
            this.imageRadius = imageRadius
        }

        fun blurValue(blurValue: Int) = apply {
            this.blurValue = blurValue
        }

        fun isCrossFade(isCrossFade: Boolean) = apply {
            this.isCrossFade = isCrossFade
        }

        fun isCenterCrop(isCenterCrop: Boolean) = apply {
            this.isCenterCrop = isCenterCrop
        }

        fun isCircle(isCircle: Boolean) = apply {
            this.isCircle = isCircle
        }

        fun isClearMemoryCache(isClearMemoryCache: Boolean) = apply {
            this.isClearMemoryCache = isClearMemoryCache
        }

        fun isClearDiskCache(isClearDiskCache: Boolean) = apply {
            this.isClearDiskCache = isClearDiskCache
        }

        fun build():ImageLoaderOptionsImpl = ImageLoaderOptionsImpl(this)
    }

}