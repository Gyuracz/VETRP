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
                            String content = "Kedves " + owner.getName() + "! <br><br>??rtes??tj??k, hogy "
                                + animal.getName() + " nev?? kuty??j??nak 1 h??nap m??lva ("
                                + now.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))
                                + ") lej??r a k??telez?? veszetts??g elleni olt??sa. <br><br>??dv??zlettel: <br>T??ltos ??llatorvosi Rendel?? <br><br><small>Ez egy automatikusan gener??lt ??zenet, k??rem ne v??laszoljon r??!</small>";
                            helper.setFrom(from, "T??ltos ??llatorvosi Rendel?? | VETRP System");
                            helper.setTo(owner.getEmail());
                            helper.setSubject("Hamarosan lej??r?? veszetts??g elleni v??d??olt??s");
                            helper.setText(content, true);
                            mailSender.send(message);
                        }
                        if(animal.getSpecies().equalsIgnoreCase("Kutya") && lastVaccine.plusMonths(animal.getMonthToNextVaccination()).compareTo(now) == 0){
                            MimeMessage message = mailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                            String content = "Kedves " + owner.getName() + "! <br><br>??rtes??tj??k, hogy "
                                + animal.getName() + " nev?? kuty??j??nak a mai napon ("
                                + now.format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))
                                + ") lej??rt a k??telez?? veszetts??g elleni olt??sa. <br><br>??dv??zlettel: <br>T??ltos ??llatorvosi Rendel?? <br><br><small>Ez egy automatikusan gener??lt ??zenet, k??rem ne v??laszoljon r??!</small>";
                            helper.setFrom(from, "T??ltos ??llatorvosi Rendel?? | VETRP System");
                            helper.setTo(owner.getEmail());
                            helper.setSubject("Lej??rt veszetts??g elleni v??d??olt??s");
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