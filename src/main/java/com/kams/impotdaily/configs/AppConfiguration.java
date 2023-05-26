/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.configs;

import com.kams.impotdaily.models.Taxe;
import com.kams.impotdaily.models.User;
import com.kams.impotdaily.repositories.ParamRepository;
import com.kams.impotdaily.repositories.TaxeRepository;
import com.kams.impotdaily.repositories.UserRepository;
import com.kams.impotdaily.services.ParamService;
import com.kams.impotdaily.services.SendMailService;
import com.kams.impotdaily.utils.AppUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author macbook
 */
/*@Configuration
@EnableScheduling*/
//@Component
@Configuration
@EnableScheduling
public class AppConfiguration {

    @Autowired
    private TaxeRepository taxeRepository;
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private ParamRepository paramRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private ParamService paramService;
    
//    @Scheduled(fixedDelay = 300000)
//    @Scheduled(fixedDelayString = "${getFixedDelay}")
//    @Scheduled(fixedDelay = 86400000)
    /* Retour
    @Scheduled(cron = "0 7 * * *")
    public void sendDailyMail1() {
        System.out.println("Send Mail");
        List<Taxe> listTaxeValid = taxeRepository.findByValidTrue();
        if (listTaxeValid.isEmpty()) {
            System.out.println("Liste vide");
        } else {
            for (Taxe taxe : listTaxeValid) {
                if (taxe.getDatePaiement().before(new Date())) {
                    taxe.setValid(false);
                    taxeRepository.save(taxe);
                } else {
                    String body = "La date de paiement de la taxe (" + taxe.getImpot().getLibelle() + ") est fixée au " + AppUtils.formatDate(taxe.getDatePaiement());
                    sendMailService.sendSimpleMail(taxe.getUser().getEmail(), body, "Rappel Taxe");
                }
            }
        }
    }*/
    
    

//    @Scheduled(fixedDelay = 86400000)
//    public void sendDailyMail() {
//        System.out.println("Send Mail");
//        List<Taxe> listTaxeValid = taxeRepository.findByValidTrue();
//        if (listTaxeValid.isEmpty()) {
//            System.out.println("Liste vide");
//        } else {
//            for (Taxe taxe : listTaxeValid) {
//                if (taxe.getImpot().getDatePaiement().before(new Date())) {
//                    String body = "La date de paiement de la taxe (" + taxe.getImpot().getLibelle() + ") était fixée au " + AppUtils.formatDate(taxe.getDatePaiement());
//                    sendMailService.sendSimpleMail(taxe.getUser().getEmail(), body, "Rappel Taxe");
//                } else {
//                    String body = "La date de paiement de la taxe (" + taxe.getImpot().getLibelle() + ") est fixée au " + AppUtils.formatDate(taxe.getDatePaiement());
//                    sendMailService.sendSimpleMail(taxe.getUser().getEmail(), body, "Rappel Taxe");
//                }
//            }
//        }
//    }
    
    @Scheduled(fixedDelay = 86400000)
//    @Scheduled(fixedDelay = 600000)
    public void sendDailyMails() {
        List<User> listUser= userRepository.findAll();
        for (User user : listUser) {
            String body= createResponseHtml(user);
            if (body != null) {
                sendMailService.sendSimpleMailHtml(user.getEmail(), body, "Rappel Taxe");
            }
        }
    }
    
    //@Bean
    public long getFixedDelay(){
        long cron= paramRepository.getOne("param").getInterval();
        return cron;
    }
    
    private String createResponseHtml(User user){
        String htmlContent= null;
        if (!user.getRole().equalsIgnoreCase("admin")) {
           List<Taxe> listTaxe= taxeRepository.findAllByCompany_idAndValidTrue(user.getCompany().getId());
            if (!listTaxe.isEmpty()) {
               StringBuilder stringBuilder= generateCommonHtmlHead();
                for (Taxe taxe : listTaxe) {
                    stringBuilder.append("<tr>");
                    stringBuilder.append("<td>").append(taxe.getImpot().getLibelle()).append("</td>");
                    stringBuilder.append("<td>").append(AppUtils.formatDate(taxe.getDatePaiement())).append("</td>");
                    stringBuilder.append("<td>").append(taxe.getMoney() == 1 ? taxe.getMontant() + " CDF" : taxe.getMontant() + " USD").append("</td>");
                    stringBuilder.append("<td>").append(taxe.getTypePenality() == 1 ?
                        (taxe.getMoney() == 1 ? taxe.getPenalities() + " CDF" : taxe.getPenalities() + " USD") :
                            taxe.getPenalities() + " %").append("</td>");
                    stringBuilder.append("</tr>");
                }
                generateCommonFooter(stringBuilder);
                htmlContent= stringBuilder.toString();
            }
            else{
                htmlContent= null;
            }
        }
        return htmlContent;
    }
    
    
    private StringBuilder generateCommonHtmlHead() {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append("<head>")
                .append("<h1>Rappel taxe</h1>")
                .append("</head>")
                .append("<body>")
                .append("<table border=1>")
                .append("<tr>")
                .append("<th>Taxe </th><th>Date Paiement</th><th>Montant</th><th>Penalites</th>")
                .append("</tr>");
    }

    private void generateCommonFooter(StringBuilder stringBuilder) {
        stringBuilder.append("</table></body>");
    }
    
//    @Scheduled(fixedDelay = 120000)
//    public void simpleTask(){
//        System.out.println("Il est "+new Date());
//    }

}
