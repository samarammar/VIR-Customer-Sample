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
import com.accuragroup.eg.Vir.adapters.GovernmentAdapter;
import com.accuragroup.eg.Vir.connections.ServiceApi;
import com.accuragroup.eg.Vir.models.Request.DefaultRequest;
import com.accuragroup.eg.Vir.models.Responces.Government;
import com.accuragroup.eg.Vir.models.Responces.GovernmentResponse;
import com.accuragroup.eg.Vir.models.Responces.SubCat;
import com.accuragroup.eg.Vir.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class GovernmentDialog extends DialogFragment {

    List<Government> governmentList = new ArrayList<>();
    private RecyclerView recyclerView;
    GovernmentAdapter governmentAdapter;
    private ProgressBar progressBar;
    protected OnDialogClickedListener callback = null;

    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_government, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_government);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        this.getDialog().setTitle(getString(R.string.government));

        loadData();
        return view;
    }

    private void loadData() {
        DefaultRequest params = new DefaultRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setLangu(Utils.getCachedString(getActivity(), Const.Language, ""));
        params.setCustomer_app(1);

        progressBar.setVisibility(View.VISIBLE);
        Ion.with(getActivity())
                .load(ServiceApi.Service.getGovernorates.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<GovernmentResponse>() {
                })
                .setCallback(new FutureCallback<GovernmentResponse>() {
                    @Override
                    public void onCompleted(Exception e, GovernmentResponse result) {

                        if (e != null) {
                            Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            governmentList = result.getReturn();
                            governmentList.add(0,new Government("0",getString(R.string.all_governorates)));
                            governmentAdapter = new GovernmentAdapter(governmentList, getActivity(), new GovernmentAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Government item) {
                                    callback.onDialogClicked(Integer.valueOf(item.getGovernmentsId()), item.getGovernmentsName());
                                    dismiss();
                                }
                            });
                            layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(governmentAdapter);
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
