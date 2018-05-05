package com.example.englishplay.loginactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

import java.util.jar.Attributes;

/**
 * Created by 小飞侠 on 2018/5/4.
 */

public class FullScreenVideoView extends VideoView {

    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    //重写宽高，实现全屏
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int width = getDefaultSize(0,widthMeasureSpec);
        int height = getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }
}
