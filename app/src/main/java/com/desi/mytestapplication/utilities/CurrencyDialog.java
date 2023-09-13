package com.desi.mytestapplication.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.desi.mytestapplication.R;
import com.desi.mytestapplication.adapters.CurrencyAdapter;
import com.desi.mytestapplication.dataModel.Currency;
import com.desi.mytestapplication.dataModel.CurrencyList;

import java.util.ArrayList;

public abstract class CurrencyDialog extends Dialog {
    private ArrayList<Currency> currencyList;
    private ArrayList<Currency> filteredList;
    private CurrencyAdapter currencyAdapter;

    private int idx;
    public interface DialogListener {
        void onCurrencySelected(int x, Currency data);
    }

    private DialogListener listener;
    public CurrencyDialog(Context context, ArrayList<Currency> list, DialogListener listener, int x) {
        super(context);
        this.listener = listener;
        this.currencyList = list;
        this.filteredList = new ArrayList<>(list);
        this.idx = x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_layout, null);
        setContentView(view);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setUpRecyclerView(view);

        SearchView searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                filteredList.clear();
                for (int i = 0;i<currencyList.size();i++) {
                    Currency curr = currencyList.get(i);
                    if (curr.getName().toLowerCase().contains(query.toLowerCase()) || curr.getCode().toLowerCase().contains(query.toLowerCase())) {
                        filteredList.add(curr);
                    }
                }
                currencyAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void setUpRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.currencyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        currencyAdapter = new CurrencyAdapter(getContext(), filteredList, new CurrencyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Currency item) {
                // When an item is clicked, notify the listener in the main activity
                listener.onCurrencySelected(idx, item);
                dismiss(); // Close the dialog
            }
        });
        recyclerView.setAdapter(currencyAdapter);
    }
}
