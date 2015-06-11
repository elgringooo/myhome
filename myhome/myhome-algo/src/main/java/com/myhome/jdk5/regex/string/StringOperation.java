/*
 * 
 */
package com.myhome.jdk5.regex.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe complementaire du J2SDK sur la manipulation de chaines de caractéres
 * Permet nottament de supprimer les accents d'une chaine de caractères
 **/
public abstract class StringOperation {

	/** Vecteur de correspondance entre accent / sans accent **/
	private static final Map<Integer, String> forbidCharacterMap = initMap();

	/**
	 * Initialisation du tableau de correspondance entre les caractéres
	 * accentués et leur homologues non accentués
	 **/

	private static Map<Integer, String> initMap() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		java.lang.String car = null;

		car = new java.lang.String("A");
		result.put(192, car); /* '\u00C0' À alt-0192 */
		result.put(193, car); /* '\u00C1' Á alt-0193 */
		result.put(194, car); /* '\u00C2' Â alt-0194 */
		result.put(195, car); /* '\u00C3' Ã alt-0195 */
		result.put(196, car); /* '\u00C4' Ä alt-0196 */
		result.put(197, car); /* '\u00C5' Å alt-0197 */
		car = new java.lang.String("AE");
		result.put(198, car); /* '\u00C6' Æ alt-0198 */
		car = new java.lang.String("C");
		result.put(199, car); /* '\u00C7' Ç alt-0199 */
		car = new java.lang.String("E");
		result.put(200, car); /* '\u00C8' È alt-0200 */
		result.put(201, car); /* '\u00C9' É alt-0201 */
		result.put(202, car); /* '\u00CA' Ê alt-0202 */
		result.put(203, car); /* '\u00CB' Ë alt-0203 */
		car = new java.lang.String("I");
		result.put(204, car); /* '\u00CC' Ì alt-0204 */
		result.put(205, car); /* '\u00CD' Í alt-0205 */
		result.put(206, car); /* '\u00CE' Î alt-0206 */
		result.put(207, car); /* '\u00CF' Ï alt-0207 */
		car = new java.lang.String("D");
		result.put(208, car); /* '\u00D0' Ð alt-0208 */
		car = new java.lang.String("N");
		result.put(209, car); /* '\u00D1' Ñ alt-0209 */
		car = new java.lang.String("O");
		result.put(210, car); /* '\u00D2' Ò alt-0210 */
		result.put(211, car); /* '\u00D3' Ó alt-0211 */
		result.put(212, car); /* '\u00D4' Ô alt-0212 */
		result.put(213, car); /* '\u00D5' Õ alt-0213 */
		result.put(214, car); /* '\u00D6' Ö alt-0214 */
		car = new java.lang.String("*");
		result.put(215, car); /* '\u00D7' × alt-0215 */
		car = new java.lang.String("0");
		result.put(216, car); /* '\u00D8' Ø alt-0216 */
		car = new java.lang.String("U");
		result.put(217, car); /* '\u00D9' Ù alt-0217 */
		result.put(218, car); /* '\u00DA' Ú alt-0218 */
		result.put(219, car); /* '\u00DB' Û alt-0219 */
		result.put(220, car); /* '\u00DC' Ü alt-0220 */
		car = new java.lang.String("Y");
		result.put(221, car); /* '\u00DD' Ý alt-0221 */
		car = new java.lang.String("Þ");
		result.put(222, car); /* '\u00DE' Þ alt-0222 */
		car = new java.lang.String("B");
		result.put(223, car); /* '\u00DF' ß alt-0223 */
		car = new java.lang.String("a");
		result.put(224, car); /* '\u00E0' à alt-0224 */
		result.put(225, car); /* '\u00E1' á alt-0225 */
		result.put(226, car); /* '\u00E2' â alt-0226 */
		result.put(227, car); /* '\u00E3' ã alt-0227 */
		result.put(228, car); /* '\u00E4' ä alt-0228 */
		result.put(229, car); /* '\u00E5' å alt-0229 */
		car = new java.lang.String("ae");
		result.put(230, car); /* '\u00E6' æ alt-0230 */
		car = new java.lang.String("c");
		result.put(231, car); /* '\u00E7' ç alt-0231 */
		car = new java.lang.String("e");
		result.put(232, car); /* '\u00E8' è alt-0232 */
		result.put(233, car); /* '\u00E9' é alt-0233 */
		result.put(234, car); /* '\u00EA' ê alt-0234 */
		result.put(235, car); /* '\u00EB' ë alt-0235 */
		car = new java.lang.String("i");
		result.put(236, car); /* '\u00EC' ì alt-0236 */
		result.put(237, car); /* '\u00ED' í alt-0237 */
		result.put(238, car); /* '\u00EE' î alt-0238 */
		result.put(239, car); /* '\u00EF' ï alt-0239 */
		car = new java.lang.String("d");
		result.put(240, car); /* '\u00F0' ð alt-0240 */
		car = new java.lang.String("n");
		result.put(241, car); /* '\u00F1' ñ alt-0241 */
		car = new java.lang.String("o");
		result.put(242, car); /* '\u00F2' ò alt-0242 */
		result.put(243, car); /* '\u00F3' ó alt-0243 */
		result.put(244, car); /* '\u00F4' ô alt-0244 */
		result.put(245, car); /* '\u00F5' õ alt-0245 */
		result.put(246, car); /* '\u00F6' ö alt-0246 */
		car = new java.lang.String("/");
		result.put(247, car); /* '\u00F7' ÷ alt-0247 */
		car = new java.lang.String("0");
		result.put(248, car); /* '\u00F8' ø alt-0248 */
		car = new java.lang.String("u");
		result.put(249, car); /* '\u00F9' ù alt-0249 */
		result.put(250, car); /* '\u00FA' ú alt-0250 */
		result.put(251, car); /* '\u00FB' û alt-0251 */
		result.put(252, car); /* '\u00FC' ü alt-0252 */
		car = new java.lang.String("y");
		result.put(253, car); /* '\u00FD' ý alt-0253 */
		car = new java.lang.String("þ");
		result.put(254, car); /* '\u00FE' þ alt-0254 */
		car = new java.lang.String("y");
		result.put(255, car); /* '\u00FF' ÿ alt-0255 */
		result.put(200, car); /* '\u00FF' alt-0255 */

