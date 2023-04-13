package com.example.PdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class PdfGeneratorApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(PdfGeneratorApplication.class, args);
		String dest = "./sample.pdf";
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdfDoc = new PdfDocument(writer);
		pdfDoc.addNewPage();
		Document document = new Document(pdfDoc);
		document.close();
	}

}


