package com.demo.phy.phybasedemo.ui.dialog.view;

import android.animation.AnimatorSet;
import android.content.Context;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.base.BaseDialog;

public class OptionMenuDialog extends BaseDialog {
    private Context mContext;
    private TextView tv_test1,tv_test2,tv_test3;

    public OptionMenuDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected float setWidthScale() {
        return 0.8f;
    }

    @Override
    protected float setHeightScale() {
        return 0.2f;
    }

    @Override
    protected AnimatorSet setEnterAnim() {
        return null;
    }

    @Override
    protected AnimatorSet setExitAnim() {
        return null;
    }

    @Override
    protected void init() {
        this.setCanceledOnTouchOutside(true);
        tv_test1 = findViewById(R.id.tv_test1);
        tv_test2 = findViewById(R.id.tv_test2);
        tv_test3 = findViewById(R.id.tv_test3);
    }

    public void show(int position){
        show();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_main_option_menu;
    }
}
