package com.app.consumer.common.enums;

import lombok.Getter;

import java.util.stream.Stream;

public enum DebtType {
    KREDI_BORCU("Kredi Borcu", 0),
    SU_FATURASI("Su Faturası", 2),
    ELEKTIRIK_FATURASI("Elektirik Faturası", 3),
    DOGALGAZ_FATURASI("Doğalgaz Faturası", 4),
    TELEFON_FATURASI("Telefon Faturası", 5),
    INTERNET_FATURASI("Internet Faturası", 10)
    ;

    private String text;
    private int val;

    private DebtType(String text, int val) {
        this.text = text;
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }
}
