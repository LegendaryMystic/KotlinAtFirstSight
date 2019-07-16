package com.sunfly.imageloader.transformation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import java.security.MessageDigest

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/16
 *  @desc : 图片高斯模糊
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

class BlurTransformation @JvmOverloads constructor(
    private val context: Context,
    radius: Int = MAX_RADIUS,
    sampling: Int = DEFAULT_SAMPLING
) : BitmapTransformation() {

    /**
     * 模糊半径 0 ～ 25
     */
    private val radius = if (radius > MAX_RADIUS) MAX_RADIUS else radius

    /**
     * 取样 0 ～ 25
     */
    private val sampling = if (sampling > MAX_RADIUS) MAX_RADIUS else sampling

    companion object{
        private const val MAX_RADIUS = 25
        private const val DEFAULT_SAMPLING = 1
    }

    private val ID = javaClass.name


    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
        val width = toTransform.width
        val height = toTransform.height

        val scaledWidth = width / sampling
        val scaledHeight = height / sampling

        var bitmap: Bitmap? = pool.get(scaledWidth,scaledHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap!!)
        canvas.scale(1 / sampling.toFloat(),1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(toTransform,0f,0f,paint)

        bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            BlurUtils.rsBlur(context,
                bitmap,radius) else BlurUtils.blur(bitmap,radius)

        return bitmap
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID + radius * 10 + sampling).toByteArray(Key.CHARSET))
    }


    override fun equals(other: Any?): Boolean {

        return if (other is BlurTransformation) radius == other.radius && sampling == other.sampling else false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(),Util.hashCode(radius,Util.hashCode(sampling)))
    }

}