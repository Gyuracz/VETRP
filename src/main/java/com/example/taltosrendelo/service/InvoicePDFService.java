package com.example.taltosrendelo.service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.taltosrendelo.entity.Invoice;
import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.entity.Treatment;
import com.example.taltosrendelo.repository.InvoiceRepository;

@Service
public class InvoicePDFService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public byte[] service(Owner owner, Treatment treatment){
        List<Invoice> invoices = invoiceRepository.findAllByTreatmentId(treatment.getId());
        Context context = new Context();
        Map<String, Object> datas = new HashMap<>();
        datas.put("owner", owner);
        datas.put("invoices", invoices);
        Integer sumPrice = 0;
            for(Invoice it : invoices){
                if(it.getGrossPrice() != null){
                    sumPrice += it.getGrossPrice();
                }
            }
        datas.put("sumPrice", sumPrice);
        context.setVariables(datas);
        String htmlContent = templateEngine.process("/pdf-templates/invoiceTemplate.html", context);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
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