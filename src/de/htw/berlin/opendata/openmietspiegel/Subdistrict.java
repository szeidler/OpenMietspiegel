package de.htw.berlin.opendata.openmietspiegel;

public class SubDistrict extends District {

	private District parent;

	public District getParent() {
		return parent;
	}

	public void setParent(District parent) {
		this.parent = parent;
	}
}