		return result;
	}

	/**
	 * Transform a string containing accents in a version without accent.
	 * 
	 * @param chaine the chaine
	 * 
	 * @return the string
	 */
	public static java.lang.String cleanAccent(java.lang.String chaine) {
		java.lang.StringBuffer result = new StringBuffer(chaine);
		final int MIN = 192;
		final int MAX = 255;
		/** Index du dernier caractere accentué **/

		for (int bcl = 0; bcl < result.length(); bcl++) {
			int carVal = chaine.charAt(bcl);
			if (carVal >= MIN && carVal <= MAX) { // Remplacement
				java.lang.String newVal = (java.lang.String) forbidCharacterMap
						.get(carVal);
				result.replace(bcl, bcl + 1, newVal);
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {

		String result = cleanAccent("[Ä{'géèê     îÏfdgdfèe$eçî()");
		System.out.println(cleanSpecialCharacters(result));

	}

	public static String cleanSpecialCharacters(String pStringToBeCleaned) {
		StringBuffer tmp = new StringBuffer();
		char car;

		int i = 0;
		while (i < pStringToBeCleaned.length()) {
			car = pStringToBeCleaned.charAt(i);

			if (Character.isJavaIdentifierPart(car)
					&& Character.getNumericValue(car) >= 0) {
				tmp.append(car);
			} else {
				tmp.append("_");
			}
			i++;
		}

		return tmp.toString().toLowerCase();
	}

}