package com.accuragroup.eg.Vir.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.Vir;
import com.accuragroup.eg.Vir.adapters.SearchAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.dialogs.BottomSheetDialogScreen;
import com.accuragroup.eg.Vir.interfaces.PaginationScrollListener;
import com.accuragroup.eg.Vir.models.Entities.CartProducts;
import com.accuragroup.eg.Vir.models.Entities.CartProductsDao;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.accuragroup.eg.Vir.models.FilterModel;
import com.accuragroup.eg.Vir.models.Request.SubCatRequest;
import com.accuragroup.eg.Vir.models.Responces.Shop;
import com.accuragroup.eg.Vir.models.Responces.ShopsResponse;
import com.accuragroup.eg.Vir.models.Responces.SubCat;
import com.accuragroup.eg.Vir.models.Responces.SubCategoryResponse;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 8/27/2017.
 */

public class CatItemsActivity extends ParentActivity implements com.accuragroup.eg.Vir.dialogs.BottomSheetDialogScreen.BottomSheetListener {

    private TabLayout SubCatsLay;
    private List<Integer> SubCatsId = new ArrayList<>();
    private List<SubCat> categoryList = new ArrayList<SubCat>();


    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES;
    private int currentPage = Const.PAGE_START;
    LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    ProgressBar progressBar;


    Bundle bundle;
    int subCatId=0;
    private ImageButton ibSearch;
    private SearchAdapter searchAdapter;

    private LinearLayout layEmpaty;
    public CartProductsDao cartProductsDao;
    String Cat_id,Cat_name;
    private FloatingActionButton fabFilter;

    private int govId=0, cityId=0,zoneId=0,sectionId=0;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    BottomSheetDialogScreen BottomSheetDialogScreen;
    TextView tv_no_shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        setTitle(getString(R.string.stores));
        enableBackButton();
        getDataFromIntent();
        initViesw();
        setTitle(Cat_name);


