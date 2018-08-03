package com.accuragroup.eg.Vir.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.Vir;
import com.accuragroup.eg.Vir.adapters.OrdersAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.interfaces.OnOrderResevedListner;
import com.accuragroup.eg.Vir.models.Entities.CartProducts;
import com.accuragroup.eg.Vir.models.Entities.CartProductsDao;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.accuragroup.eg.Vir.models.Request.UserOrdersRequest;
import com.accuragroup.eg.Vir.models.Responces.OrdersResponse;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.greenrobot.greendao.query.Query;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class OrdersActivity extends ParentActivity implements OnOrderResevedListner {

    private LinearLayout layEmpty;
    private RecyclerView rvOrders;
    private RelativeLayout layMain;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar pbOrders;
    OrdersAdapter ordersAdapter;
    String UserId;
    public CartProductsDao cartProductsDao;

    private ImageButton ibSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        enableBackButton();
        setTitle(getString(R.string.orders));
        initViews();
        getCurrentOrders();

    }

    private void getCurrentOrders() {
        UserOrdersRequest params = new UserOrdersRequest();
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setName(Const.ACCESS_KEY);
        params.setUserID(Integer.parseInt(UserId));
        params.setLangu(Utils.getCachedString(OrdersActivity.this, Const.Language, ""));

        pbOrders.setVisibility(View.VISIBLE);
        Ion.with(OrdersActivity.this)
                .load(ServiceApi.Service.getSendOrders.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<OrdersResponse>() {
                })
                .setCallback(new FutureCallback<OrdersResponse>() {
                    @Override
                    public void onCompleted(Exception e, OrdersResponse result) {
                        if (e != null) {
                            Utils.showLongToast(OrdersActivity.this, getString(R.string.connectionFieldTryAgain));
                            pbOrders.setVisibility(View.GONE);
                        } else {

                            if (result.getStatus() == 200) {
                                ordersAdapter = new OrdersAdapter(OrdersActivity.this, result.getReturn());
                                layoutManager = new LinearLayoutManager(OrdersActivity.this);
                                rvOrders.setLayoutManager(layoutManager);
                                rvOrders.setAdapter(ordersAdapter);
                                pbOrders.setVisibility(View.GONE);
                            } else {
                                layMain.setVisibility(View.GONE);
                                layEmpty.setVisibility(View.VISIBLE);
                                pbOrders.setVisibility(View.GONE);
                            }
                        }
                    }
                });


    }

    public String cartSize() {
        DaoSession daoSession = ((Vir) getApplication()).getDaoSession();
        cartProductsDao = daoSession.getCartProductsDao();
        Query<CartProducts> cartProductQuery = cartProductsDao.queryBuilder().build();
        return String.valueOf(cartProductQuery.list().size());
    }
    private void initViews() {

        ibSearch = (ImageButton) findViewById(R.id.ib_search);
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrdersActivity.this, SearchActivity.class));
            }
        });

        UserId = Utils.getCachedString(OrdersActivity.this, "USERID", "");
        layMain = (RelativeLayout) findViewById(R.id.lay_current_orders_main);
        layEmpty = (LinearLayout) findViewById(R.id.lay_orders_error);
        rvOrders = (RecyclerView) findViewById(R.id.rv_current_orders);
        pbOrders = (ProgressBar) findViewById(R.id.pb_orders);
        pbOrders = (ProgressBar) findViewById(R.id.pb_orders);

        tvCart = (TextView) findViewById(R.id.tv_cartSize);
//        tvCart.setText(SplashActivity.cartSize());
        if (cartSize().equals("0")){
            tvCart.setVisibility(View.GONE);
            tvCart.setText(cartSize());
        }else
        {
            tvCart.setText(cartSize());
            tvCart.setVisibility(View.VISIBLE);
        }
        ibCart = (ImageButton) findViewById(R.id.ib_cart);
        ibCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrdersActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cartSize().equals("0")){
            tvCart.setVisibility(View.GONE);
            tvCart.setText(cartSize());
        }else
        {
            tvCart.setText(cartSize());
            tvCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDialogClosed(int id) {
    }
}
