package fr.dta.projetFilRouge.utils;

public class StringUtils {

	
	public static boolean testFiles(String url) {
		String[] tabTypeImage = url.split(".");
		
		String typeImage = tabTypeImage[1];
		
		return "jpeg".equals(typeImage) || "png".equals(typeImage) || "tiff".equals(typeImage) || "bmp".equals(typeImage) || "gif".equals(typeImage);
	}
}
