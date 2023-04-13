package com.example.PdfGenerator;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

public class AddingTable {
    public static void main(String[] args) throws Exception {
        String dest = "C:/itextExamples/addingTable.pdf";
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdf = new PdfDocument(writer);

        Document doc = new Document(pdf, PageSize.A4.rotate());

        float sizeCol = 75F;
        int sizeFont = 6;

        float [] pointColumnWidths = {sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol,
                sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol};
        Table table = new Table(pointColumnWidths);

        PdfFont russian = PdfFontFactory.createFont(
                "src/main/resources/fonts/FreeSans/freesans.ttf", "CP1251", true);

        addRow(table, sizeFont);

        doc.setFont(russian);
        doc.add(table);

        doc.close();
        System.out.println("Table created successfully..");
    }


    public static void addRow(Table table, int sizeFont){
        table.addCell(new Cell().add("Имя").setFontSize(sizeFont));
        table.addCell(new Cell().add("Фамилия").setFontSize(sizeFont));
        table.addCell(new Cell().add("Отчество").setFontSize(sizeFont));
        table.addCell(new Cell().add("Возраст").setFontSize(sizeFont));
        table.addCell(new Cell().add("Пол").setFontSize(sizeFont));
        table.addCell(new Cell().add("Дата рождения").setFontSize(sizeFont));
        table.addCell(new Cell().add("Место рождения").setFontSize(sizeFont));
        table.addCell(new Cell().add("Индекс").setFontSize(sizeFont));
        table.addCell(new Cell().add("Страна").setFontSize(sizeFont));
        table.addCell(new Cell().add("Область").setFontSize(sizeFont));
        table.addCell(new Cell().add("Город").setFontSize(sizeFont));
        table.addCell(new Cell().add("Улица").setFontSize(sizeFont));
        table.addCell(new Cell().add("Дом").setFontSize(sizeFont));
        table.addCell(new Cell().add("Квартира").setFontSize(sizeFont));
    }
}