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

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {
    private ArrayList<Currency> currencyList;
    private Context context;
    public RateAdapter(Context context, ArrayList<Currency> c_list){
        currencyList = c_list;
        this.context = context;
    }

    @NonNull
    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rates_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateAdapter.ViewHolder holder, int position) {
        Currency currency =  currencyList.get(position);
        holder.currencyName.setText(currency.getName());
        holder.currencyCode.setText(currency.getCode());
        if((""+currency.getRate()).length() >=8){
            holder.currencyRate.setText((""+currency.getRate()).substring(0, 8));
        }else{
            holder.currencyRate.setText(""+currency.getRate());
        }
        String imageName = currency.getCode().toLowerCase()!="try"?currency.getCode().toLowerCase():"trl";
        holder.currencyImage.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyName;
        private TextView currencyCode;
        private TextView currencyRate;
        private ImageView currencyImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.rate_list_subtitle);
            currencyCode = itemView.findViewById(R.id.rate_list_title);
            currencyImage = itemView.findViewById(R.id.rate_list_image);
            currencyRate = itemView.findViewById(R.id.rate_list_rate);

        }
    }
}
