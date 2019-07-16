package com.sunfly.http

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException

import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException

import java.io.NotSerializableException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

import retrofit2.HttpException
import java.lang.Exception

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/9
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class ApiException(throwable: Throwable, val code: Int) : Exception(){

    companion object{
        //对应HTTP的状态码
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504
    }


    override var message: String? = throwable.message


    fun handleException(e: Throwable): ApiException {
        var tempThrowable = e

        var throwable: Throwable? = e
        //获取最根源的异常
        while (throwable?.cause != null) {
            tempThrowable = throwable
            throwable = throwable.cause
        }

        val ex: ApiException
        if (tempThrowable is HttpException) {             //HTTP错误
            
            ex = ApiException(tempThrowable, tempThrowable.code())
            when (tempThrowable.code()) {
                UNAUTHORIZED, FORBIDDEN -> {
                }
                NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message =
                    "默认网络异常"  //均视为网络错误
                else -> ex.message = "默认网络异常"
            }//权限错误，需要实现重新登录操作
            //                    onPermissionError(ex);
            return ex
        } else if (e is SocketTimeoutException) {
            ex = ApiException(e, ERROR.TIMEOUT_ERROR)
            ex.message = "网络连接超时，请检查您的网络状态，稍后重试！"
            return ex
        } else if (e is ConnectException) {
            ex = ApiException(e, ERROR.TIMEOUT_ERROR)
            ex.message = "网络连接异常，请检查您的网络状态，稍后重试！"
            return ex
        }
//        else if (e is UnknownHostException) {
//            ex = ApiException(e, ERROR.TIMEOUT_ERROR)
//            ex.message = "网络连接异常，请检查您的网络状态，稍后重试！"
//            return ex
//        }
        else if (e is NullPointerException) {
            ex = ApiException(e, ERROR.NULL_POINTER_EXCEPTION)
            ex.message = "空指针异常"
            return ex
        } else if (e is javax.net.ssl.SSLHandshakeException) {
            ex = ApiException(e, ERROR.SSL_ERROR)
            ex.message = "证书验证失败"
            return ex
        } else if (e is ClassCastException) {
            ex = ApiException(e, ERROR.CAST_ERROR)
            ex.message = "类型转换错误"
            return ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is JsonSyntaxException
            || e is NotSerializableException
            || e is ParseException
        ) {
            ex = ApiException(e, ERROR.PARSE_ERROR)
            ex.message = "解析错误"
            return ex
        } else if (e is IllegalStateException) {
            ex = ApiException(e, ERROR.ILLEGAL_STATE_ERROR)
            ex.message = e.message
            return ex
        } else {
            ex = ApiException(e, ERROR.UNKNOWN)
            ex.message = "未知错误"
            return ex
        }
    }

    /**
     * 约定异常
     */
    object ERROR {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000
        /**
         * 连接超时
         */
        const val TIMEOUT_ERROR = 1001
        /**
         * 空指针错误
         */
        const val NULL_POINTER_EXCEPTION = 1002

        /**
         * 证书出错
         */
        const val SSL_ERROR = 1003

        /**
         * 类转换错误
         */
        const val CAST_ERROR = 1004

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1005

        /**
         * 非法数据异常
         */
        const val ILLEGAL_STATE_ERROR = 1006

    }

}