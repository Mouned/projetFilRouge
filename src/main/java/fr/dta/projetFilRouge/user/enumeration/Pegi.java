package fr.dta.projetFilRouge.user.enumeration;

public enum Pegi {

	third("3"), seven("7"),twelve("12"),sixteen("16"),eighteen("18");
	
	private String pegi = "";

	Pegi(String pegi) {
		this.pegi = pegi;
	}

	public String toString() {
		return pegi;
	}
}
