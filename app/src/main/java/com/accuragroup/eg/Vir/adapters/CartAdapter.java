package com.accuragroup.eg.Vir.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.Vir;
import com.accuragroup.eg.Vir.interfaces.OnCartTotalPriceListener;
import com.accuragroup.eg.Vir.interfaces.OnItemRemovedListener;
import com.accuragroup.eg.Vir.models.Entities.CartProducts;
import com.accuragroup.eg.Vir.models.Entities.CartProductsDao;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${sayed} on 11/13/2017.
 */

public class CartAdapter extends ParentRecyclerAdapter<CartProducts> {

    private OnItemRemovedListener onItemRemovedListener;
    private OnCartTotalPriceListener onCartTotalPriceListener;
    CartProductsDao cartProductsDao;
    Activity activity;


    public CartAdapter(Context context,Activity activity, List<CartProducts> data, int layoutId, OnItemRemovedListener onItemRemovedListener
            , OnCartTotalPriceListener onCartTotalPriceListener) {
        super(context, data, layoutId);
        this.activity=activity;
        DaoSession daoSession = ((Vir) activity.getApplication()).getDaoSession();
        cartProductsDao = daoSession.getCartProductsDao();
        this.onItemRemovedListener = onItemRemovedListener;
        this.onCartTotalPriceListener = onCartTotalPriceListener;
    }

    @Override
    public ParentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ParentRecyclerViewHolder viewHolder, final int position) {
        final CartAdapter.ViewHolder holder = (CartAdapter.ViewHolder) viewHolder;

        // get objects
        final CartProducts cartProducts = data.get(position);


        Picasso.with(context).load(cartProducts.getImg()).into(holder.ivProduct);
        holder.tvProductName.setText(cartProducts.getTitle());
        holder.tvPrice.setText(String.valueOf(cartProducts.getPrice() + context.getString(R.string.egp)));
        holder.tvQuntity.setText(String.valueOf(cartProducts.getQuantity()));
        holder.tvShopName.setText(String.valueOf(cartProducts.getCategory()));

        totalPrice();

        holder.ivUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    cartProducts.setQuantity(cartProducts.getQuantity() + 1);
                    cartProductsDao.update(cartProducts);
                    IncreaseProductPrice(holder.tvTotal, cartProducts);
                    notifyDataSetChanged();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });

        holder.ivDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (cartProducts.getQuantity() > 1) {
                        cartProducts.setQuantity(cartProducts.getQuantity() - 1);
                        cartProductsDao.update(cartProducts);
                        DecreaseProductPrice(holder.tvTotal, cartProducts);
                        notifyDataSetChanged();
                    } else {

                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartProductsDao.delete(cartProducts);
                removeItem(position);

                if (data.size() == 0) {

                    onItemRemovedListener.onItemRemoved(position);
                }
            }
        });
    }


    public List<CartProducts> myCartList() {
        return data;
    }


    class ViewHolder extends ParentRecyclerViewHolder {

        ImageView ivProduct, ivDelete, ivUp, ivDown;
        TextView tvProductName, tvQuntity, tvPrice, tvTotal, tvShopName;


        public ViewHolder(View itemView) {
            super(context, itemView);
            ivProduct = (ImageView) itemView.findViewById(R.id.iv_cart_product_image);
            tvQuntity = (TextView) itemView.findViewById(R.id.tv_product_quantity_cart);
            tvProductName = (TextView) itemView.findViewById(R.id.tv_product_cart_titel);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_product_cart_price);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete_cart);
            ivUp = (ImageView) itemView.findViewById(R.id.iv_plus);
            tvTotal = (TextView) itemView.findViewById(R.id.iv_total);
            tvShopName = (TextView) itemView.findViewById(R.id.tv_product_cart_shop_name);
            ivDown = (ImageView) itemView.findViewById(R.id.iv_down);
        }
    }

    public void totalPrice() {

        int total = 0;
        for (int i = 0; i < data.size(); i++) {

            total += Integer.parseInt(data.get(i).getTotal());
        }
        onCartTotalPriceListener.onTotal(String.valueOf(total));
    }


    private void DecreaseProductPrice(TextView tvPrice, CartProducts product) {

        String currentPrice = String.valueOf(product.getPrice());
        int newPrice = Integer.valueOf(product.getTotal()) - Integer.valueOf(currentPrice);
        product.setTotal(String.valueOf(newPrice));
    }

    private void IncreaseProductPrice(TextView tvPrice, CartProducts product) {

        String currentPrice = String.valueOf(product.getPrice());
        int newPrice = Integer.valueOf(currentPrice) + Integer.valueOf(product.getTotal());
        product.setTotal(String.valueOf(newPrice));
    }

}
