package com.demo.phy.phybasedemo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;


/**
 * Created by Administrator on 2018/4/9.
 */

public abstract class BasePopWindow {

    protected View popupView;
    protected PopupWindow popupWindow = null;
    public Activity mContext;
    TextView mTitle;
    protected boolean isInit = false;
    private boolean openAnimation;
    protected int width = ViewGroup.LayoutParams.WRAP_CONTENT, height = ViewGroup.LayoutParams.WRAP_CONTENT;

    public void init(Activity context) {
        mContext = context;
        popupView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        popupWindow = new PopupWindow(width, height);
        popupWindow.setContentView(popupView);
        mTitle = popupView.findViewById(R.id.title);
        if (openAnimation) setPopAnimation();
        initOtherView();
        setPopBackground();
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setClippingEnabled(true);
        isInit = true;
    }

    public void setPopBackground() {
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void setContext(Activity context) {
        mContext = context;
    }

    public abstract int getLayoutId();

    public abstract void initOtherView();

    protected void setPopAnimation() {
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    public void openAnimation(boolean open) {
        openAnimation = open;
    }

}
