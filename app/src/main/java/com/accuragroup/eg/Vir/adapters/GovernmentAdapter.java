package com.accuragroup.eg.Vir.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.models.Responces.Government;

import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class GovernmentAdapter extends RecyclerView.Adapter<GovernmentAdapter.MyViewHolder> {


    private List<Government> governmentList;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public GovernmentAdapter(List<Government> cities, Context context, OnItemClickListener listener) {
        this.governmentList = cities;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public GovernmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new GovernmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GovernmentAdapter.MyViewHolder holder, int position) {

        holder.bind(governmentList.get(position), listener);
        Government government = governmentList.get(position);
        holder.tvName.setText(government.getGovernmentsName());
    }

    @Override
    public int getItemCount() {
        return governmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final Government item, final GovernmentAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Government item);
    }
}
