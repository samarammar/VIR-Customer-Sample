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

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.adapters.CityAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.models.Request.CityiesRequest;
import com.accuragroup.eg.Vir.models.Responces.City;
import com.accuragroup.eg.Vir.models.Responces.CityResponse;
import com.accuragroup.eg.Vir.models.Responces.Government;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class CityDialog extends DialogFragment {

    List<City> cityList = new ArrayList<>();
    private RecyclerView recyclerView;
    CityAdapter cityAdapter;
    private ProgressBar progressBar;
    protected OnDialogClickedListener callback = null;

    private RecyclerView.LayoutManager layoutManager;
    int GovernmentId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_city, container, false);
        Bundle bundle = getArguments();
        GovernmentId = bundle.getInt("GovId");
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_city);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        this.getDialog().setTitle(getString(R.string.chooseCity));

        loadData();
        return view;
    }

    private void loadData() {
        CityiesRequest params = new CityiesRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setLangu(Utils.getCachedString(getActivity(), Const.Language, ""));
        params.setGovId(GovernmentId);
        params.setCustomer_app(1);

        progressBar.setVisibility(View.VISIBLE);
        Ion.with(getActivity())
                .load(ServiceApi.Service.getCities.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<CityResponse>() {
                })
                .setCallback(new FutureCallback<CityResponse>() {
                    @Override
                    public void onCompleted(Exception e, CityResponse result) {

                        if (e != null) {
                            Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            cityList = result.getReturn();
                            cityList.add(0,new City("0",getString(R.string.all_cities)));
                            cityAdapter = new CityAdapter(cityList, getActivity(), new CityAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(City item) {
                                    callback.onDialogClicked(Integer.valueOf(item.getCityId()), item.getCityName());
                                    dismiss();
                                }


                            });
                            layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(cityAdapter);
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

    }

    public void setOnDialogClickedListener(OnDialogClickedListener l) {
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
