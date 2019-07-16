package com.sunfly.base

import android.os.Bundle

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/8
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

abstract class BaseMvpActivity<P : BasePresenter<*,*>> : BaseActivity() {

    protected var mPresenter: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        mPresenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun createPresenter(): P


    override fun onDestroy() {
        super.onDestroy()
            mPresenter?.detachView()
            mPresenter = null

    }


}