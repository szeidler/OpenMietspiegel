package de.htw.berlin.opendata.openmietspiegel;

public class SubDistrict extends District {

	private District parent;

	public SubDistrict(String shortName, String name, District parent) {
		this.shortName = shortName;
		this.name = name;
		this.parent = parent;
	}

	public District getParent() {
		return parent;
	}

	public void setParent(District parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Subdistrict{" + "name='" + name + '\'' + ", shortName='" + shortName + '\'' + ", parent='" + parent.getName() + '\'' + '}';
	}
}
