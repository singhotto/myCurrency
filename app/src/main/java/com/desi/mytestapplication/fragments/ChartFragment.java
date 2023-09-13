package com.desi.mytestapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.desi.mytestapplication.R;
import com.desi.mytestapplication.dataModel.Currency;
import com.desi.mytestapplication.dataModel.CurrencyList;
import com.desi.mytestapplication.utilities.CurrencyDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChartFragment extends Fragment implements CurrencyDialog.DialogListener {

    private LineChart lineChart;
    private ArrayList<CurrencyList> list;
    private Currency firstCurr, secondCurr;

    private int days;

    TextView selectCurr1, selectCurr2;
    ImageView selectImage1, selectImage2;
    View currencySelectLayout1, currencySelectLayout2;
    MaterialButton day7, day10, day15, day30;
    public ChartFragment(ArrayList<CurrencyList> list){
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_chart, container, false);

        selectCurr1 = rootView.findViewById(R.id.currency_selectText1);
        selectCurr2 = rootView.findViewById(R.id.currency_selectText2);
        selectImage1 = rootView.findViewById(R.id.select_image1);
        selectImage2 = rootView.findViewById(R.id.select_image2);
        currencySelectLayout1 = rootView.findViewById(R.id.currency_select_layout1);
        currencySelectLayout2 = rootView.findViewById(R.id.currency_select_layout2);
        lineChart = rootView.findViewById(R.id.chart);
        day7 = rootView.findViewById(R.id.day7);
        day10 = rootView.findViewById(R.id.day10);
        day15 = rootView.findViewById(R.id.day15);
        day30 = rootView.findViewById(R.id.day30);

        firstCurr = list.get(0).getCurrencyByCode("EUR");
        secondCurr = list.get(0).getCurrencyByCode("INR");

        days = 7;
        day7.setTextColor(getResources().getColor(R.color.white));
        day7.setBackgroundColor(getResources().getColor(R.color.select_btn));

        currencySelectLayout1.setOnClickListener(v -> {
            showDialogBox(1, list.get(0));
        });
        currencySelectLayout2.setOnClickListener(v -> {
            showDialogBox(2, list.get(0));
        });
        day7.setOnClickListener(v->{
            changePeriod(7);
        });
        day10.setOnClickListener(v-> {
            changePeriod(10);
        });
        day15.setOnClickListener(v-> {
            changePeriod(15);
        });
        day30.setOnClickListener(v-> {
            changePeriod(30);
        });
        dataUpdate();
        return rootView;
    }

    private void changePeriod(int dd){
        days = dd;
        day7.setTextColor(getResources().getColor(R.color.white));
        day10.setTextColor(getResources().getColor(R.color.white));
        day15.setTextColor(getResources().getColor(R.color.white));
        day30.setTextColor(getResources().getColor(R.color.white));
        day7.setBackgroundColor(getResources().getColor(R.color.keypad_ops));
        day10.setBackgroundColor(getResources().getColor(R.color.keypad_ops));
        day15.setBackgroundColor(getResources().getColor(R.color.keypad_ops));
        day30.setBackgroundColor(getResources().getColor(R.color.keypad_ops));

        if(days == 7){
            day7.setTextColor(getResources().getColor(R.color.white));
            day7.setBackgroundColor(getResources().getColor(R.color.select_btn));
        }

        if(days == 10){
            day10.setTextColor(getResources().getColor(R.color.white));
            day10.setBackgroundColor(getResources().getColor(R.color.select_btn));
        }

        if(days == 15){
            day15.setTextColor(getResources().getColor(R.color.white));
            day15.setBackgroundColor(getResources().getColor(R.color.select_btn));
        }

        if(days == 30){
            day30.setTextColor(getResources().getColor(R.color.white));
            day30.setBackgroundColor(getResources().getColor(R.color.select_btn));
        }
        dataUpdate();
    }

    void showDialogBox(int idx, CurrencyList list) {
        CurrencyDialog dialog = new CurrencyDialog(requireContext(), list.getAllCurrencies(), this, idx) {
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

    private void updateChart(){
        ArrayList<String> xVal = new ArrayList<>();
        List<Entry> entries = new ArrayList<>();

        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        double max = 0;
        double min = 1000;

        for(int i = 0;i<days;i++){
            Currency currF = list.get(days-i-1).getCurrencyByCode(firstCurr.getCode());
            Currency currS = list.get(days-i-1).getCurrencyByCode(secondCurr.getCode());
            double res = (1/ currF.getRate())*currS.getRate();
            if(days>15){
                if(i % 2 == 1){
                    calendar.add(Calendar.DAY_OF_YEAR, -2);
                    SimpleDateFormat formatD = new SimpleDateFormat("dd/MM/yyyy");
                    xVal.add(formatD.format(calendar.getTime()));
                }else{
                    xVal.add("");
                }
            }else{
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                SimpleDateFormat formatD = new SimpleDateFormat("dd/MM/yyyy");
                xVal.add(formatD.format(calendar.getTime()));
            }
            entries.add(new Entry(i, Float.parseFloat(Double.toString(res))));
            if(res>max)
                max = res;
            if(res<min)
                min = res;
        }

        Collections.reverse(xVal);
        double avg = (max-min)*.1;
        lineChart.getAxisRight().setDrawLabels(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelRotationAngle(90f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVal));
        xAxis.setLabelCount(days+1);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        yAxis.setAxisMinimum(Float.parseFloat(Double.toString(min-avg)));
        yAxis.setAxisMaximum(Float.parseFloat(Double.toString(max+avg)));
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);


        LineDataSet dataSet = new LineDataSet(entries, "Rates");
        dataSet.setColor(Color.YELLOW);
        dataSet.setValueTextColor(Color.WHITE);

        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);

        lineChart.invalidate();
    }

    private void dataUpdate(){
        selectCurr1.setText(firstCurr.getCode());
        selectImage1.setImageResource(getResources().getIdentifier(firstCurr.getCode().toLowerCase(), "drawable", requireContext().getPackageName()));

        selectCurr2.setText(secondCurr.getCode());
        selectImage2.setImageResource(getResources().getIdentifier(secondCurr.getCode().toLowerCase(), "drawable", requireContext().getPackageName()));

        updateChart();
    }

    @Override
    public void onCurrencySelected(int x, Currency currency){
        if(x == 1){
            firstCurr = currency;
        }else {
            secondCurr = currency;
        }
        dataUpdate();
    }
}