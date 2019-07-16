package com.sunfly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.blankj.rxbus.RxBus
import com.bumptech.glide.Glide
import com.sunfly.base.BaseActivity
import com.sunfly.bus.BusProvider
import com.sunfly.http.ApiException
import com.sunfly.http.ResultException
import com.sunfly.imageloader.ImageLoader
import com.sunfly.imageloader.ImageLoaderOptionsImpl
import com.sunfly.imageloader.LoaderOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun contentViewLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initView() {

        my_btn.setOnClickListener {
            ImageLoader.instance.loadImage(
            this, ImageLoaderOptionsImpl.Builder()
                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563190387212&di=81cb825dfbb5cba73bae3b6c702883af&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201807%2F10%2F20180710154602_nxxql.jpg")
                .imageView(imageV)
                .build()
        )
        }
    }

    override fun initData() {

    }


    private var obj: String ? = "fuck"



}
