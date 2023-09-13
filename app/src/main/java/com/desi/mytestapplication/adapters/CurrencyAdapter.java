package com.desi.mytestapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.desi.mytestapplication.R;
import com.desi.mytestapplication.dataModel.Currency;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private ArrayList<Currency> currencyList;
    private Context context;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Currency item);
    }
    public CurrencyAdapter(Context context, ArrayList<Currency> currencyList, OnItemClickListener listener) {
        this.context = context;
        this.currencyList = currencyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency =  currencyList.get(position);
        holder.currencyName.setText(currency.getName());
        holder.currencyCode.setText(currency.getCode());
        String imageName = currency.getCode().toLowerCase()!="try"?currency.getCode().toLowerCase():"trl";
        holder.currencyImage.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(currency);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyName;
        private TextView currencyCode;
        private ImageView currencyImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currency_list_subtitle);
            currencyCode = itemView.findViewById(R.id.currency_list_title);
            currencyImage = itemView.findViewById(R.id.currency_list_image);
        }
    }
}