        if (Utils.hasConnection(this)) {
            getSubCategories(Cat_id);
            setUpTabsListner();

            recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    currentPage += 1;

                    loadNextPage(subCatId);
                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });


        }else{
            showEmpty();
            progressBar.setVisibility(View.GONE);
        }


    }

    private void initViesw() {
        tv_no_shops=(TextView)findViewById(R.id.tv_no_shops);
        fabFilter=(FloatingActionButton)findViewById(R.id.fabFilter);
        fabFilter.setOnClickListener(this);
        BottomSheetDialogScreen=new BottomSheetDialogScreen();


        tvCart = (TextView) findViewById(R.id.tv_cartSize);
        if (cartSize().equals("0")){
            tvCart.setVisibility(View.GONE);
            Log.i("true",cartSize());
            tvCart.setText(cartSize());

        }else {
            Log.i("false",cartSize());
            tvCart.setText(cartSize());
            tvCart.setVisibility(View.VISIBLE);
        }
        bundle = new Bundle();

        recyclerView = (RecyclerView) findViewById(R.id.rv_subCats);
        layEmpaty = (LinearLayout) findViewById(R.id.lay_empty);

        SubCatsLay = (TabLayout) findViewById(R.id.sub_cats_tabs);
        ibSearch = (ImageButton) findViewById(R.id.ib_search);
        ibSearch.setOnClickListener(this);


        ibCart = (ImageButton) findViewById(R.id.ib_cart);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        searchAdapter = new SearchAdapter( CatItemsActivity.this, new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Shop model) {
                Log.i("commingcatid", String.valueOf(subCatId));
                startActivity(new Intent(CatItemsActivity.this, ShopActivity.class)
                        .putExtra("shopName", model.getShopName())
                        .putExtra("shopId", Integer.valueOf(model.getID()))
                        .putExtra("keyword","407")
                        .putExtra("subcatid",subCatId)
                        .putExtra("catid",Cat_id)
                );
            }
        });

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ib_search:
                startActivity(new Intent(CatItemsActivity.this, SearchActivity.class));
                break;
            case R.id.fabFilter:
                Bundle bundle = new Bundle();
                bundle.putString("catid", Cat_id);
                BottomSheetDialogScreen.setArguments(bundle);
                BottomSheetDialogScreen.show(getSupportFragmentManager(),"bottomSheetDialogFragment");
                break;
            case R.id.ib_cart:
                startActivity(new Intent(CatItemsActivity.this, CartActivity.class));
                break;
        }

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();

        Cat_id = intent.getExtras().getString("cat_parent_id");
        Cat_name = intent.getExtras().getString("catName");

        settings = this.getSharedPreferences("BottomSheet", Context.MODE_PRIVATE);
        editor = settings.edit();

        String json = settings.getString(Cat_id, "");
        Gson gson = new Gson();
        FilterModel obj = gson.fromJson(json, FilterModel.class);
        if(settings.contains(Cat_id)){
            govId=obj.getGovId();
            cityId=obj.getCityId();
            zoneId=obj.getZoneId();
            sectionId=obj.getSectionId();

        }else {
            govId=0;
            cityId=0;
            zoneId=0;
            sectionId=0;
        }


    }

    private void getSubCategories(String catId) {
        SubCatRequest params = new SubCatRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setCatId(Integer.valueOf(catId));
        params.setLangu(Utils.getCachedString(CatItemsActivity.this, Const.Language, ""));

        Ion.with(CatItemsActivity.this)
                .load(ServiceApi.Service.getCategories.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<SubCategoryResponse>() {
                }).setCallback(new FutureCallback<SubCategoryResponse>() {
            @Override
            public void onCompleted(Exception e, SubCategoryResponse result) {
                if (e != null) {
                    hideProgressDialog();
                    Log.wtf(Const.LOG_TAG, String.valueOf(e));
                } else {
                    if (result.getStatus() == 200) {
                        categoryList = result.getReturn();
                        categoryList.add(0, new SubCat(0, getString(R.string.all)));
                        for (int i = 0; i < categoryList.size(); i++) {
                            SubCatsLay.addTab(SubCatsLay.newTab().setText(result.getReturn().get(i).getCatName()).setTag(result.getReturn().get(i).getCatId()));
                            SubCatsId.add(result.getReturn().get(i).getCatId());
                        }
                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        SubCatsLay.getTabAt(0).select();
                                    }
                                }, 100);

//
                    } else {
                        Utils.showShortToast(CatItemsActivity.this, getString(R.string.connectionFieldTryAgain));

                    }
                }
            }
        });
    }

    private void setUpTabsListner() {
        SubCatsLay.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    searchAdapter.clear();
                    currentPage = Const.PAGE_START;
                    isLoading = false;
                    isLastPage = false;
                    progressBar.setVisibility(View.VISIBLE);
                    subCatId=0;
                    loadFirstPage(0);

                } else {
                    subCatId = SubCatsId.get(tab.getPosition());
                    searchAdapter.clear();
                    currentPage = Const.PAGE_START;
                    isLoading = false;
                    isLastPage = false;
                    progressBar.setVisibility(View.VISIBLE);
                    loadFirstPage(subCatId);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                subCatId = SubCatsId.get(tab.getPosition());


            }
        });
    }

    public String cartSize() {
        DaoSession daoSession = ((Vir) getApplication()).getDaoSession();
        cartProductsDao = daoSession.getCartProductsDao();
        Query<CartProducts> cartProductQuery = cartProductsDao.queryBuilder().build();
        return String.valueOf(cartProductQuery.list().size());
    }

    private void loadFirstPage(final int subcat) {

        JsonObject data = new JsonObject();
        data.addProperty("catId", Cat_id);
        data.addProperty("langu", Utils.getCachedString(CatItemsActivity.this, Const.Language, ""));
        data.addProperty("subCatId", subcat);
        data.addProperty("page", currentPage);
        data.addProperty("govId", govId);
        data.addProperty("cityId", cityId);
        data.addProperty("sectionId",sectionId);
        data.addProperty("perPage", 10);
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);


        Ion.with(CatItemsActivity.this)
                .load(ServiceApi.Service.getShops.getBaseService())
                .setJsonObjectBody(data)
                .as(new TypeToken<ShopsResponse>() {
                })
                .setCallback(new FutureCallback<ShopsResponse>() {
                    @Override
                    public void onCompleted(Exception e, ShopsResponse result) {
                        if (e != null) {
                            Log.wtf(Const.LOG_TAG, String.valueOf(e));
                            showEmpty();
                        } else {
                            Log.i("subcatid", String.valueOf(subcat));
                            if (result.getStatus() == 200) {
                                progressBar.setVisibility(View.GONE);

                                showList();
                                List<Shop> results = result.getReturn();
                                progressBar.setVisibility(View.GONE);
                                searchAdapter.addAll(results);
                                TOTAL_PAGES = result.getTotalPage();
                                if (currentPage != TOTAL_PAGES) searchAdapter.addLoadingFooter();
                                else isLastPage = true;



                            } else {
                                showEmpty();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });

    }


    private void loadNextPage(final int subcat) {
        JsonObject data = new JsonObject();
        data.addProperty("catId", Cat_id);
        data.addProperty("langu", Utils.getCachedString(CatItemsActivity.this, Const.Language, ""));
        data.addProperty("subCatId", subcat);
        data.addProperty("page", currentPage);
        data.addProperty("govId", govId);
        data.addProperty("cityId", cityId);
        data.addProperty("sectionId",sectionId);
        data.addProperty("perPage", 10);
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);


        Ion.with(CatItemsActivity.this)
                .load(ServiceApi.Service.getShops.getBaseService())
                .setJsonObjectBody(data)
                .as(new TypeToken<ShopsResponse>() {
                })
                .setCallback(new FutureCallback<ShopsResponse>() {
                    @Override
                    public void onCompleted(Exception e, ShopsResponse result) {
                        if (e != null) {
                            Log.wtf(Const.LOG_TAG, String.valueOf(e));
                            showEmpty();
                        } else {
                            Log.i("subcatid", String.valueOf(subcat));
                            if (result.getStatus() == 200) {
                                searchAdapter.removeLoadingFooter();
                                isLoading = false;

                                List<Shop> results = result.getReturn();
                                searchAdapter.addAll(results);

                                if (currentPage != TOTAL_PAGES) searchAdapter.addLoadingFooter();
                                else isLastPage = true;


                            } else {
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });

    }


    private void showEmpty() {
        layEmpaty.setVisibility(View.VISIBLE);
        tv_no_shops.setVisibility(View.VISIBLE);
    }

    private void showList() {
        layEmpaty.setVisibility(View.GONE);
        tv_no_shops.setVisibility(View.GONE);
    }


    @Override
    public void retryPageLoad() {
        loadNextPage(subCatId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        tvCart = (TextView) findViewById(R.id.tv_cartSize);
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
    public void onButtonClicked(Boolean text) {
        finish();
        startActivity(getIntent());
    }
}
