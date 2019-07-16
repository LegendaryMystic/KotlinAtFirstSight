package com.sunfly

import com.sunfly.base.BaseModel
import com.sunfly.base.BaseMvpActivity
import com.sunfly.base.BaseView

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

class TestActivity: BaseMvpActivity<TestPresenter>(),TestContract.View{
    override fun onGetResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): TestPresenter {
        return TestPresenter(this)
    }

    override fun contentViewLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}