package com.sunfly

import com.sunfly.base.BasePresenter

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 *  @date : 2019/7/15
 *  @desc :
 *  Copyright (c) 2019 *********  All rights reserved.
 */

class TestPresenter(view: TestContract.View) : BasePresenter<TestContract.View, TestContract.Model>(TestModel(), view)