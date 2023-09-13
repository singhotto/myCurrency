package com.desi.mytestapplication.dataModel;
public class Currency {
    private String name;
    private String code;
    private double rate;

    public Currency(String name, String code, double rate) {
        this.name = name;
        this.code = code;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setExchangeRate(double rate){
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
