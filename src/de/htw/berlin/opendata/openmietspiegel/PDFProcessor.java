package de.htw.berlin.opendata.openmietspiegel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFProcessor {

	private List<District> districts = new ArrayList<District>();
	private List<SubDistrict> subDistricts = new ArrayList<SubDistrict>();
	private List<StreetObject> streets = new ArrayList<StreetObject>();

	public void processDistricts(String pdfText) {
		List<String> pdfLineByLine= Arrays.asList(pdfText.split("\n"));
		District lastDistrict = null;
		// quick&dirty skip of header and footer
		for (int i = 3; i < pdfLineByLine.size() - 1; i++) {
			String line = pdfLineByLine.get(i);
			if (!Character.isWhitespace(line.charAt(0))) {
				// it's a district
				line = line.replace("  ", " ");
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
				//@TODO Find a proper solution for multiple whitespace
				if (items[1].equals("Lich")) {
					items[0] = "Lichtenberg";
				}
				if (!items[0].isEmpty() && !items[1].isEmpty()) 
				{
					SubDistrict sd = new SubDistrict(items[1], items[0].trim(), lastDistrict);
					lastDistrict.subDistricts.add(sd);
					subDistricts.add(sd);
				}
			}
		}
		/*
		for(District item :districts) {
			System.out.println(item.toString());
		}
		for(SubDistrict item :subDistricts) {
			System.out.println(item.toString());
		}*/
	}

	public void processData(String pdfText)
	{
		List<String> pdfLineByLine= Arrays.asList(pdfText.split("\n"));
		boolean parse = false;
		for ( String line : pdfLineByLine )
		{
			// AB hier wirds interessant
			if ( line.equals("A") )
			{
				parse = true;
				continue;
			}
			
			if (!parse)
				continue;
			
			if ( line.length() == 1 )
				System.out.println(line);
			
			StreetObject so = null;
			
			try
			{
				so = this.getStreetObjectByLine(line);
			}
			catch ( Exception e )
			{
				System.out.println(e.getMessage());
			}
			
			if ( so != null )
				this.streets.add(so);
		}
		
		System.out.println("Done.");
	}
	
	private StreetObject getStreetObjectByLine(String line) 
	{
		StreetObject so = null;
		// parse action
					boolean hasNoisePollution = false;
					AreaRating rating;
					StreetNumberClassification snc;
					String streetNumberFrom = "";
					String streetNumberTo = "";
					Area area;
					Street street = null;
					District dis = null;
					
					String[] items = line.split("\\s");
					
					// rating and noise pollution
					int index = items.length-1;
					String item = items[index];
					hasNoisePollution = item.contains("*");
					item = item.replace("*", "");
					rating = AreaRating.valueOf(item.toUpperCase());
					index--;
					
					// classification
					item = items[index];
					snc = StreetNumberClassification.valueOf(item.toUpperCase());
					index--;
					
					// alle weiteren whitespaces ignorieren, n�chstes item ist Area
					if ( snc == StreetNumberClassification.K )
					{
						// whitespaces ignorieren
						while ( items[index].equals("") )
						{
							index--;
						}
						
						// jetzt haben wir die area
						item = items[index];
						area = Area.valueOf(item.toUpperCase());
					}
					// hausnummern ermitteln
					else
					{
						// whitespaces ignorieren
						while ( items[index].equals("") )
						{
							index--;
						}
						
						// hausnummern mit buchstaben beinhalten ein extra leerzeichen 
						if ( !this.isNumeric(items[index]) )
						{
							streetNumberTo = items[index];
							index--;
						}
						
						streetNumberTo += items[index];
						index--;
						
						streetNumberTo = new StringBuilder(streetNumberTo).reverse().toString();
						
						// whitespaces ignorieren
						while ( items[index].equals("-") || items[index].equals("") )
						{
							index--;
						}
						
						if ( !this.isNumeric(items[index]) )
						{
							streetNumberFrom = items[index];
							index--;
						}
						
						streetNumberFrom += items[index];
						index--;
						
						streetNumberFrom = new StringBuilder(streetNumberFrom).reverse().toString();
					}
					
					
					// area
					item = items[index];
					area = Area.valueOf(item.toUpperCase());
					index--;
					
					// district
					dis = this.findDistrictByShortName(items[index]);
					index--;
					
					String streetName = "";
					// der Rest ist der Stra�enname
					for ( int i = 0; i<=index; i++ )
					{
						if ( i>0 )
							streetName += " ";
						
						streetName += items[i];
					}
					street = new Street(streetName);
					
					so = new StreetObject(streetNumberFrom);
					so.setArea(area);
					so.setDistrict(dis);
					so.setHasNoisePollution(hasNoisePollution);
					so.setRating(rating);
					so.setStreetNumberClassification(snc);
					so.setToStreetNumber(streetNumberTo);
					so.setStreet(street);
					
					return so;
	}

	private District findDistrictByShortName(String shortName)
	{
		District d = null;
		
		for ( District dis : this.districts )
		{
			if ( shortName.equals(dis.getShortName()) )
			{
				d = dis;
				break;
			}
		}
		
		return d;
	}
	
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
