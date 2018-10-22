package com.tomaszr.exchangerate.model;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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

    public boolean readCurrencyAlior () throws IOException {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://kantor.aliorbank.pl/forex").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String htmlDoc=doc.body().text();

        int currencyIndex=htmlDoc.indexOf("EUR");
        String test=htmlDoc.substring(currencyIndex+4,currencyIndex+10);
        euroBuy=Double.parseDouble(htmlDoc.substring(currencyIndex+4,currencyIndex+10));
        euroSell=Double.parseDouble(htmlDoc.substring(currencyIndex+20,currencyIndex+26));

        currencyIndex=htmlDoc.indexOf("USD");
        dollarBuy=Double.parseDouble(htmlDoc.substring(currencyIndex+4,currencyIndex+10));
        dollarSell=Double.parseDouble(htmlDoc.substring(currencyIndex+20,currencyIndex+26));

        currencyIndex=htmlDoc.indexOf("CHF");
        chfBuy=Double.parseDouble(htmlDoc.substring(currencyIndex+4,currencyIndex+10));
        chfSell=Double.parseDouble(htmlDoc.substring(currencyIndex+20,currencyIndex+26));

        return true;
    }

    public boolean readCurrencyCinkciarz() {

        euroBuy=1.1;
        dollarBuy=1.2;
        chfBuy=1.3;
        return true;
    }
    public boolean readCurrencyWalutomat() {

        euroBuy=1.1;
        dollarBuy=1.2;
        chfBuy=1.3;
        return true;
    }
}
