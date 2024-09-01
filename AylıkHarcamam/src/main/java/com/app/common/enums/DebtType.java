package com.app.common.enums;

import lombok.Getter;

import java.util.stream.Stream;

public enum DebtType {
    KREDI_BORCU("Kredi Borcu", 0),
    KREDI_KARTI_BORCU("Kredi Kartı Borcu", 1),
    SU_FATURASI("Su Faturası", 2),
    ELEKTIRIK_FATURASI("Elektirik Faturası", 3),
    DOGALGAZ_FATURASI("Doğalgaz Faturası", 4),
    TELEFON_FATURASI("Telefon Faturası", 5),
    KIRA("Kira", 6),
    MUTFAK_HARCAMASI("Mutfak Harcaması", 7),
    UYELIK_HARCAMASI("Üyelik Harcaması (Spotify/Netflix/Disney+)", 8),
    OZEL_HARCAMA("Özel Harcama", 9);

    @Getter
    private String text;
    private int value;

    private DebtType(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static DebtType getDebtTypeByValue(int debtTypeValue) {
        return Stream.of(values())
                .filter(debtType -> debtType.value == debtTypeValue)
                .findFirst()
                .orElse(null);
    }

}
