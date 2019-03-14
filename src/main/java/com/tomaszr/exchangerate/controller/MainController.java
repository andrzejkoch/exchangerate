package com.tomaszr.exchangerate.controller;

import com.tomaszr.exchangerate.model.CurrencyRates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<CurrencyRates> currencyRatesList = new ArrayList<>();

        CurrencyRates currencyRatesAlior = new CurrencyRates();
        CurrencyRates currencyRatesCinkciarz = new CurrencyRates();
        CurrencyRates currencyRatesWalutomat = new CurrencyRates();
        CurrencyRates currencyRatesInternetowyKantor = new CurrencyRates();

        currencyRatesAlior.readCurrencyAlior();
        currencyRatesCinkciarz.readCurrencyCinkciarz();
        currencyRatesWalutomat.readCurrencyWalutomat();
        currencyRatesInternetowyKantor.readCurrencyInternetowyKantor();

        currencyRatesList.add(currencyRatesAlior);
        currencyRatesList.add(currencyRatesCinkciarz);
        currencyRatesList.add(currencyRatesWalutomat);
        currencyRatesList.add(currencyRatesInternetowyKantor);

//        System.out.println(currencyRates1.getEur());

        model.addAttribute("rates", currencyRatesList);


        return "currencyrates";
    }

    @PostMapping("/contact")
//    @ResponseBody
    public String contact(@RequestParam(value = "name") String nameParam,
                          @RequestParam(value = "email") String emailParam,
                          @RequestParam(value = "phone") String phoneParam,
                          @RequestParam(value = "message") String messageParam) {
//        Post post = new Post(titleParam, content);
//        PostComment postComment = new PostComment();
//        postComment.setComment(titleParam);
//
//        post.addComment(postComment);
//        postRepository.save(post);

        System.out.println(nameParam);
        System.out.println(emailParam);
        System.out.println(phoneParam);
        System.out.println(messageParam);
//        return "index";
        return "contact";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}

