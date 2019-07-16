package com.sunfly.imageloader

import androidx.annotation.IntDef

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/15
 *  @desc : 图片加载框架缓存策略 注解替代枚举
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

class CacheStrategy{

    companion object{
        const val ALL = 0

        const val NONE = 1

        const val RESOURCE = 2

        const val DATA = 3

        const val AUTOMATIC = 4
    }

    @IntDef(ALL, NONE, RESOURCE, DATA, AUTOMATIC)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Strategy

}