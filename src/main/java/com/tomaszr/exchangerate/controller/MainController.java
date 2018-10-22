package com.tomaszr.exchangerate.controller;

import com.tomaszr.exchangerate.model.CurrencyRates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/currencyrates")
    public String currencyRates(Model model) throws IOException {
        List<CurrencyRates> currencyRatesList=new ArrayList<>();

        CurrencyRates currencyRatesAlior = new CurrencyRates();
        CurrencyRates currencyRatesCinkciarz = new CurrencyRates();
        CurrencyRates currencyRatesWalutomat = new CurrencyRates();

        currencyRatesAlior.readCurrencyAlior();
        currencyRatesCinkciarz.readCurrencyCinkciarz();
        currencyRatesWalutomat.readCurrencyWalutomat();

        currencyRatesList.add(currencyRatesAlior);
        currencyRatesList.add(currencyRatesCinkciarz);
        currencyRatesList.add(currencyRatesWalutomat);

//        System.out.println(currencyRates1.getEur());

        model.addAttribute("rates",currencyRatesList );

        return "currencyrates";
    }


}

