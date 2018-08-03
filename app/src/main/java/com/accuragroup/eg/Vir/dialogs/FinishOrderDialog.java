package com.accuragroup.eg.Vir.dialogs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.Vir;
import com.accuragroup.eg.Vir.activities.OrdersActivity;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.models.Entities.CartProductsDao;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.accuragroup.eg.Vir.models.Entities.Item;
import com.accuragroup.eg.Vir.models.Request.OrderRequest;
import com.accuragroup.eg.Vir.models.Request.SendOrderRequest;
import com.accuragroup.eg.Vir.models.Responces.AddOrderResponse;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 12/13/2017.
 */

public class FinishOrderDialog extends ParentDialog {


    ProgressDialog progressDialog;
    private Button btnConfirm, btnCancel;
    private TextView tvTotalPrice;
    private OrderRequest orderRequest;
    List<Item> items;
    private String UserID;


    List<String> orderData;
    CartProductsDao cartProductsDao;


    public FinishOrderDialog(Context context, Activity activity, String Total, OrderRequest orderRequest) {
        super(context);
        setContentView(R.layout.dialog_finish_order);

        DaoSession daoSession = ((Vir) activity.getApplication()).getDaoSession();
        cartProductsDao = daoSession.getCartProductsDao();
        this.orderRequest = orderRequest;
        getOrderData();
        UserID = Utils.getCachedString(context, "USERID", "");

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(getString(R.string.please_wait_dotted));
        progressDialog.setCancelable(false);
        btnCancel = findViewById(R.id.btn_cancel_order);
        btnConfirm = findViewById(R.id.btn_confirm_order);
        tvTotalPrice = findViewById(R.id.tv_order_finish_total);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        tvTotalPrice.setText(Total);
    }

    private void getOrderData() {

        orderData = new ArrayList<>();
        items = orderRequest.getItems();
        for (int i = 0; i < items.size(); i++) {
            orderData.add(String.valueOf(items.get(i).getProductId()) + "_" + String.valueOf(items.get(i).getQty()));
        }
        Log.wtf(Const.LOG_TAG, String.valueOf(orderData));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_confirm_order) {
            SendOrder();

        } else {
            dismiss();
        }
    }

    private void SendOrder() {
        SendOrderRequest sendOrderRequest = new SendOrderRequest();
        sendOrderRequest.setAccessPassWord(Const.ACCESS_PASSWORD);
        sendOrderRequest.setName(Const.ACCESS_KEY);
        sendOrderRequest.setUserId(Integer.parseInt(UserID));
        sendOrderRequest.setServiceId_Quantity(android.text.TextUtils.join(",", orderData));
        sendOrderRequest.setLangu(Utils.getCachedString(context, Const.Language, ""));


        progressDialog.show();
        Ion.with(context)
                .load(ServiceApi.Service.addOrder.getBaseService())
                .setJsonPojoBody(sendOrderRequest)
                .as(new TypeToken<AddOrderResponse>() {
                })
                .setCallback(new FutureCallback<AddOrderResponse>() {
                    @Override
                    public void onCompleted(Exception e, AddOrderResponse result) {
                        if (e != null) {
                            Log.wtf(Const.LOG_TAG, String.valueOf(e));
                            Utils.showLongToast(context, context.getString(R.string.connectionFieldTryAgain));
                            progressDialog.hide();
                        } else {
                            if (result.getStatus() == 200) {
                                Utils.showShortToast(context, getString(R.string.orderSentSuccessfully));
                                cartProductsDao.deleteAll();
                                context.startActivity(new Intent(context, OrdersActivity.class));
                                ((Activity) context).finish();
                                progressDialog.hide();
                            } else {
                                progressDialog.hide();
                                Utils.showShortToast(context, getString(R.string.something_went_wrong_please_try_again));
                            }

                        }
                    }
                });
    }
}
