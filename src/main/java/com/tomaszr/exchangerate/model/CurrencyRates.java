package com.tomaszr.exchangerate.model;

import lombok.Getter;

public class CurrencyRates {
    @Getter
    private double euroSell;
    @Getter
    private double euroBuy;
    @Getter
    private double dollarSell;
    @Getter
    private double dollarBuy;
    @Getter
    private double chfSell;
    @Getter
    private double chfBuy;

    public boolean readCurrencyAlior() {

        euro=1.1;
        dollar=1.2;
        chf=1.3;
        return true;
    }

    public boolean readCurrencyCinkciarz() {

        euro=1.1;
        dollar=1.2;
        chf=1.3;
        return true;
    }
    public boolean readCurrencyWalutomat() {

        euro=1.1;
        dollar=1.2;
        chf=1.3;
        return true;
    }
}
