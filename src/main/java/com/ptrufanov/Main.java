package com.ptrufanov;

import com.ptrufanov.domain.Invoice;
import com.ptrufanov.domain.Usage;
import com.ptrufanov.service.InvoiceService;
import com.ptrufanov.utils.InvoicePrinter;


import static com.ptrufanov.data.Packages.*;
import static com.ptrufanov.data.PriceLists.PRICE_LIST;
import static com.ptrufanov.utils.UsageUtils.createUsage;

public class Main {
    public static void main(String[] args) {

        Usage usage = createUsage(50, 120);

        InvoiceService invoiceService = new InvoiceService();
        Invoice invoice = invoiceService.calculateInvoiceTotal(usage, PRICE_LIST, S_PACKAGE);

        InvoicePrinter.print(invoice);
    }
}