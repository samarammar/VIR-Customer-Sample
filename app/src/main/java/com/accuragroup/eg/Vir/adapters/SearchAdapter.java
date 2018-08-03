package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.interfaces.PaginationAdapterCallback;
import com.accuragroup.eg.Vir.models.Responces.Shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 7/20/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final int ITEM = 0; //shop
    private static final int LOADING = 1; //loading


    private List<Shop> searchModelList;
    private List<Shop> shopListFilterd;
    private Context context;
    private OnItemClickListener listener;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMsg;



    public SearchAdapter(Context context, OnItemClickListener listener) {
        searchModelList=new ArrayList<>();
        this.context = context;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCallback = (PaginationAdapterCallback) context;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder =null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View itemView = inflater.inflate(R.layout.item_search_reasult, parent, false);
                viewHolder = new ShopViewHolder(itemView);
                break;

            case LOADING:
                View loadingView = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new ShopLoadingViewHolder(loadingView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Shop shop = searchModelList.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                final ShopViewHolder shopViewHolder = (ShopViewHolder) holder;

                shopViewHolder.bind(searchModelList.get(position), listener);
                shopViewHolder.tvShopName.setText(shop.getShopName());
                shopViewHolder.tvShopCity.setText(shop.getShopCity());
                shopViewHolder.tvShopGovern.setText(shop.getShopGov());
                shopViewHolder.rbShopRate.setRating(shop.getShopRate());
                List<String> list = shop.getShopCategories();
                StringBuilder builder = new StringBuilder();
                if (list.size()!=0) {
                    for (int i = 0; i < list.size(); i++) {

                        if (i==list.size()-1){
                            builder.append(list.get(i).toString());
                        }else {
                            builder.append(list.get(i).toString() + ", ");
                        }
                    }

                shopViewHolder.tvShopCat.setText(builder.toString());
                }else {
                    shopViewHolder.tvShopCat.setText("");
                }
                String shopImage = shop.getShopImage();
                if (shopImage != null) {
                    Picasso.with(context).load(shopImage).resize(100, 100).placeholder(R.drawable.placeholder).into(shopViewHolder.ivShopImage);
                }

                if (shop.getShopCity().replaceAll("^\"|\"$", "").equals("")) {
                    shopViewHolder.tv_comma.setVisibility(View.INVISIBLE);
                } else {
                    shopViewHolder.tv_comma.setVisibility(View.VISIBLE);
                }

                break;

            case LOADING:
                ShopLoadingViewHolder loadingViewHolder = (ShopLoadingViewHolder) holder;
                if (retryPageLoad) {
                    loadingViewHolder.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingViewHolder.mProgressBar.setVisibility(View.GONE);

                    loadingViewHolder.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    context.getString(R.string.error_msg_unknown));

                } else {
                    loadingViewHolder.mErrorLayout.setVisibility(View.GONE);
                    loadingViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                }

                break;
//                shopViewHolder.tvShopName.setText(shop.getShopName());
//                shopViewHolder.tvShopCity.setText(shop.getShopCity());
//                shopViewHolder.tvShopRate.setText(String.valueOf(shop.getShopRate()));
//                String shopImage = shop.getShopImage();
//                if (shopImage != null) {
//                    Picasso.with(context).load(shop.getShopImage()).into(shopViewHolder.ivShopImage);
//                    shopViewHolder.pbShopImage.setVisibility(View.GONE);
//
//                } else {
//                    shopViewHolder.pbShopImage.setVisibility(View.VISIBLE);
//
//                }


        }







    }


    @Override
    public int getItemCount() {
        return searchModelList == null ? 0 : searchModelList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM;
        } else {
            return (position == searchModelList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        }
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivShopImage;
        private TextView tvShopName, tvShopCat, tvShopCity, tvShopGovern, tv_comma;
        private RatingBar rbShopRate;

        public ShopViewHolder(View itemView) {
            super(itemView);
            tvShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
            tvShopCat = (TextView) itemView.findViewById(R.id.tv_shop_cat);
            tvShopCity = (TextView) itemView.findViewById(R.id.tv_shop_city);
            tvShopGovern = (TextView) itemView.findViewById(R.id.tv_shop_govern);
            rbShopRate = (RatingBar) itemView.findViewById(R.id.rb_shop_rating);
            ivShopImage = (ImageView) itemView.findViewById(R.id.iv_shope_image);
            tv_comma = (TextView) itemView.findViewById(R.id.comma_search);

        }

        public void bind(final Shop model, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(model);

                }

            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Shop model);
    }


    //loading shop item view holder

    protected class ShopLoadingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public ShopLoadingViewHolder(View itemView) {
            super(itemView);


            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = (ImageButton) itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = (TextView) itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = (LinearLayout) itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:
                    showRetry(false, null);
                    mCallback.retryPageLoad();
                    break;
            }
        }
    }

    //Helpers


    public void add(Shop shop) {
        searchModelList.add(shop);
        notifyItemInserted(searchModelList.size() - 1);
    }

    public void addAll(List<Shop> shopList) {
        for (Shop shop : shopList) {
            add(shop);
        }
    }

    public void remove(Shop shop) {
        int position = searchModelList.indexOf(shop);
        if (position > -1) {
            searchModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Shop());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = searchModelList.size() - 1;
        Shop shop = getItem(position);

        if (shop != null) {
            searchModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Shop getItem(int position) {
        return searchModelList.get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     */


    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(searchModelList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    shopListFilterd = searchModelList;
                } else {
                    List<Shop> filteredList = new ArrayList<>();
                    for (Shop shop : searchModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (shop.getShopName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(shop);
                        }
                    }

                    shopListFilterd = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = shopListFilterd;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                shopListFilterd = (ArrayList<Shop>) results.values;
                notifyDataSetChanged();
            }
        };
    }




}
