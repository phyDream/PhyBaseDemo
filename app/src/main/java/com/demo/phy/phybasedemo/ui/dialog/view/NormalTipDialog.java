package com.demo.phy.phybasedemo.ui.dialog.view;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.base.BaseDialog;

public class NormalTipDialog extends BaseDialog {

    private Context mContext;
    EditText et_transferNote;
    TextView tv_name;
    Button btnCancel;
    Button btn_sure;
    private int position;
    private String tip;
    private OnClickListenner listenner;

    public NormalTipDialog(Context context) {
        super(context);
        mContext = context;
    }

    public void setListenner(OnClickListenner listenner) {
        this.listenner = listenner;
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
        tv_name = findViewById(R.id.tv_name);
        btnCancel = findViewById(R.id.btn_cancel);
        btn_sure = findViewById(R.id.btn_change);

        tv_name.setText(tip);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listenner.onSure(position);
            }
        });

        this.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                showKeyboard();
            }
        });
    }

    private void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et_transferNote, 0);
    }

    public void show(String tip,OnClickListenner listenner){
        this.listenner = listenner;
        this.tip = tip;
        show();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_normal_tipk;
    }

    public interface OnClickListenner {
        void onSure(int position);
    }
}
