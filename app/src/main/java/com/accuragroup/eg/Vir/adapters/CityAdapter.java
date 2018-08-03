package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.models.Responces.City;

import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {


    private List<City> cityList;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public CityAdapter(List<City> cities, Context context, OnItemClickListener listener) {
        this.cityList = cities;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new CityAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityAdapter.MyViewHolder holder, int position) {

        holder.bind(cityList.get(position), listener);
        City government = cityList.get(position);
        holder.tvName.setText(government.getCityName());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final City item, final CityAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(City item);
    }
}
