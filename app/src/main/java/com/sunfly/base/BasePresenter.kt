package com.sunfly.base


import android.app.Activity
import android.util.Log
import com.sunfly.utils.Preconditions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 *  Incremental change is better than ambitious failure
 *
 *  @author : <a href="http://mysticcoder.coding.me">dingliang</a>
 *  @date : 2019/7/8
 *  @desc :
 *  Copyright (c) 2019 SunFly Holdings Co., Ltd  All rights reserved.
 */

open class BasePresenter<V : BaseView, M : BaseModel>: IPresenter<V>{
    protected var mView: WeakReference<V>? = null
    protected lateinit var mModel: M

    protected val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

//    init {
//        Preconditions.checkNotNull(model, "${ BaseModel::class.java.name} can not be null")
//        Preconditions.checkNotNull(view, "${ BaseView::class.java.name} can not be null")
//        this.mModel = model
//        attachView(view)
//    }

    constructor(model: M,view: V): this(view)  {
//        Preconditions.checkNotNull(model, "${ BaseModel::class.java.name} can not be null")
        Preconditions.checkNotNull(view, "${ BaseView::class.java.name} can not be null")
        this.mModel = model
//        attachView(view)

    }

    constructor(view: V)  {
        Preconditions.checkNotNull(view, "${ BaseView::class.java.name} can not be null")
        attachView(view)

    }


    protected fun getView(): V? {
        return mView?.get()
    }


     final override fun attachView(view: V) {
         this.mView = WeakReference(view)
    }

    override fun detachView() {

        mView?.clear()
        mView = null

        unDispose()

    }

    /*****************************************************************************************************/
    /**
     * 将 [Disposable] 添加到 [CompositeDisposable] 中统一管理
     * 可在 [Activity.onDestroy] 中使用 [.unDispose] 停止正在执行的 RxJava 任务,避免内存泄漏
     * 可考虑使用 [RxLifecycle | AutoDispose] 避免内存泄漏,此方法作为备用方案
     *
     * @param disposable
     */
    protected fun addDisposable(disposable: Disposable) {
        //将所有 Disposable 放入集中处理
        mCompositeDisposable.add(disposable)
        Log.d("订阅", mCompositeDisposable.toString() + "个数" + mCompositeDisposable.size())
    }

    fun unDispose() {

        //保证 Activity 结束时取消所有正在执行的订阅
        mCompositeDisposable.clear()

    }
}