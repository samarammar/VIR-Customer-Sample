package com.accuragroup.eg.Vir.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.adapters.ZoneAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.models.Request.ZoneRequest;
import com.accuragroup.eg.Vir.models.Responces.City;
import com.accuragroup.eg.Vir.models.Responces.Zone;
import com.accuragroup.eg.Vir.models.Responces.ZoneResponse;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 11/6/2017.
 */

public class ZoneDialog extends DialogFragment {

    List<Zone> zoneList = new ArrayList<>();
    private RecyclerView recyclerView;
    ZoneAdapter zoneAdapter;
    private ProgressBar progressBar;
    protected OnDialogClickedListener callback = null;

    private RecyclerView.LayoutManager layoutManager;
    int GovernmentId;
    int CitytId;
    TextView tvNoZones;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_city, container, false);
        Bundle bundle = getArguments();
        GovernmentId = bundle.getInt("GovId");
        CitytId = bundle.getInt("CityId");
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_city);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        tvNoZones=(TextView)view.findViewById(R.id.tv_no_zones);

        this.getDialog().setTitle(getString(R.string.chooseCity));

        loadData();
        return view;
    }

    private void loadData() {
        ZoneRequest params = new ZoneRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setLangu(Utils.getCachedString(getActivity(), Const.Language, ""));
        params.setGovId(GovernmentId);
        params.setCityId(CitytId);
        params.setCustomer_app(1);

        progressBar.setVisibility(View.VISIBLE);
        Ion.with(getActivity())
                .load(ServiceApi.Service.getZones.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<ZoneResponse>() {
                })
                .setCallback(new FutureCallback<ZoneResponse>() {
                    @Override
                    public void onCompleted(Exception e, ZoneResponse result) {
                        if (e != null) {
                            Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            if (result.getStatus() == 200) {
                                zoneList = result.getReturn();
                                zoneList.add(0,new Zone("0",getString(R.string.all_zones)));
                                zoneAdapter = new ZoneAdapter(zoneList, getActivity()
                                        , new ZoneAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(Zone item) {
                                        callback.onDialogClicked(Integer.parseInt(item.getZoneId()), item.getZoneName());
                                        dismiss();
                                    }
                                });

                                layoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(zoneAdapter);
                                progressBar.setVisibility(View.GONE);
                            } else {
                                progressBar.setVisibility(View.GONE);
                                tvNoZones.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                tvNoZones.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dismiss();
                                    }
                                });
                            }
                        }
                    }
                });

    }


    public void setOnDialogClickedListener(ZoneDialog.OnDialogClickedListener l) {
        callback = l;
    }


    public interface OnDialogClickedListener {
        public abstract void onDialogClicked(int position, String name);
    }

    @Override
    public void onStop() {
        Ion.getDefault(getActivity()).cancelAll(getActivity());
        super.onStop();
    }
}
