package com.app.common.utils;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class FileUtils {
    public static BufferedWriter generateWriter(File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    public static void writeLine(BufferedWriter writer, String str) {
        try {
            writer.write(str);
            writer.newLine();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String createDebtLine(String debtType, BigDecimal debtAmount) {
        return String.format("%s : %.2f TL", debtType, debtAmount);
    }

    public static String writeExtraLine(String lineName, BigDecimal lineValue) {
        return String.format("%s ==========> %.2f TL", lineName, lineValue);
    }
}
