package com.example.taltosrendelo.entity;

import java.util.List;

public class InvoiceListDto {
    
    private List<Invoice> invoices;


    public InvoiceListDto() {
    }

    public InvoiceListDto(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
