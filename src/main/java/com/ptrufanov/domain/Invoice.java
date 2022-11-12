package com.ptrufanov.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static java.util.Optional.ofNullable;

public class Invoice {
    private List<InvoiceRow> rows;

    public BigDecimal getTotalPrice() {
        return ofNullable(rows).stream()
                .flatMap(Collection::stream)
                .map(InvoiceRow::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public List<InvoiceRow> getRows() {
        return rows;
    }

    public void setRows(List<InvoiceRow> rows) {
        this.rows = rows;
    }
}
