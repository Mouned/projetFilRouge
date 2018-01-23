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
	
	public static Pegi valueByCode(String code) {
		switch(code) {
			case("3") : return third;
			case("7") : return seven;
			case("12") : return twelve;
			case("16") : return sixteen;
			case("18") : return eighteen;
			default : return null;
		}
	}
}
