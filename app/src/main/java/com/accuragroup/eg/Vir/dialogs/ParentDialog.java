package com.accuragroup.eg.Vir.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.utils.DialogUtils;

public class ParentDialog extends Dialog implements View.OnClickListener {
    // used to hold connection handlers that should be cancelled when destroyed
    protected Context context;
    private FrameLayout rootView;
    private TextView tvDialogTitle;
    private ImageButton ibClose;
    protected ProgressDialog progressDialog;

    private View progressView;

    public ParentDialog(Context context) {
        super(context);
        this.context = context;

        // set no title and transparent bg
        // customize dialog
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.DialogTransitions;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // init and customize the dialog toolbar
        rootView = (FrameLayout) findViewById(android.R.id.content);
        View toolbar = findViewById(R.id.toolbar);

    }

    public void showProgressDialog() {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            progressDialog = DialogUtils.showProgressDialog(getContext(), R.string.please_wait_dotted);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (tvDialogTitle != null) {
            tvDialogTitle.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(context.getString(titleId));
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (ibClose != null) {
            ibClose.setVisibility(flag ? View.VISIBLE : View.GONE);
        }
    }


    public String getString(int strId) {
        return context.getString(strId);
    }

    @Override
    public void onClick(View v) {

    }


    public void hideProgress() {
        if (progressView != null) {
            progressView.setVisibility(View.GONE);
        }
        super.setCancelable(true);
    }

    @Override
    public void show() {
        super.show();

        // customize the dialog width
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

}
