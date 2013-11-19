package de.htw.berlin.opendata.openmietspiegel;

public class StreetObject {

	private District district; // some streets extend over more than one districts
	private SubDistrict subDistrict; // some streets extend over more than one districts
	private Area area;
	private Street street;
	private String fromStreetNumber;
	private String toStreetNumber;
	private StreetNumberClassification streetNumberClassification;
	private AreaRating rating;
	private boolean hasNoisePollution;

	public StreetObject(String fromAddress, String toStreetNumber) {
		this.fromStreetNumber = fromStreetNumber;
		this.toStreetNumber = toStreetNumber;
	}

	public StreetObject(String fromStreetNumber) {
		this.fromStreetNumber = fromStreetNumber;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public SubDistrict getSubDistrict() {
		return subDistrict;
	}

	public void setDistrict(SubDistrict subDistrict) {
		this.subDistrict = subDistrict;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getFromStreetNumber() {
		return fromStreetNumber;
	}

	public void setFromStreetNumber(String fromStreetNumber) {
		this.fromStreetNumber = fromStreetNumber;
	}

	public String getToStreetNumber() {
		return toStreetNumber;
	}

	public void setToStreetNumber(String toStreetNumber) {
		this.toStreetNumber = toStreetNumber;
	}

	public StreetNumberClassification getStreetNumberClassification() {
		return streetNumberClassification;
	}

	public void setStreetNumberClassification(StreetNumberClassification streetNumberClassification) {
		this.streetNumberClassification = streetNumberClassification;
	}

	public AreaRating getRating() {
		return rating;
	}

	public void setRating(AreaRating rating) {
		this.rating = rating;
	}

	public boolean isHasNoisePollution() {
		return hasNoisePollution;
	}

	public void setHasNoisePollution(boolean hasNoisePollution) {
		this.hasNoisePollution = hasNoisePollution;
	}
}
