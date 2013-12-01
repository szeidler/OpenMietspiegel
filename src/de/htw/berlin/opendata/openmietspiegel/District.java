package de.htw.berlin.opendata.openmietspiegel;

import java.util.ArrayList;

public class District {

	protected String name;
	protected String shortName;
	protected ArrayList<SubDistrict> subDistricts = new ArrayList<SubDistrict>();

	public District() {
	}

	public District(String shortName) {
		this.shortName = shortName;
	}

	public District(String shortName, String name) {
		this.shortName = shortName;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "District{" + "name='" + name + '\'' + ", shortName='" + shortName + '\'' + '}';
	}
}
