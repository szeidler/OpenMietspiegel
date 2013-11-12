package de.htw.berlin.opendata.openmietspiegel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFProcessor {

	private List<District> districts = new ArrayList<District>();
	private List<SubDistrict> subDistricts = new ArrayList<SubDistrict>();

	public void process(String pdfText) {
		List<String> pdfLineByLine= Arrays.asList(pdfText.split("\n"));
		District lastDistrict = null;
		// quick&dirty skip of header and footer
		for (int i = 3; i < pdfLineByLine.size() - 1; i++) {
			String line = pdfLineByLine.get(i);
			if (!Character.isWhitespace(line.charAt(0))) {
				// it's a district
				String[] items = line.split("\\s", 2);
				lastDistrict = new District(items[0], items[1]);
				districts.add(lastDistrict);
			} else {
				// it's a subdistrict
				String[] items = line.split(" ", -1);
				if (items.length > 2) {
					subDistricts.add(new SubDistrict(items[2], items[1], lastDistrict));
				}
			}
		}
	}
}
