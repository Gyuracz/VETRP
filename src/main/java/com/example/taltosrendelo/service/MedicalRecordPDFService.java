package com.example.taltosrendelo.service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.entity.Treatment;

@Service
public class MedicalRecordPDFService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] service(Animal animal, Owner owner, Treatment treatment){
        Context context = new Context();
        Map<String, Object> datas = new HashMap<>();
        datas.put("animal", animal);
        datas.put("owner", owner);
        datas.put("treatment", treatment);
        context.setVariables(datas);
        String htmlContent = templateEngine.process("/pdf-templates/medicalRecordTemplate.html", context);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            // renderer.getFontResolver().addFont("./src/main/resources/static/fonts/Calibri.ttf", "UTF-8", BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
	
}