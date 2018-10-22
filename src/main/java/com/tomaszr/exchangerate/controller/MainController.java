package com.tomaszr.exchangerate.controller;

import com.tomaszr.exchangerate.model.CurrencyRates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/currencyrates")
    public String currencyRates(Model model) {
        List<CurrencyRates> currencyRatesList=new ArrayList<>();

        CurrencyRates currencyRates1 = new CurrencyRates();
        CurrencyRates currencyRates2 = new CurrencyRates();
        CurrencyRates currencyRates3 = new CurrencyRates();

        currencyRates1.readCurrency();
        currencyRates2.readCurrency();
        currencyRates3.readCurrency();

        currencyRatesList.add(currencyRates1);
        currencyRatesList.add(currencyRates2);
        currencyRatesList.add(currencyRates3);

//        System.out.println(currencyRates1.getEur());

        model.addAttribute("rates",currencyRatesList );

        return "currencyrates";
    }


}

