package de.htw.berlin.opendata.openmietspiegel;

public class Main {
	public static void main(String[] args) {
		System.out.println("waiting…");
		PDFTextParser pdf = new PDFTextParser();
		System.out.println(pdf.convertPDFToString("resources/Strassenverzeichnis2013.pdf"));
	}
}