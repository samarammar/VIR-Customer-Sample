package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.models.Responces.OrderDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class OrderProductAdapter extends ParentRecyclerAdapter<OrderDetail> {


    public OrderProductAdapter(Context context, List<OrderDetail> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public ParentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        OrderProductAdapter.ViewHolder viewHolder = new OrderProductAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ParentRecyclerViewHolder holder, int position) {

        OrderProductAdapter.ViewHolder viewHolder = (OrderProductAdapter.ViewHolder) holder;

        OrderDetail orderDetail = data.get(position);

        viewHolder.tvQuantity.setText(context.getString(R.string.qty)+" "+ String.valueOf(orderDetail.getItemQuantity()));
        viewHolder.tvShop.setText(String.valueOf(orderDetail.getShopName()));
        viewHolder.tvTitle.setText(orderDetail.getProductName());
        Picasso.with(context).load(orderDetail.getProductImage()).into(viewHolder.ivProduct);
        viewHolder.tvPrice.setText(String.valueOf(orderDetail.getItemPrice()) + "  " + getString(R.string.egp));

    }

    class ViewHolder extends ParentRecyclerViewHolder {


        private TextView tvTitle, tvQuantity, tvPrice, tvShop;
        private ImageView ivProduct;

        public ViewHolder(View itemView) {
            super(context, itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_order_product_name);
            tvQuantity = (TextView) itemView.findViewById(R.id.tv_order_product_qtu);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_order_product_price);
            tvShop = (TextView) itemView.findViewById(R.id.tv_order_product_shop);
            ivProduct = (ImageView) itemView.findViewById(R.id.iv_order_product);

        }
    }

}
