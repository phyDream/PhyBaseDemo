package com.demo.phy.phybasedemo.ui.dialog.view;

import android.view.View;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.base.BasePopWindow;
import com.demo.phy.phybasedemo.utils.ScreenUtils;

public class OptionMenuPop extends BasePopWindow {

    private TextView tv_test1;
    private TextView tv_test2;
    private TextView tv_test3;

    @Override
    public int getLayoutId() {
        return R.layout.pop_main_option_menu;
    }

    @Override
    public void initOtherView() {

        tv_test1 = popupView.findViewById(R.id.tv_test1);
        tv_test2 = popupView.findViewById(R.id.tv_test2);
        tv_test3 = popupView.findViewById(R.id.tv_test3);

    }

    public void show(View v) {
        int Offset = (int) ScreenUtils.Companion.dp2px(mContext,110f);
        popupWindow.showAsDropDown(v, -Offset, 10);

    }
}
