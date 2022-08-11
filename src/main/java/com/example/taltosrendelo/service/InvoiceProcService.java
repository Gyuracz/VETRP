package com.example.taltosrendelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Invoice;
import com.example.taltosrendelo.entity.InvoiceListDto;
import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.repository.InvoiceRepository;
import com.example.taltosrendelo.repository.MedicineRepository;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;

@Service
public class InvoiceProcService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private UpdateMedicineService updateMedicineService;

    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    @Autowired
    private UpdateSurgicalInstrumentService updateSurgicalInstrumentService;

    public void service(InvoiceListDto invoiceListDto, Long treatmentId){
        List<Invoice> existing = invoiceRepository.findAllByTreatmentId(treatmentId);
        for(Invoice invoice : existing){
            for(Medicine it : medicineRepository.findAll()){
                if(it.getName().equals(invoice.getMaterialName())){
                    it.setQuantity(it.getQuantity() + invoice.getQuantity());
                    updateMedicineService.service(it);
                }
            }
            for(SurgicalInstrument it : surgicalInstrumentRepository.findAll()){
                if(it.getName().equals(invoice.getMaterialName())){
                    it.setQuantity(it.getQuantity() + invoice.getQuantity());
                    updateSurgicalInstrumentService.service(it);
                }
            }
        }
        invoiceRepository.deleteAll(existing);

        for(Invoice it : invoiceListDto.getInvoices()){
            it.setTreatmentId(treatmentId);
        }
        List<Invoice> saved = invoiceRepository.saveAll(invoiceListDto.getInvoices());
        for(Invoice invoice : saved){
            for(Medicine it : medicineRepository.findAll()){
                if(it.getName().equals(invoice.getMaterialName())){
                    it.setQuantity(it.getQuantity() - invoice.getQuantity());
                    updateMedicineService.service(it);
                }
            }
            for(SurgicalInstrument it : surgicalInstrumentRepository.findAll()){
                if(it.getName().equals(invoice.getMaterialName())){
                    it.setQuantity(it.getQuantity() - invoice.getQuantity());
                    updateSurgicalInstrumentService.service(it);
                }
            }
        }
    }

}
