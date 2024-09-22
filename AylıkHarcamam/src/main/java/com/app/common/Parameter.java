package com.app.common;

import java.text.NumberFormat;
import java.util.Locale;

public class Parameter {

    public static NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(new Locale("tr", "TR"));
}
