package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.activities.OrdersActivity;
import com.accuragroup.eg.Vir.dialogs.OrderProductsDialog;
import com.accuragroup.eg.Vir.interfaces.OnOrderResevedListner;
import com.accuragroup.eg.Vir.models.Responces.Order;
import com.accuragroup.eg.Vir.utils.Utils;

import java.util.List;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<Order> orderDetails;
    private String lang;


    public OrdersAdapter(Context context, List<Order> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(OrdersAdapter.MyViewHolder holder, int position) {

        final Order order = orderDetails.get(position);
        lang = Utils.getCachedString(context, Const.Language, "");

        holder.tvOrderNum.setText("# " + String.valueOf(order.getOrderId()));
        holder.tvOrderDate.setText(order.getOrderDate());
        holder.tvOrderAmount.setText(order.getOrderTotal() + " " + context.getString(R.string.egp));
        holder.tvOrderShopName.setText(order.getOrderShopName());
        String orderStatuse = order.getOrderStatus();
        if (orderStatuse.equals(Const.pending)) {
            holder.ivOrderStatuse.setImageResource(R.drawable.ic_order_pending);
        } else if (orderStatuse.equals(Const.cancelled)) {
            holder.ivOrderStatuse.setImageResource(R.drawable.path);

        } else {
            holder.ivOrderStatuse.setImageResource(R.drawable.ic_order_deliverd);
        }

        if (lang.equals("ar")) {
            holder.tvOrderDetails.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_action_left_arrow, 0)
            ;
        }

        holder.tvOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderProductsDialog productsDialog = new OrderProductsDialog(context, order.getOrderDetails()
                        , order.getOrderId(), order.getOrderTotal()
                        , order.getOrderStatus()
                        , order.getOrderId(), new OnOrderResevedListner() {
                    @Override
                    public void onDialogClosed(int id) {
                        context.startActivity(new Intent(context, OrdersActivity.class));
                        ((OrdersActivity) context).finish();

                    }
                });
                productsDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivOrderStatuse;
        private TextView tvOrderShopName, tvOrderNum, tvOrderDate, tvOrderAmount, tvOrderDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderDetails = (TextView) itemView.findViewById(R.id.tv_order_details);
            tvOrderDate = (TextView) itemView.findViewById(R.id.tv_order_date);
            tvOrderShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
            tvOrderAmount = (TextView) itemView.findViewById(R.id.tv_order_total);
            tvOrderNum = (TextView) itemView.findViewById(R.id.tv_order_num);
            ivOrderStatuse = itemView.findViewById(R.id.btn_order_status);
        }
    }
}
