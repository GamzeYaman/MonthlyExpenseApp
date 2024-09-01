package com.app.common.mapper;

import com.app.common.enums.DebtType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Mapper
public class CommonMapper {

    @Named("enumToString")
    public String enumToString(DebtType source) {
        if (source != null && source.getText() != null) {
            return source.getText();
        }
        return null;
    }

    @Named("getMonthName")
    public String getMonthName(Month month) {
        return month.getDisplayName(TextStyle.FULL, new Locale("tr"));
    }

    public @Named("getMoneyInFormatted")
    String getMoneyInFormatted(BigDecimal amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("tr"));
        return numberFormat.format(amount);
    }
}
