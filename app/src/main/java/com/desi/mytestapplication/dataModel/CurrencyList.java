package com.desi.mytestapplication.dataModel;

import android.content.Context;
import android.util.Log;

import com.desi.mytestapplication.network.VolleyRequestManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CurrencyList {
    private HashMap<String, Currency> currencyMap;
    private Date date;
    public interface CurrencyListUpdateListener {
        void onUpdateComplete();
    }
    public CurrencyList() {
        currencyMap = new HashMap<>();
        this.addCurrency(new Currency("United Arab Emirates Dirham", "AED", 3.965));
        this.addCurrency(new Currency("Afghan Afghani", "AFN", 79.576));
        this.addCurrency(new Currency("Albanian Lek", "ALL", 108.331));
        this.addCurrency(new Currency("Armenian Dram", "AMD", 418.326));
        this.addCurrency(new Currency("Netherlands Antillean Guilder", "ANG", 1.954));
        this.addCurrency(new Currency("Angolan Kwanza", "AOA", 890.637));
        this.addCurrency(new Currency("Argentine Peso", "ARS", 377.051));
        this.addCurrency(new Currency("Australian Dollar", "AUD", 1.671));
        this.addCurrency(new Currency("Aruban Florin", "AWG", 1.943));
        this.addCurrency(new Currency("Azerbaijani Manat", "AZN", 1.840));
        this.addCurrency(new Currency("Bosnia-Herzegovina Convertible Mark", "BAM", 1.955));
        this.addCurrency(new Currency("Barbadian Dollar", "BBD", 2.190));
        this.addCurrency(new Currency("Bangladeshi Taka", "BDT", 118.479));
        this.addCurrency(new Currency("Bulgarian Lev", "BGN", 1.955));
        this.addCurrency(new Currency("Bahraini Dinar", "BHD", 0.409));
        this.addCurrency(new Currency("Burundian Franc", "BIF", 3069.557));
        this.addCurrency(new Currency("Bermudian Dollar", "BMD", 1.080));
        this.addCurrency(new Currency("Brunei Dollar", "BND", 1.464));
        this.addCurrency(new Currency("Bolivian Boliviano", "BOB", 7.494));
        this.addCurrency(new Currency("Brazilian Real", "BRL", 5.374));
        this.addCurrency(new Currency("Bahamian Dollar", "BSD", 1.084));
        this.addCurrency(new Currency("Bitcoin", "BTC", 0.0000416));
        this.addCurrency(new Currency("Bhutanese Ngultrum", "BTN", 89.735));
        this.addCurrency(new Currency("Botswanan Pula", "BWP", 14.645));
        this.addCurrency(new Currency("Belarusian Ruble", "BYN", 2.737));
        this.addCurrency(new Currency("Belarusian Ruble (old)", "BYR", 21159.358));
        this.addCurrency(new Currency("Belize Dollar", "BZD", 2.186));
        this.addCurrency(new Currency("Canadian Dollar", "CAD", 1.465));
        this.addCurrency(new Currency("Congolese Franc", "CDF", 2682.705));
        this.addCurrency(new Currency("Swiss Franc", "CHF", 0.956));
        this.addCurrency(new Currency("Chilean Unit of Account (UF)", "CLF", 0.0333));
        this.addCurrency(new Currency("Chilean Peso", "CLP", 924.227));
        this.addCurrency(new Currency("Chinese Yuan", "CNY", 7.839));
        this.addCurrency(new Currency("Colombian Peso", "COP", 4433.816));
        this.addCurrency(new Currency("Costa Rican Colón", "CRC", 584.697));
        this.addCurrency(new Currency("Cuban Convertible Peso", "CUC", 1.080));
        this.addCurrency(new Currency("Cuban Peso", "CUP", 28.608));
        this.addCurrency(new Currency("Cape Verdean Escudo", "CVE", 110.245));
        this.addCurrency(new Currency("Czech Republic Koruna", "CZK", 24.131));
        this.addCurrency(new Currency("Djiboutian Franc", "DJF", 193.086));
        this.addCurrency(new Currency("Danish Krone", "DKK", 7.467));
        this.addCurrency(new Currency("Dominican Peso", "DOP", 61.559));
        this.addCurrency(new Currency("Algerian Dinar", "DZD", 147.696));
        this.addCurrency(new Currency("Egyptian Pound", "EGP", 33.296));
        this.addCurrency(new Currency("Eritrean Nakfa", "ERN", 16.193));
        this.addCurrency(new Currency("Ethiopian Birr", "ETB", 59.923));
        this.addCurrency(new Currency("Euro", "EUR", 1.000));
        this.addCurrency(new Currency("Fijian Dollar", "FJD", 2.440));
        this.addCurrency(new Currency("Falkland Islands Pound", "FKP", 0.857));
        this.addCurrency(new Currency("British Pound Sterling", "GBP", 0.858));
        this.addCurrency(new Currency("Georgian Lari", "GEL", 2.845));
        this.addCurrency(new Currency("Guernsey Pound", "GGP", 0.857));
        this.addCurrency(new Currency("Ghanaian Cedi", "GHS", 12.396));
        this.addCurrency(new Currency("Gibraltar Pound", "GIP", 0.857));
        this.addCurrency(new Currency("Gambian Dalasi", "GMD", 65.692));
        this.addCurrency(new Currency("Guinean Franc", "GNF", 9312.353));
        this.addCurrency(new Currency("Guatemalan Quetzal", "GTQ", 8.534));
        this.addCurrency(new Currency("Guyanaese Dollar", "GYD", 226.890));
        this.addCurrency(new Currency("Hong Kong Dollar", "HKD", 8.468));
        this.addCurrency(new Currency("Honduran Lempira", "HNL", 26.704));
        this.addCurrency(new Currency("Croatian Kuna", "HRK", 7.430));
        this.addCurrency(new Currency("Haitian Gourde", "HTG", 147.054));
        this.addCurrency(new Currency("Hungarian Forint", "HUF", 384.906));
        this.addCurrency(new Currency("Indonesian Rupiah", "IDR", 16436.503));
        this.addCurrency(new Currency("Israeli New Sheqel", "ILS", 4.098));
        this.addCurrency(new Currency("Isle of Man Pound", "IMP", 0.857));
        this.addCurrency(new Currency("Indian Rupee", "INR", 1));
        this.addCurrency(new Currency("Iraqi Dinar", "IQD", 1420.249));
        this.addCurrency(new Currency("Iranian Rial", "IRR", 45638.360));
        this.addCurrency(new Currency("Icelandic Króna", "ISK", 143.182));
        this.addCurrency(new Currency("Jersey Pound", "JEP", 0.857));
        this.addCurrency(new Currency("Jamaican Dollar", "JMD", 167.310));
        this.addCurrency(new Currency("Jordanian Dinar", "JOD", 0.764));
        this.addCurrency(new Currency("Japanese Yen", "JPY", 157.836));
        this.addCurrency(new Currency("Kenyan Shilling", "KES", 156.942));
        this.addCurrency(new Currency("Kyrgystani Som", "KGS", 95.280));
        this.addCurrency(new Currency("Cambodian Riel", "KHR", 4510.202));
        this.addCurrency(new Currency("Comorian Franc", "KMF", 491.200));
        this.addCurrency(new Currency("North Korean Won", "KPW", 971.571));
        this.addCurrency(new Currency("South Korean Won", "KRW", 1423.345));
        this.addCurrency(new Currency("Kuwaiti Dinar", "KWD", 0.333));
        this.addCurrency(new Currency("Cayman Islands Dollar", "KYD", 0.904));
        this.addCurrency(new Currency("Kazakhstani Tenge", "KZT", 496.222));
        this.addCurrency(new Currency("Laotian Kip", "LAK", 21346.225));
        this.addCurrency(new Currency("Lebanese Pound", "LBP", 16299.417));
        this.addCurrency(new Currency("Sri Lankan Rupee", "LKR", 347.029));
        this.addCurrency(new Currency("Liberian Dollar", "LRD", 200.798));
        this.addCurrency(new Currency("Lesotho Loti", "LSL", 19.983));
        this.addCurrency(new Currency("Lithuanian Litas", "LTL", 3.188));
        this.addCurrency(new Currency("Latvian Lats", "LVL", 0.653));
        this.addCurrency(new Currency("Libyan Dinar", "LYD", 5.223));
        this.addCurrency(new Currency("Moroccan Dirham", "MAD", 11.084));
        this.addCurrency(new Currency("Moldovan Leu", "MDL", 19.336));
        this.addCurrency(new Currency("Malagasy Ariary", "MGA", 4895.134));
        this.addCurrency(new Currency("Macedonian Denar", "MKD", 61.509));
        this.addCurrency(new Currency("Myanmar Kyat", "MMK", 2277.397));
        this.addCurrency(new Currency("Mongolian Tugrik", "MNT", 3749.363));
        this.addCurrency(new Currency("Macanese Pataca", "MOP", 8.763));
        this.addCurrency(new Currency("Mauritanian Ouguiya (pre-2018)", "MRO", 385.402));
        this.addCurrency(new Currency("Mauritanian Ouguiya", "MRU", 49.462));
        this.addCurrency(new Currency("Maldivian Rufiyaa", "MVR", 16.629));
        this.addCurrency(new Currency("Malawian Kwacha", "MWK", 1159.295));
        this.addCurrency(new Currency("Mexican Peso", "MXN", 18.452));
        this.addCurrency(new Currency("Malaysian Ringgit", "MYR", 5.017));
        this.addCurrency(new Currency("Mozambican Metical", "MZN", 68.282));
        this.addCurrency(new Currency("Namibian Dollar", "NAD", 19.983));
        this.addCurrency(new Currency("Nigerian Naira", "NGN", 817.248));
        this.addCurrency(new Currency("Nicaraguan Córdoba", "NIO", 39.683));
        this.addCurrency(new Currency("Norwegian Krone", "NOK", 11.509));
        this.addCurrency(new Currency("Nepalese Rupee", "NPR", 143.576));
        this.addCurrency(new Currency("New Zealand Dollar", "NZD", 1.813));
        this.addCurrency(new Currency("Omani Rial", "OMR", 0.416));
        this.addCurrency(new Currency("Panamanian Balboa", "PAB", 1.085));
        this.addCurrency(new Currency("Peruvian Nuevo Sol", "PEN", 4.005));
        this.addCurrency(new Currency("Papua New Guinean Kina", "PGK", 3.969));
        this.addCurrency(new Currency("Philippine Peso", "PHP", 61.311));
        this.addCurrency(new Currency("Pakistani Rupee", "PKR", 332.100));
        this.addCurrency(new Currency("Polish Złoty", "PLN", 4.476));
        this.addCurrency(new Currency("Paraguayan Guarani", "PYG", 7892.604));
        this.addCurrency(new Currency("Qatari Rial", "QAR", 3.931));
        this.addCurrency(new Currency("Romanian Leu", "RON", 4.954));
        this.addCurrency(new Currency("Serbian Dinar", "RSD", 117.269));
        this.addCurrency(new Currency("Russian Ruble", "RUB", 104.075));
        this.addCurrency(new Currency("Rwandan Franc", "RWF", 1290.472));
        this.addCurrency(new Currency("Saudi Riyal", "SAR", 4.049));
        this.addCurrency(new Currency("Solomon Islands Dollar", "SBD", 9.035));
        this.addCurrency(new Currency("Seychellois Rupee", "SCR", 14.324));
        this.addCurrency(new Currency("Sudanese Pound", "SDG", 648.467));
        this.addCurrency(new Currency("Swedish Krona", "SEK", 11.916));
        this.addCurrency(new Currency("Singapore Dollar", "SGD", 1.461));
        this.addCurrency(new Currency("Saint Helena Pound", "SHP", 1.314));
        this.addCurrency(new Currency("Sierra Leonean Leone", "SLL", 21321.292));
        this.addCurrency(new Currency("Somali Shilling", "SOS", 613.733));
        this.addCurrency(new Currency("South Sudanese Pound", "SSP", 649.359));
        this.addCurrency(new Currency("Surinamese Dollar", "SRD", 41.240));
        this.addCurrency(new Currency("São Tomé and Príncipe Dobra (pre-2018)", "STD", 22344.693));
        this.addCurrency(new Currency("São Tomé and Príncipe Dobra", "STN", 49.462));
        this.addCurrency(new Currency("Syrian Pound", "SYP", 14035.553));
        this.addCurrency(new Currency("Swazi Lilangeni", "SZL", 20.298));
        this.addCurrency(new Currency("Thai Baht", "THB", 38.170));
        this.addCurrency(new Currency("Tajikistani Somoni", "TJS", 11.913));
        this.addCurrency(new Currency("Turkmenistani Manat", "TMT", 3.789));
        this.addCurrency(new Currency("Tunisian Dinar", "TND", 3.339));
        this.addCurrency(new Currency("Tongan Pa'anga", "TOP", 2.574));
        this.addCurrency(new Currency("Turkish Lira", "TRY", 28.825));
        this.addCurrency(new Currency("Trinidad and Tobago Dollar", "TTD", 7.369));
        this.addCurrency(new Currency("Taiwanese Dollar", "TWD", 34.414));
        this.addCurrency(new Currency("Tanzanian Shilling", "TZS", 2716.520));
        this.addCurrency(new Currency("Ukrainian Hryvnia", "UAH", 40.052));
        this.addCurrency(new Currency("Ugandan Shilling", "UGX", 4033.287));
        this.addCurrency(new Currency("United States Dollar", "USD", 1.080));
        this.addCurrency(new Currency("Uruguayan Peso", "UYU", 40.883));
        this.addCurrency(new Currency("Uzbekistan Som", "UZS", 13123.679));
        this.addCurrency(new Currency("Venezuelan Bolívar (pre-2018)", "VEF", 3514036.162));
        this.addCurrency(new Currency("Venezuelan Bolívar", "VES", 34.695));
        this.addCurrency(new Currency("Vietnamese Dong", "VND", 26001.180));
        this.addCurrency(new Currency("Vanuatu Vatu", "VUV", 130.559));
        this.addCurrency(new Currency("Samoan Tala", "WST", 2.992));
        this.addCurrency(new Currency("Central African CFA Franc BEAC", "XAF", 655.841));
        this.addCurrency(new Currency("Silver Ounce", "XAG", 0.045));
        this.addCurrency(new Currency("Gold Ounce", "XAU", 0.001));
        this.addCurrency(new Currency("Eastern Caribbean Dollar", "XCD", 2.918));
        this.addCurrency(new Currency("International Monetary Fund (IMF) Special Drawing Rights", "XDR", 0.817));
        this.addCurrency(new Currency("Central African CFA Franc BCEAO", "XOF", 655.841));
        this.addCurrency(new Currency("CFP Franc", "XPF", 119.529));
        this.addCurrency(new Currency("Yemeni Rial", "YER", 270.268));
        this.addCurrency(new Currency("South African Rand", "ZAR", 20.344));
        this.addCurrency(new Currency("Zambian Kwacha (pre-2013)", "ZMK", 9717.331));
        this.addCurrency(new Currency("Zambian Kwacha", "ZMW", 21.933));
        this.addCurrency(new Currency("Zimbabwean Dollar (pre-2009)", "ZWL", 347.618));
    }
    public void addCurrency(Currency currency) {
        currencyMap.put(currency.getCode(), currency);
    }
    public void updateRates(Context context, CurrencyListUpdateListener listener) {
        VolleyRequestManager volleyRequestManager = VolleyRequestManager.getInstance(context);
        volleyRequestManager.makeGetRequest("https://api.exchangerate.host/latest", new VolleyRequestManager.VolleyResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parse the JSON response to get exchange rates
                    JSONObject ratesObject = response.getJSONObject("rates");

                    // Loop through your CurrencyList and update the exchange rates
                    for (Currency currency : currencyMap.values()) {
                        String code = currency.getCode();
                        if (ratesObject.has(code)) {
                            double rate = ratesObject.getDouble(code);
                            currency.setExchangeRate(rate);
                        }
                    }
                    date = new Date();
                    listener.onUpdateComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception error) {
                error.printStackTrace();
            }
        });
    }

    public void updateRates(Context context, String date, CurrencyListUpdateListener listener) {
        VolleyRequestManager volleyRequestManager = VolleyRequestManager.getInstance(context);
        volleyRequestManager.makeGetRequest("https://api.exchangerate.host/"+date, new VolleyRequestManager.VolleyResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parse the JSON response to get exchange rates
                    JSONObject ratesObject = response.getJSONObject("rates");

                    // Loop through your CurrencyList and update the exchange rates
                    for (Currency currency : currencyMap.values()) {
                        String code = currency.getCode();
                        if (ratesObject.has(code)) {
                            double rate = ratesObject.getDouble(code);
                            currency.setExchangeRate(rate);
                        }
                    }
                    listener.onUpdateComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception error) {
                error.printStackTrace();
            }
        });
    }
    public Currency getCurrencyByCode(String code) {
        return currencyMap.get(code);
    }
    public ArrayList<Currency> getAllCurrencies() {
        ArrayList<Currency> currencyList =  new ArrayList<>(currencyMap.values());
        currencyList.sort((curr1, curr2)-> curr1.getCode().compareTo(curr2.getCode()));
        return currencyList;
    }
    public Date getDate() {
        return date;
    }
}
