package com.tomaszr.exchangerate.controller;

import com.tomaszr.exchangerate.model.CurrencyRates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    final String toEmailAddress="";
    final String ccEmailAddress="";
    final String emailHost="smtp.gmail.com";
    final String emailUser="";
    final String userPassword="";

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

        model.addAttribute("rates", currencyRatesList);


        return "currencyrates";
    }

    @PostMapping("/contact")
//    @ResponseBody
    public String contact(@RequestParam(value = "name") String nameParam,
                          @RequestParam(value = "email") String emailParam,
                          @RequestParam(value = "phone") String phoneParam,
                          @RequestParam(value = "message") String messageParam) {
//        static Properties mailServerProperties;
//        static Session getMailSession;
//        static MimeMessage generateMailMessage;

//        System.out.println(nameParam);
//        System.out.println(emailParam);
//        System.out.println(phoneParam);
//        System.out.println(messageParam);

        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmailAddress));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            generateMailMessage.setSubject("Exchangerate site. Contact from: "+nameParam);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String emailBody = nameParam+" sent message from Exchangerate site.<br><br>"+
                "Phone number: "+phoneParam+"<br>"+
                "Email address: "+emailParam+"<br><br>"+
                messageParam+"<br><br> Regards, <br> Exchangerate Admin";
        try {
            generateMailMessage.setContent(emailBody, "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = null;
        try {
            transport = getMailSession.getTransport("smtp");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        try {
            transport.connect(emailHost, emailUser, userPassword);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "contact";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}

