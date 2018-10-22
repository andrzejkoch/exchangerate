package com.tomaszr.exchangerate.model;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrencyRates {
    @Getter
    @Setter
    private String cantorName;
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

    private double roundValue(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public boolean readCurrencyCinkciarz() throws IOException {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://cinkciarz.pl/kantor/kursy-walut-cinkciarz-pl").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cantorName = "Cinkciarz";
        String htmlDoc = doc.body().text();

        int currencyIndex = htmlDoc.indexOf("1 EUR");
        String test = htmlDoc.substring(currencyIndex + 4, currencyIndex + 10);
        euroBuy = Double.parseDouble((htmlDoc.substring(currencyIndex + 6, currencyIndex + 12)).replace(',', '.'));
        euroSell = Double.parseDouble((htmlDoc.substring(currencyIndex + 13, currencyIndex + 19)).replace(',', '.'));

        currencyIndex = htmlDoc.indexOf("1 USD");
        dollarBuy = Double.parseDouble((htmlDoc.substring(currencyIndex + 6, currencyIndex + 12)).replace(',', '.'));
        dollarSell = Double.parseDouble((htmlDoc.substring(currencyIndex + 13, currencyIndex + 19)).replace(',', '.'));

        currencyIndex = htmlDoc.indexOf("1 CHF");
        chfBuy = Double.parseDouble((htmlDoc.substring(currencyIndex + 6, currencyIndex + 12)).replace(',', '.'));
        chfSell = Double.parseDouble((htmlDoc.substring(currencyIndex + 13, currencyIndex + 19)).replace(',', '.'));

        return true;
    }

    public boolean readCurrencyAlior() {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://kantor.aliorbank.pl/forex").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cantorName = "Alior";
        String htmlDoc = doc.body().text();

        int currencyIndex = htmlDoc.indexOf("EUR");
        String test = htmlDoc.substring(currencyIndex + 4, currencyIndex + 10);
        euroBuy = Double.parseDouble(htmlDoc.substring(currencyIndex + 4, currencyIndex + 10));
        euroSell = Double.parseDouble(htmlDoc.substring(currencyIndex + 20, currencyIndex + 26));

        currencyIndex = htmlDoc.indexOf("USD");
        dollarBuy = Double.parseDouble(htmlDoc.substring(currencyIndex + 4, currencyIndex + 10));
        dollarSell = Double.parseDouble(htmlDoc.substring(currencyIndex + 20, currencyIndex + 26));

        currencyIndex = htmlDoc.indexOf("CHF");
        chfBuy = Double.parseDouble(htmlDoc.substring(currencyIndex + 4, currencyIndex + 10));
        chfSell = Double.parseDouble(htmlDoc.substring(currencyIndex + 20, currencyIndex + 26));
        return true;
    }


    public boolean readCurrencyWalutomat() {

        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.walutomat.pl/kursy-walut/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cantorName = "Walutomat";
        String htmlDoc = doc.body().text();

        int currencyIndex = htmlDoc.indexOf("EUR / PLN");
        euroSell = 1.002 * Double.parseDouble((htmlDoc.substring(currencyIndex + 10, currencyIndex + 16)).replace(',', '.'));
        euroSell = roundValue(euroSell, 4);
        euroBuy = 0.998 * Double.parseDouble((htmlDoc.substring(currencyIndex + 21, currencyIndex + 27)).replace(',', '.'));
        euroBuy=roundValue(euroBuy,4);

        currencyIndex = htmlDoc.indexOf("USD / PLN");
        dollarSell = 1.002 * Double.parseDouble((htmlDoc.substring(currencyIndex + 10, currencyIndex + 16)).replace(',', '.'));
        dollarSell=roundValue(dollarSell,4);
        dollarBuy = 0.998 * Double.parseDouble((htmlDoc.substring(currencyIndex + 21, currencyIndex + 27)).replace(',', '.'));
        dollarBuy=roundValue(dollarBuy,4);

        currencyIndex = htmlDoc.indexOf("CHF / PLN");
        chfSell = 1.002 * Double.parseDouble((htmlDoc.substring(currencyIndex + 10, currencyIndex + 16)).replace(',', '.'));
        chfSell=roundValue(chfSell,4);
        chfBuy = 0.998 * Double.parseDouble((htmlDoc.substring(currencyIndex + 21, currencyIndex + 27)).replace(',', '.'));
        chfBuy=roundValue(chfBuy,4);
        return true;
    }
}
