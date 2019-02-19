package com.demo.phy.phybasedemo.ui.dialog.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.base.BasePopWindow;

public class BottomPop extends BasePopWindow {
    @Override
    public int getLayoutId() {
        return R.layout.pop_bottom;
    }

    @Override
    public void initOtherView() {

    }

    public void show(View v) {
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.popwindow_bottom_anim_style); // 设置动画
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(mContext, 1f);
            }
        });
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(mContext, 0.7f);
    }

    /**
     * 设置背景色
     */
    private void setBackgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }
}
