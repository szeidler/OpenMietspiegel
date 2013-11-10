package de.htw.berlin.opendata.openmietspiegel;
import de.htw.berlin.opendata.openmietspiegel.PDFTextParser;

public class Main {

    public static void main(String[] args) {
        System.out.println("waiting…");
        System.out.println(PDFTextParser.pdftoText("resources/Strassenverzeichnis2013.pdf"));
    }
}
