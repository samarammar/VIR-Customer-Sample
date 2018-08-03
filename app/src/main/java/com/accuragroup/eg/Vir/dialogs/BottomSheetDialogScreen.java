package com.accuragroup.eg.Vir.dialogs;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.accuragroup.eg.Vir.R;
import com.accuragroup.eg.Vir.dialogs.CityDialog;
import com.accuragroup.eg.Vir.dialogs.GovernmentDialog;
import com.accuragroup.eg.Vir.dialogs.ZoneDialog;
import com.accuragroup.eg.Vir.models.FilterModel;
import com.google.gson.Gson;

/**
 * Created by Apex on 3/25/2018.
 */

public class BottomSheetDialogScreen extends BottomSheetDialogFragment implements View.OnClickListener {
    private BottomSheetListener mListener;
    private EditText etGovernment, etCity, etZone;
    private Button btnFilter;
    FragmentManager fragmentManager;
    GovernmentDialog governmentDialog;
    CityDialog cityDialog;
    ZoneDialog zoneDialog;
    private int governId = 0, cityId = 0, zoneId = 0,section = 0;
    private String govName;
    private String cityName,catid,zoneName;

    FilterModel filterModel=new FilterModel();
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Spinner sp_section_filter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottome_sheet_dialog,container,false);

        getDataFromIntent();
        initViews(v);
        initListeners(v);
        setData();
        return v;
    }

    private void  getDataFromIntent(){
        Bundle bundle = getArguments();
        catid = bundle.getString("catid");

    }
    private void setData(){
        settings = getActivity().getSharedPreferences("BottomSheet", Context.MODE_PRIVATE);
        editor = settings.edit();

        String json = settings.getString(catid, "");
        Log.i("catid",catid);
        Gson gson = new Gson();
        FilterModel obj = gson.fromJson(json, FilterModel.class);
        if(settings.contains(catid)){
            governId=obj.getGovId();
            cityId=obj.getCityId();
            zoneId=obj.getZoneId();

            section=obj.getSectionId();

            govName=obj.getGovName();
            cityName=obj.getCityName();
            zoneName=obj.getZoneName();

            if (governId!=0){
                etGovernment.setText(govName);
            }

            if (cityId!=0){
                etCity.setText(cityName);
            }

            if (zoneId!=0) {
                etZone.setText(zoneName);
            }

            sp_section_filter.setSelection(section);
            Log.i("section", String.valueOf(section));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_govern_filter:
                governmentDialog.show(fragmentManager, "");
                break;


            case R.id.et_cities_filter:
                Bundle bundle = new Bundle();
                bundle.putInt("GovId", governId);
                cityDialog.setArguments(bundle);
                cityDialog.show(fragmentManager, "");
                break;
            case R.id.et_zones_filter:
                Bundle bundlezone = new Bundle();
                bundlezone.putInt("GovId", governId);
                bundlezone.putInt("CityId", cityId);
                zoneDialog.setArguments(bundlezone);
                zoneDialog.show(fragmentManager, "");
                break;

            case R.id.btn_Filter:
                if (cityId==0) {
                    filterModel.setCityId(0);
                    filterModel.setCityName("");
                }else{
                    filterModel.setCityId(cityId);
                    filterModel.setCityName(cityName);
                }
                if (governId==0) {
                    filterModel.setGovName("");
                    filterModel.setGovId(0);
                }else {
                    filterModel.setGovName(govName);
                    filterModel.setGovId(governId);
                }
                if (zoneId==0) {
                    filterModel.setZoneId(0);
                    filterModel.setZoneName("");
                }else {
                    filterModel.setZoneId(zoneId);
                    filterModel.setZoneName(zoneName);
                }

                if (section==0){
                    filterModel.setSectionId(0);
                }else {
                    filterModel.setSectionId(section);
                }


                Gson gson=new Gson();
                String json=gson.toJson(filterModel);
                editor.putString(catid, json);
                editor.commit();

                mListener.onButtonClicked(true);
                dismiss();
                break;
        }
    }

    public interface BottomSheetListener{
        void onButtonClicked(Boolean text);
    }

    private void initViews(View view){

        btnFilter=(Button)view.findViewById(R.id.btn_Filter);
        etGovernment = (EditText)view.findViewById(R.id.et_govern_filter);
        etCity = (EditText) view.findViewById(R.id.et_cities_filter);
        etZone = (EditText) view.findViewById(R.id.et_zones_filter);

        fragmentManager = getActivity().getFragmentManager();
        cityDialog = new CityDialog();

        governmentDialog = new GovernmentDialog();

        zoneDialog = new ZoneDialog();


    }

    private void initListeners(View view){
        etGovernment.setOnClickListener(this);
        etCity.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
        etZone.setOnClickListener(this);

        governmentDialog.setOnDialogClickedListener(new GovernmentDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name) {
                governId = position;
                etGovernment.setText(name);
                govName=name;

            }
        });


        cityDialog.setOnDialogClickedListener(new CityDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name) {
                cityId = position;
                etCity.setText(name);
                cityName=name;
            }
        });
        zoneDialog.setOnDialogClickedListener(new ZoneDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name) {
                zoneId = position;
                etZone.setText(name);
            }
        });

        String[] Section = {getString(R.string.all_sections), getString(R.string.men), getString(R.string.women), getString(R.string.kids)};
        sp_section_filter=(Spinner)view.findViewById(R.id.sp_section_filter);
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Section);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_section_filter.setAdapter(sectionAdapter);
        sp_section_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section = sp_section_filter.getSelectedItemPosition();            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener=(BottomSheetListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement BottomSheetListener");
        }

    }
}
