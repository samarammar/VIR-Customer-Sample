package com.accuragroup.eg.Vir.dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.activities.OrdersActivity;
import com.accuragroup.eg.Vir.adapters.OrderProductAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.interfaces.OnOrderResevedListner;
import com.accuragroup.eg.Vir.models.Request.UpdateOrderStatusRequest;
import com.accuragroup.eg.Vir.models.Responces.ChangeOrderStatuseResponse;
import com.accuragroup.eg.Vir.models.Responces.OrderDetail;
import com.accuragroup.eg.Vir.utils.DialogUtils;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class OrderProductsDialog extends ParentDialog {

    private TextView tvDialogTitle, tvOrderTotal, tvTitle;
    private LinearLayout layDone;
    private RecyclerView rvItemDetails;
    OrderProductAdapter orderProductsAdapter;
    private ImageButton ivClose;
    int OrderId;
    String Status;
    private ProgressDialog progressDialog;
    private OnOrderResevedListner onOrderResevedListner;

    public OrderProductsDialog(Context context, List<OrderDetail> orderDetails
            , int OrderNumber, String Total, String Status, int id, OnOrderResevedListner onOrderResevedListner) {
        super(context);
        this.onOrderResevedListner = onOrderResevedListner;
        this.OrderId = id;
        this.Status = Status;
        setContentView(R.layout.dialog_order_products);
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        tvDialogTitle = (TextView) findViewById(R.id.tv_dialog_title);
        tvDialogTitle.setText(getString(R.string.item_details));
        rvItemDetails = (RecyclerView) findViewById(R.id.rv_order_products);
        tvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        tvTitle.setText("# " + String.valueOf(OrderNumber));
        ivClose = (ImageButton) findViewById(R.id.ib_close);
        ivClose.setOnClickListener(this);

        layDone = findViewById(R.id.lay_order_recived);
        layDone.setOnClickListener(this);
        checkOrderStatus();
        tvOrderTotal = findViewById(R.id.tv_orders_details_total);
        tvOrderTotal.setText(getString(R.string.total) + Total + " " + getString(R.string.le));


        rvItemDetails.setLayoutManager(new LinearLayoutManager(context));
        orderProductsAdapter = new OrderProductAdapter(context, orderDetails, R.layout.item_order_detail);
        rvItemDetails.setAdapter(orderProductsAdapter);


    }

    private void checkOrderStatus() {
        if (Status.equals(Const.completed)) {
            layDone.setVisibility(View.GONE);
        } else if (Status.equals(Const.cancelled)) {
            layDone.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_close) {
            dismiss();
        } else if (v.getId() == R.id.lay_order_recived) {

            DialogUtils.showConfirmDialog(context, context.getString(R.string.areYouWantToConfirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    UpdateOrderStatuse();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        } else {
            super.onClick(v);

        }
    }

    private void UpdateOrderStatuse() {
        UpdateOrderStatusRequest params = new UpdateOrderStatusRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setOrderId(OrderId);
        params.setStatus(Const.OrderCompleted);


        progressDialog.show();
        Ion.with(context)
                .load(ServiceApi.Service.changeOrderStatus.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<ChangeOrderStatuseResponse>() {
                })
                .setCallback(new FutureCallback<ChangeOrderStatuseResponse>() {
                    @Override
                    public void onCompleted(Exception e, ChangeOrderStatuseResponse result) {
                        if (e != null) {
                            Log.wtf(Const.LOG_TAG, String.valueOf(e));
                            Utils.showLongToast(context, getString(R.string.connectionFieldTryAgain));
                            progressDialog.hide();

                        } else {
                            if (result.getStatus() == 200) {
                                dismiss();
                                onOrderResevedListner.onDialogClosed(1);
                                progressDialog.hide();
                            } else {
                                Utils.showLongToast(context, getString(R.string.some_thing_went_wrong));
                                progressDialog.hide();

                            }
                        }
                    }
                });


    }

}
