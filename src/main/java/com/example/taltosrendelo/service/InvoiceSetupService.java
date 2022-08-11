package com.example.taltosrendelo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Invoice;
import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.entity.Treatment;
import com.example.taltosrendelo.repository.InvoiceRepository;

@Service
public class InvoiceSetupService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> service(Treatment treatment){
        List<Invoice> existingInvoices = invoiceRepository.findAllByTreatmentId(treatment.getId());
        List<Invoice> invoices = new ArrayList<>();
        if(existingInvoices.isEmpty()){
            {
                Invoice invoice = new Invoice();
                invoice.setMaterialName(treatment.getTreatmentType().getName());
                invoice.setMaterialPrice(treatment.getTreatmentType().getPrice());
                invoices.add(invoice);
            }
            for(Medicine it : treatment.getMedicines()){
                Invoice invoice = new Invoice();
                invoice.setMaterialName(it.getName());
                invoice.setMaterialPrice(it.getPricePerKg());
                invoices.add(invoice);
            }
            for(SurgicalInstrument it : treatment.getSurgicals()){
                Invoice invoice = new Invoice();
                invoice.setMaterialName(it.getName());
                invoice.setMaterialPrice(it.getPrice());
                invoices.add(invoice);
            }
        }else{
            invoices.addAll(existingInvoices);
            List<String> materials = new ArrayList<>();
            for(Invoice existingInvoice : existingInvoices){
                materials.add(existingInvoice.getMaterialName());
            }
            for(Medicine it : treatment.getMedicines()){
                if(!materials.contains(it.getName())){
                    Invoice invoice = new Invoice();
                    invoice.setMaterialName(it.getName());
                    invoice.setMaterialPrice(it.getPricePerKg());
                    invoices.add(invoice);
                }
            }
            for(SurgicalInstrument it : treatment.getSurgicals()){
                if(!materials.contains(it.getName())){
                    Invoice invoice = new Invoice();
                    invoice.setMaterialName(it.getName());
                    invoice.setMaterialPrice(it.getPrice());
                    invoices.add(invoice);
                }
            }
        }
        return invoices;
    }
    
}
