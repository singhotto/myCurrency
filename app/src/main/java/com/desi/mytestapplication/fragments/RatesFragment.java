package com.desi.mytestapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.desi.mytestapplication.R;
import com.desi.mytestapplication.adapters.RateAdapter;
import com.desi.mytestapplication.dataModel.Currency;
import com.desi.mytestapplication.dataModel.CurrencyList;
import com.desi.mytestapplication.utilities.CurrencyDialog;

import java.util.ArrayList;

public class RatesFragment extends Fragment implements CurrencyDialog.DialogListener{
    private ArrayList<Currency> list;
    private ArrayList<Currency> updatedList;
    private RateAdapter adapter;

    private Currency baseCurr;
    private Currency euro;

    ImageView baseIcon;
    TextView baseText;

    View rateLayout;

    public RatesFragment(CurrencyList list){
        this.list = list.getAllCurrencies();
        baseCurr = list.getCurrencyByCode("EUR");
        euro = list.getCurrencyByCode("EUR");
        this.updatedList = new ArrayList<>(list.getAllCurrencies());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_rates, container, false);
        rateLayout = rootView.findViewById(R.id.base_country_layout);
        baseText = rootView.findViewById(R.id.rate_layout_base_text);
        baseIcon = rootView.findViewById(R.id.rate_layout_img);
        rateLayout.setOnClickListener(v->{
            showDialogBox(0);
        });
        baseIcon.setImageResource(getResources().getIdentifier(baseCurr.getCode().toLowerCase(), "drawable", requireContext().getPackageName()));
        baseText.setText("1 " + baseCurr.getCode().toUpperCase());
        setUpRecyclerView(rootView);
        return rootView;
    }
    private void setUpRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.rate_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RateAdapter(getContext(), updatedList);
        recyclerView.setAdapter(adapter);
    }

    void showDialogBox(int idx) {
        CurrencyDialog dialog = new CurrencyDialog(requireContext(), list, this, idx) {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                // Set minimum width and height here after the dialog is created
                Window window = getWindow();
                if (window != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(window.getAttributes());

                    layoutParams.width = 650;
                    layoutParams.height = 1000;

                    window.setAttributes(layoutParams);
                }
            }
        };
        dialog.show();
    }


    @Override
    public void onCurrencySelected(int x, Currency currency){
        baseCurr = currency;
        baseIcon.setImageResource(getResources().getIdentifier(baseCurr.getCode().toLowerCase()!="try"?baseCurr.getCode().toLowerCase():"trl", "drawable", requireContext().getPackageName()));
        baseText.setText("1 " + baseCurr.getCode().toUpperCase());
        updatedList.clear();
        for(int i = 0;i<list.size();i++){
            Currency curr = list.get(i);
            updatedList.add(new Currency(curr.getName(), curr.getCode(), (1 / baseCurr.getRate()) * curr.getRate()));
            if(baseCurr.getCode() == updatedList.get(i).getCode()){
                updatedList.get(i).setExchangeRate(1.0);
            }
        }
        adapter.notifyDataSetChanged();
    }

}