package com.desi.mytestapplication.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.desi.mytestapplication.utilities.CurrencyDialog;
import com.desi.mytestapplication.R;
import com.desi.mytestapplication.dataModel.Currency;
import com.desi.mytestapplication.dataModel.CurrencyList;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment implements View.OnClickListener, CurrencyDialog.DialogListener {

    private boolean isCalculated;

    private float textSize;
    private static final String TAG = "HomeFragment";

    CurrencyList list;
    Currency firstCurr, secondCurr;
    TextView firstCountry, firstCountrySubText, secondCountrySubText,  secondCountry, firstCountryText, secondCountryText, calculationText;
    TextView firstTextView, firstSubTextView, secondTextView, secondSubTextView;
    MaterialButton buttonPref, buttonSwap, buttonCopy, buttonSend, buttonClear, buttonDel;
    MaterialButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonPM, buttonDot, buttonEqual;
    MaterialButton button0, button00, button000, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    View firstCurrencyLayout, secondCurrencyLayout;

    ImageView firstCurrencyImage, secondCurrencyImage;

    public HomeFragment(CurrencyList list){
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        firstCurrencyLayout = rootView.findViewById(R.id.firstCurrencyLayout);
        secondCurrencyLayout = rootView.findViewById(R.id.secondCurrencyLayout);
        firstCountry = rootView.findViewById(R.id.firstTextView);
        firstCountryText = rootView.findViewById(R.id.firstCurrencyText);
        firstCountrySubText = rootView.findViewById(R.id.firstSubTextView);
        secondCountry = rootView.findViewById(R.id.secondTextView);
        secondCountryText = rootView.findViewById(R.id.secondCurrencyText);
        secondCountrySubText = rootView.findViewById(R.id.secondSubTextView);
        calculationText = rootView.findViewById(R.id.calculationText);
        firstTextView = rootView.findViewById(R.id.firstTextView);
        firstSubTextView = rootView.findViewById(R.id.firstSubTextView);
        firstCurrencyImage = rootView.findViewById(R.id.firstCurrencyImage);
        secondTextView = rootView.findViewById(R.id.secondTextView);
        secondSubTextView = rootView.findViewById(R.id.secondSubTextView);
        secondCurrencyImage = rootView.findViewById(R.id.secondCurrencyImage);

        assignId(rootView, buttonPref, R.id.preferenceBtn);
        assignId(rootView, buttonSwap, R.id.swapBtn);
        assignId(rootView, buttonCopy, R.id.copyBtn);
        assignId(rootView, buttonSend, R.id.sendBtn);
        assignId(rootView, buttonClear, R.id.clearBtn);

        assignId(rootView, buttonDel, R.id.delBtn);
        assignId(rootView, buttonPlus, R.id.plusBtn);
        assignId(rootView, buttonMinus, R.id.minusBtn);
        assignId(rootView, buttonMultiply, R.id.multiplyBtn);
        assignId(rootView, buttonDivide, R.id.divideBtn);
        assignId(rootView, buttonPM, R.id.plusMinusBtn);
        assignId(rootView, buttonDot, R.id.dotBtn);
        assignId(rootView, buttonEqual, R.id.equalBtn);

        assignId(rootView, button0, R.id.zeroBtn);
        assignId(rootView, button00, R.id.zero2Btn);
        assignId(rootView, button000, R.id.zero3Btn);
        assignId(rootView, button1, R.id.oneBtn);
        assignId(rootView, button2, R.id.twoBtn);
        assignId(rootView, button3, R.id.threeBtn);
        assignId(rootView, button4, R.id.fourBtn);
        assignId(rootView, button5, R.id.fiveBtn);
        assignId(rootView, button6, R.id.sixBtn);
        assignId(rootView, button7, R.id.sevenBtn);
        assignId(rootView, button8, R.id.eightBtn);
        assignId(rootView, button9, R.id.nineBtn);

        textSize = firstCountryText.getTextSize();

        firstCountryText.setText("1");

        firstCurrencyLayout.setOnClickListener(v -> {
            showDialogBox(1, list);
        });
        secondCurrencyLayout.setOnClickListener(v -> {
            showDialogBox(2, list);
        });
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyCurrency", requireContext().MODE_PRIVATE);


        String curr1 = sharedPreferences.getString("currency1", "EUR");
        String curr2 = sharedPreferences.getString("currency2", "INR");
        firstCurr = list.getCurrencyByCode(curr1);
        secondCurr = list.getCurrencyByCode(curr2);

        updateData();

        return rootView;
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

    public void onCurrencySelected(int x, Currency currency){
        if(x == 1){
            firstCurr = currency;
        }else {
            secondCurr = currency;
        }
        updateData();
    }

    private void assignId(View view, MaterialButton btn, int id) {
        btn = view.findViewById(id);
        btn.setOnClickListener(this);
    }

    private void updateData() {
        if (firstCurr != null && secondCurr != null && firstCountryText != null) {
            firstTextView.setText(firstCurr.getCode());
            firstSubTextView.setText(firstCurr.getName());
            firstCurrencyImage.setImageResource(getResources().getIdentifier(firstCurr.getCode().toLowerCase(), "drawable", requireContext().getPackageName()));

            secondTextView.setText(secondCurr.getCode());
            secondSubTextView.setText(secondCurr.getName());
            secondCurrencyImage.setImageResource(getResources().getIdentifier(secondCurr.getCode().toLowerCase(), "drawable", requireContext().getPackageName()));


            // Extract the text from the firstCountryText TextView
            String firstCountryTextValue = firstCountryText.getText().toString().trim();

            // Check if firstCountryTextValue is empty or not a valid numeric value
            if (firstCountryTextValue.isEmpty()) {
                secondCountryText.setText("0");
            } else {
                try {
                    double inputValue = Double.parseDouble(firstCountryTextValue);
                    double res = (1 / firstCurr.getRate()) * secondCurr.getRate() * inputValue;
                    secondCountryText.setText(new DecimalFormat("#.##").format(res));
                } catch (NumberFormatException e) {
                    // Handle the case where the input is not a valid number
                    secondCountryText.setText("Invalid Input");
                }
            }

            adjustTextSize(firstCountryText);
            adjustTextSize(secondCountryText);
        }
    }


    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = firstCountryText.getText().toString();
        String calcText = calculationText.getText().toString();

        if(buttonText.equals("VALUTA PREFERITA")){
            setDefault();
            return;
        }

        if(buttonText.equals("RUOTA VALUTA")){
            rotateCurr();
            return;
        }

        if(buttonText.equals("COPIA")){
            copyText();
            return;
        }

        if(buttonText.equals("INVIA")){
            sendMsg();
            return;
        }

        if(!dataToCalculate.equals("0")){
            if(calc(buttonText))
                return;
        }

        if(buttonText.equals("CLR")) {
            firstCountryText.setText("0");
            secondCountryText.setText("0");
            calculationText.setText("");
            firstCountryText.setTextSize(25);
            secondCountryText.setTextSize(25);
            updateData();
            return;
        }

        if(buttonText.equals("±")){
            if(firstCountryText.getText().charAt(0) == '-'){
                dataToCalculate = dataToCalculate.substring(1, dataToCalculate.length());
            }else{
                dataToCalculate = "-" + dataToCalculate;
            }
            firstCountryText.setText(dataToCalculate);
            updateData();
            return;
        }

        if(buttonText.equals("=")){
            dataToCalculate = getCalculationResult(calcText + dataToCalculate);
            calculationText.setText("");
            firstCountryText.setText(dataToCalculate);
            updateData();
            return;
        }

        if(buttonText.equals("DEL")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
            if(dataToCalculate.length() == 0){
                dataToCalculate = "0";
            }
            firstCountryText.setText(dataToCalculate);
            updateData();
            adjustTextSize(firstCountryText);
            adjustTextSize(secondCountryText);
            return;
        }

        if(dataToCalculate.equals("0") || isCalculated){
            isCalculated = false;
            dataToCalculate = buttonText;
        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        firstCountryText.setText(dataToCalculate);
        updateData();
    }

    private void adjustTextSize(TextView textView) {
        float textWidth = textView.getPaint().measureText(textView.getText().toString());

        if (textWidth < textView.getWidth() && textView.getTextSize() < textSize) {
            float newSize = textView.getTextSize() * 1.05f;
            textView.setTextSize(0, newSize);
        } else if (textWidth > textView.getWidth()) {
            float newSize = textView.getTextSize() * 0.8f;
            textView.setTextSize(0, newSize);
        }
    }


    private void setDefault(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyCurrency", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currency1", firstCurr.getCode());
        editor.putString("currency2", secondCurr.getCode());
        editor.apply();
        Toast.makeText(requireContext(), "Aggiunto come preferito", Toast.LENGTH_SHORT).show();
    }

    private void rotateCurr(){
        Currency temp = firstCurr;
        firstCurr = secondCurr;
        secondCurr = temp;
        updateData();
    }

    private void copyText(){
        ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(requireContext().CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", makeMsg());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(requireContext(), "Testo Coppiato", Toast.LENGTH_SHORT).show();
    }

    private void sendMsg(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, makeMsg());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Send rates using:"));
    }

    private String makeMsg(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", new Locale("it", "IT"));
        return dateFormat.format(new Date())+"\n"+firstCountryText.getText()+" "+firstCurr.getCode()+"\n"+secondCountryText.getText()+" "+secondCurr.getCode()+"\n";
    }

    private boolean calc(String op){
        String dataToCalculate = firstCountryText.getText().toString();
        String calcText = calculationText.getText().toString();
        if(op.equals("+") || op.equals("*") || op.equals("+") || op.equals("-") || op.equals("/")){
            if(calcText.length() == 0){
                calculationText.setText(dataToCalculate + op);
                isCalculated = true;
            }else{
                if(isCalculated) {
                    calcText = calcText.substring(0, calcText.length()-1) + op;
                    calculationText.setText(calcText);
                    return true;
                }
                calcText = "(" + calcText + dataToCalculate + ")";
                firstCountryText.setText(getCalculationResult(calcText));
                calcText = calcText + op;
                calculationText.setText(calcText);
                isCalculated = true;
            }
            return true;
        }
        return false;
    }
    String getCalculationResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String res =  context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            return decimalFormat.format(Double.parseDouble(res));
        } catch (Exception e) {
            return "error";
        }
    }
}