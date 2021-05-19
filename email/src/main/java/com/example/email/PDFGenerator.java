package com.example.email;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerator {
	public void generatePDF(String FilePath) throws DocumentException, FileNotFoundException {
		Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FilePath));
			document.open();
			document.add(generateTable());
			document.close();
	}

	private PdfPTable generateTable() {
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("First Line:"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Column1A");
		table.addCell("Column1B");
		table.addCell("Column2A");
		table.addCell("Column2B");
		table.addCell("Column3A");
		table.addCell("Column3B");
		table.addCell("Column4A");
		table.addCell("Column4B");
		table.addCell("Column5A");
		table.addCell("Column5B");
		return table;
	}
}
