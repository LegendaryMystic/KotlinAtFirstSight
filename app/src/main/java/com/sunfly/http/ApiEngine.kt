package com.sunfly.http

import android.util.Log
import com.sunfly.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/9
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

object ApiEngine {
    private val DEFAULT_TIMEOUT: Long = 10

    val service: ApiService by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
        buildRetrofit().create(ApiService::class.java)
    }


    /**
     * 构建OkHttpClient
     */
    private fun buildOkHttpClient(): OkHttpClient {

        //添加一个log拦截器,打印所有的log
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            try {
                val text = URLDecoder.decode(it, "utf-8")
                Log.e("OKHttp-----", text)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                Log.e("OKHttp-----", it)
            }

        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheSize = 1024 * 1024 * 50 //50M缓存大小
        val cacheFile = File(App.context.cacheDir,"OkHttpCache")
        val cache = Cache(cacheFile, cacheSize.toLong())

        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

    }

    /**
     * 构建Retrofit实例
     */
    private fun buildRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(buildOkHttpClient())
            //然后将下面的GsonConverterFactory.create()替换成我们自定义的ResponseConverterFactory.create()
//            .addConverterFactory(ResponseConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}