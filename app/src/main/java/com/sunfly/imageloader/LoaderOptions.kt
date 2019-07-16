package com.sunfly.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

open class LoaderOptions {
    var url: String? = null

    lateinit var imageView: ImageView

    @DrawableRes
    var placeholder: Int = 0

    @DrawableRes
    var errorPic: Int = 0

}