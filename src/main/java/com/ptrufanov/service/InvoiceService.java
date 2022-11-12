package com.ptrufanov.service;

import com.ptrufanov.domain.*;
import com.ptrufanov.domain.Package;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class InvoiceService {

    public Invoice calculateInvoiceTotal(Usage usage, PriceList priceList, Package apPackage) {
        Objects.requireNonNull(usage, "An error during invoice generation. Traffic usage is not set");
        List<InvoiceRow> invoiceRows = new ArrayList<>();
        invoiceRows.add(generatePackageInvoiceRow(apPackage));
        List<InvoiceRow> extraInvoiceRows = usage.getUsages().entrySet().stream()
                .map(usageEntry -> generateExtraInvoiceRow(usageEntry.getKey(), usageEntry.getValue(), apPackage, priceList))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        invoiceRows.addAll(extraInvoiceRows);
        Invoice invoice = new Invoice();
        invoice.setRows(invoiceRows);
        return invoice;
    }

    private Optional<InvoiceRow> generateExtraInvoiceRow(TrafficType trafficType, int usage, Package aPackage, PriceList priceList) {
        int extraAmount = calculateExtraTrafficAmount(usage, trafficType, aPackage);
        if (extraAmount > 0) {
            BigDecimal price = priceList.getPrice(trafficType);
            return of(createInvoiceRowForExtraAmount(extraAmount, price, trafficType));
        } else {
            return empty();
        }
    }

    private int calculateExtraTrafficAmount(int usedAmount, TrafficType trafficType, Package aPackage) {
        int packageTrafficAmount = aPackage.getAmount(trafficType);
        if (usedAmount > packageTrafficAmount) {
            return usedAmount - packageTrafficAmount;
        } else {
            return 0;
        }
    }

    private InvoiceRow createInvoiceRowForExtraAmount(int extraAmount, BigDecimal price, TrafficType trafficType) {
        InvoiceRow extraRow = new InvoiceRow();
        extraRow.setAmount(extraAmount);
        extraRow.setPrice(price.multiply(valueOf(extraAmount)));
        extraRow.setDescription("Extra " + trafficType.name());
        return extraRow;
    }

    private InvoiceRow generatePackageInvoiceRow(Package aPackage) {
        InvoiceRow packageRow = new InvoiceRow();
        packageRow.setAmount(1);
        packageRow.setDescription(aPackage.getName());
        packageRow.setPrice(aPackage.getPriceLimit());
        return packageRow;
    }
}
