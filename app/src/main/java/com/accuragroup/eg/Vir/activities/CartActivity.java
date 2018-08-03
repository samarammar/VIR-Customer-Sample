package com.accuragroup.eg.Vir.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.Vir;
import com.accuragroup.eg.Vir.adapters.CartAdapter;
import com.accuragroup.eg.Vir.dialogs.FinishOrderDialog;
import com.accuragroup.eg.Vir.interfaces.OnCartTotalPriceListener;
import com.accuragroup.eg.Vir.interfaces.OnItemRemovedListener;
import com.accuragroup.eg.Vir.models.Entities.CartProducts;
import com.accuragroup.eg.Vir.models.Entities.CartProductsDao;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.accuragroup.eg.Vir.models.Entities.Item;
import com.accuragroup.eg.Vir.models.Request.OrderRequest;
import com.accuragroup.eg.Vir.utils.Utils;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 11/13/2017.
 */

public class CartActivity extends ParentActivity implements OnCartTotalPriceListener, OnItemRemovedListener {


    private RecyclerView rvCart;
    private TextView tvTotal;
    private Button tvMain, btnFinishOrder;
    FrameLayout layCart;
    private String UserID;


    CartAdapter cartAdapter;
    List<CartProducts> productsList;
    List<CartProducts> orderList;
    List<Item> itemList;
    List<Integer> idsList;
    private LinearLayout layMain, layError;
    private Query<CartProducts> cartProductsQuery;
    private String totalPrice;


    CartProductsDao cartProductsDao;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(getString(R.string.cart));
        enableBackButton();
        DaoSession daoSession = ((Vir) this.getApplication()).getDaoSession();
        cartProductsDao = daoSession.getCartProductsDao();

        initViews();
        updateUi();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_buyNow) {

                onTotal(totalPrice);
                orderList = cartAdapter.myCartList();
                itemList = new ArrayList<>();

                for (int i = 0; i < orderList.size(); i++) {
                    Item item = new Item();
                    item.setProductId((int) orderList.get(i).getId());
                    item.setQty(orderList.get(i).getQuantity());
                    itemList.add(item);
                }
                OrderRequest orderRequest = new OrderRequest();
                orderRequest.setItems(itemList);
                FinishOrderDialog finishOrderDialog = new FinishOrderDialog(CartActivity.this,CartActivity.this, totalPrice, orderRequest);
                finishOrderDialog.show();
            }
    }

    private void updateUi() {
        itemList = new ArrayList<>();
        idsList = new ArrayList<>();
        orderList = new ArrayList<>();

        rvCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        cartAdapter = new CartAdapter(CartActivity.this,CartActivity.this, getList(), R.layout.item_cart, this, this);
        rvCart.setAdapter(cartAdapter);


    }

    private List<CartProducts> getList() {
        cartProductsQuery = cartProductsDao.queryBuilder().orderAsc(CartProductsDao.Properties.Id).build();
        productsList = cartProductsQuery.list();

        if (productsList.size() == 0) {
            layError.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < productsList.size(); i++) {
                idsList.add((int) productsList.get(i).getId());
            }
        }

        return cartProductsQuery.list();
    }


    private void initViews() {

        UserID = Utils.getCachedString(CartActivity.this, "USERID", "");

        layError = (LinearLayout) findViewById(R.id.lay_no_cart);
        layMain = (LinearLayout) findViewById(R.id.lay_main);
        tvTotal = (TextView) findViewById(R.id.tv_totalPrice);
        rvCart = (RecyclerView) findViewById(R.id.rv_cart);
        btnFinishOrder = (Button) findViewById(R.id.btn_buyNow);
        btnFinishOrder.setOnClickListener(this);
    }

    @Override
    public void onTotal(String totalPrice) {
        tvTotal.setText(getString(R.string.totalPrice) + " " + totalPrice + " " + getString(R.string.Egp));
        this.totalPrice = totalPrice;
    }

    @Override
    public void onItemRemoved(int position) {
        layError.setVisibility(View.VISIBLE);
    }
}
