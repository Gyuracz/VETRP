package com.example.taltosrendelo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.repository.AnimalRepository;
import com.example.taltosrendelo.repository.OwnerRepository;

@Service
@EnableAsync
public class VaccineEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    // @Scheduled(fixedRate = 60000)
    @Scheduled(cron = "0 0 3 * * ?")
    public void service(){
        try{
            for(Owner owner : ownerRepository.findAllByOrderByIdDesc()){
                if(owner.getEmail() != null && owner.getEmail().length() > 2){
                    for(Animal animal : animalRepository.findAllByOwnerId(owner.getId())){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate lastVaccine = LocalDate.parse(animal.getLastVaccinationAgainstRabies(), formatter);
                        LocalDate now = LocalDate.parse(LocalDate.now().format(formatter));
                        if(animal.getSpecies().equalsIgnoreCase("Kutya") && lastVaccine.plusMonths(animal.getMonthToNextVaccination() - 1).compareTo(now) == 0){
                            MimeMessage message = mailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                            String content = "Kedves " + owner.getName() + "! <br><br>Értesítjük, hogy "
                                + animal.getName() + " nevű kutyájának 1 hónap múlva ("
                                + now.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))
                                + ") lejár a kötelező veszettség elleni oltása. <br><br>Üdvözlettel: <br>Táltos Állatorvosi Rendelő <br><br><small>Ez egy automatikusan generált üzenet, kérem ne válaszoljon rá!</small>";
                            helper.setFrom(from, "Táltos Állatorvosi Rendelő | VETRP System");
                            helper.setTo(owner.getEmail());
                            helper.setSubject("Hamarosan lejáró veszettség elleni védőoltás");
                            helper.setText(content, true);
                            mailSender.send(message);
                        }
                        if(animal.getSpecies().equalsIgnoreCase("Kutya") && lastVaccine.plusMonths(animal.getMonthToNextVaccination()).compareTo(now) == 0){
                            MimeMessage message = mailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                            String content = "Kedves " + owner.getName() + "! <br><br>Értesítjük, hogy "
                                + animal.getName() + " nevű kutyájának a mai napon ("
                                + now.format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))
                                + ") lejárt a kötelező veszettség elleni oltása. <br><br>Üdvözlettel: <br>Táltos Állatorvosi Rendelő <br><br><small>Ez egy automatikusan generált üzenet, kérem ne válaszoljon rá!</small>";
                            helper.setFrom(from, "Táltos Állatorvosi Rendelő | VETRP System");
                            helper.setTo(owner.getEmail());
                            helper.setSubject("Lejárt veszettség elleni védőoltás");
                            helper.setText(content, true);
                            mailSender.send(message);
                        }
                        Thread.sleep(3000);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
}