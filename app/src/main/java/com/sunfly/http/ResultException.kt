package com.sunfly.http

import java.lang.RuntimeException

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/9
 *  @desc : 根据后台约定的code，message自定义异常信息
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class ResultException(val code: Int, override var message: String?) : RuntimeException()
//data class ResultException(val code: Int, override val message: String) : RuntimeException()