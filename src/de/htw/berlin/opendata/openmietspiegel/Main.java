package de.htw.berlin.opendata.openmietspiegel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("waiting…");
		PDFTextParser pdf = new PDFTextParser();
		String pdfText = pdf.convertPDFToString("resources/Strassenverzeichnis2013.pdf");
		PDFProcessor pdfProcessor = new PDFProcessor();
		pdfProcessor.process(pdfText);
	}
}