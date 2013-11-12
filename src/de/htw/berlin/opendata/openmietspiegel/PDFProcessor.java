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
				String[] items = line.split("\\s");
				lastDistrict = new District(items[0], items[1]);
				districts.add(lastDistrict);
			} else {
				// it's a subdistrict
				String[] items = line.split("\\s(?=\\S*$)");
				//@TODO Find a proper solution for double lines: Stadtrandsiedlung Malchow
				if (items[1].equals("StaMa")) {
					items[0] = "Stadtrandsiedlung Malchow";
				}
				if (!items[0].isEmpty() && !items[1].isEmpty()) {
					subDistricts.add(new SubDistrict(items[1], items[0].trim(), lastDistrict));
				}
			}
		}
		for(SubDistrict item :subDistricts) {
			System.out.println(item.toString());
		}
	}
}
