package com.example.taltosrendelo.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.entity.Treatment;

@Service
@EnableAsync
public class MedicalRecordEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void service(Owner owner, Animal animal, Treatment treatment){
        try{
            String to = owner.getEmail();
            if(to != null && to.length() > 2){
                Context context = new Context();
                Map<String, Object> datas = new HashMap<>();
                datas.put("animal", animal);
                datas.put("owner", owner);
                datas.put("treatment", treatment);
                context.setVariables(datas);
                String htmlContent = templateEngine.process("/email-templates/medicalRecordTemplate.html", context);
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(from, "Táltos Állatorvosi Rendelő | VETRP System");
                helper.setTo(to);
                helper.setSubject("E-Kórlap");
                helper.setText(htmlContent, true);
                mailSender.send(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
}