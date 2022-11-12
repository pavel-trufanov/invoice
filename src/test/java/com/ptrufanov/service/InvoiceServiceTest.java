package com.ptrufanov.service;

import com.ptrufanov.domain.Invoice;
import com.ptrufanov.domain.InvoiceRow;
import com.ptrufanov.domain.TrafficType;
import com.ptrufanov.domain.Usage;
import com.ptrufanov.utils.UsageUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.ptrufanov.data.Packages.S_PACKAGE;
import static com.ptrufanov.data.PriceLists.PRICE_LIST;
import static com.ptrufanov.domain.TrafficType.MINUTE;
import static com.ptrufanov.domain.TrafficType.SMS;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {

    private final InvoiceService invoiceService = new InvoiceService();

    @Test
    void calculateInvoiceTotal_noExtraUsage() {
        Usage usage = UsageUtils.createUsage(10, 10);
        Invoice invoice = invoiceService.calculateInvoiceTotal(usage, PRICE_LIST, S_PACKAGE);
        InvoiceRow packageRow = invoice.getRows().get(0);
        assertEquals(1, invoice.getRows().size());
        assertEquals(1, packageRow.getAmount());
        assertEquals(S_PACKAGE.getName(), packageRow.getDescription());
        assertEquals(S_PACKAGE.getPriceLimit(), packageRow.getPrice());
        assertEquals(S_PACKAGE.getPriceLimit(), invoice.getTotalPrice());
    }

    @Test
    void calculateInvoiceTotal_emptyUsage() {
        Invoice invoice = invoiceService.calculateInvoiceTotal(new Usage(), PRICE_LIST, S_PACKAGE);

        assertEquals(1, invoice.getRows().size());
        assertEquals(S_PACKAGE.getPriceLimit(), invoice.getTotalPrice());
    }

    @Test
    void calculateInvoiceTotal_nullUsage() {
        assertThrows(NullPointerException.class,
                () -> invoiceService.calculateInvoiceTotal(null, PRICE_LIST, S_PACKAGE));
    }

    @Test
    void calculateInvoiceTotal_extraMinutes() {
        Usage usage = UsageUtils.createUsage(20, 10);
        Invoice invoice = invoiceService.calculateInvoiceTotal(usage, PRICE_LIST, S_PACKAGE);

        InvoiceRow extraMinutesRow = findRowByTrafficType(MINUTE, invoice.getRows());
        assertEquals(2, invoice.getRows().size());
        assertEquals(10, extraMinutesRow.getAmount());
        assertEquals(0, valueOf(2).compareTo(extraMinutesRow.getPrice()));
        assertEquals(0, valueOf(7).compareTo(invoice.getTotalPrice()));
    }

    @Test
    void calculateInvoiceTotal_extraSMS() {
        Usage usage = UsageUtils.createUsage(10, 70);
        Invoice invoice = invoiceService.calculateInvoiceTotal(usage, PRICE_LIST, S_PACKAGE);

        InvoiceRow extraSmsRow = findRowByTrafficType(SMS, invoice.getRows());
        assertEquals(2, invoice.getRows().size());
        assertEquals(20, extraSmsRow.getAmount());
        assertEquals(0, valueOf(6).compareTo(extraSmsRow.getPrice()));
        assertEquals(0, valueOf(11).compareTo(invoice.getTotalPrice()));
    }

    @Test
    void calculateInvoiceTotal_extraMinutesAndExtraSMS() {
        Usage usage = UsageUtils.createUsage(30, 60);
        Invoice invoice = invoiceService.calculateInvoiceTotal(usage, PRICE_LIST, S_PACKAGE);

        InvoiceRow extraMinutesRow = findRowByTrafficType(MINUTE, invoice.getRows());
        InvoiceRow extraSmsRow = findRowByTrafficType(SMS, invoice.getRows());

        assertEquals(3, invoice.getRows().size());
        assertEquals(20, extraMinutesRow.getAmount());
        assertEquals(0, valueOf(4).compareTo(extraMinutesRow.getPrice()));
        assertEquals(10, extraSmsRow.getAmount());
        assertEquals(0, valueOf(3).compareTo(extraSmsRow.getPrice()));
        assertEquals(0, valueOf(12).compareTo(invoice.getTotalPrice()));
    }

    private InvoiceRow findRowByTrafficType(TrafficType type, List<InvoiceRow> rows) {
        return rows.stream()
                .filter(invoiceRow -> invoiceRow.getDescription().contains(type.name()))
                .findFirst()
                .orElse(null);
    }
}