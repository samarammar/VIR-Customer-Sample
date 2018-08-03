package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.models.Responces.Zone;

import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.MyViewHolder> {


    private List<Zone> zoneList;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public ZoneAdapter(List<Zone> zones, Context context, OnItemClickListener listener) {
        this.zoneList = zones;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ZoneAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new ZoneAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ZoneAdapter.MyViewHolder holder, int position) {

        holder.bind(zoneList.get(position), listener);
        Zone zone = zoneList.get(position);
        holder.tvName.setText(zone.getZoneName());
    }

    @Override
    public int getItemCount() {
        return zoneList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final Zone item, final ZoneAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Zone item);
    }
}
