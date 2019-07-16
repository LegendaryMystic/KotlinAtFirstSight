package com.sunfly;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.sunfly.imageloader.ImageLoader;

public class MyButton extends AbstractDragFloatActionButton {


    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_button;//拿到你自己定义的悬浮布局
    }

    @Override
    public void renderView(View view) {
          //初始化那些布局

    }


}
