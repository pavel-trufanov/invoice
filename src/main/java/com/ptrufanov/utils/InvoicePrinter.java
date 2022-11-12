package com.ptrufanov.utils;

import com.ptrufanov.domain.Invoice;

public class InvoicePrinter {

    public static void print(Invoice invoice) {
        System.out.format("%-15s %-15s %-15s%n", "Description", "Amount", "Price");
        System.out.println("---------------------------------------");
        invoice.getRows().forEach(row -> {
            System.out.format("%-15s %-15s %5.2f%n", row.getDescription(), row.getAmount(), row.getPrice());
        });
        System.out.println("---------------------------------------");
        System.out.format("%-30s %5.2f%n", "Invoice total:", invoice.getTotalPrice());
    }
}
