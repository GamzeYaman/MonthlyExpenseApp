package com.app.common.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;

@Service
public class FileUtils {
    public static BufferedWriter generateWriter(File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    public static BufferedReader generateReader(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    public static Document generateDocument(String pdfFilePath) throws FileNotFoundException {
        return new Document(new PdfDocument(new PdfWriter(pdfFilePath)));
    }

    public static PdfFont getPdfFont() throws IOException {
        return PdfFontFactory.createFont("C:/Windows/Fonts/arial.ttf", "Identity-H");
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
