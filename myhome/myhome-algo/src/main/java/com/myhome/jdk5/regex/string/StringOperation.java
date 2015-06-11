/*
 * 
 */
package com.myhome.jdk5.regex.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe complementaire du J2SDK sur la manipulation de chaines de caract�res
 * Permet nottament de supprimer les accents d'une chaine de caract�res
 **/
public abstract class StringOperation {

	/** Vecteur de correspondance entre accent / sans accent **/
	private static final Map<Integer, String> forbidCharacterMap = initMap();

	/**
	 * Initialisation du tableau de correspondance entre les caract�res
	 * accentu�s et leur homologues non accentu�s
	 **/

	private static Map<Integer, String> initMap() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		java.lang.String car = null;

		car = new java.lang.String("A");
		result.put(192, car); /* '\u00C0' � alt-0192 */
		result.put(193, car); /* '\u00C1' � alt-0193 */
		result.put(194, car); /* '\u00C2' � alt-0194 */
		result.put(195, car); /* '\u00C3' � alt-0195 */
		result.put(196, car); /* '\u00C4' � alt-0196 */
		result.put(197, car); /* '\u00C5' � alt-0197 */
		car = new java.lang.String("AE");
		result.put(198, car); /* '\u00C6' � alt-0198 */
		car = new java.lang.String("C");
		result.put(199, car); /* '\u00C7' � alt-0199 */
		car = new java.lang.String("E");
		result.put(200, car); /* '\u00C8' � alt-0200 */
		result.put(201, car); /* '\u00C9' � alt-0201 */
		result.put(202, car); /* '\u00CA' � alt-0202 */
		result.put(203, car); /* '\u00CB' � alt-0203 */
		car = new java.lang.String("I");
		result.put(204, car); /* '\u00CC' � alt-0204 */
		result.put(205, car); /* '\u00CD' � alt-0205 */
		result.put(206, car); /* '\u00CE' � alt-0206 */
		result.put(207, car); /* '\u00CF' � alt-0207 */
		car = new java.lang.String("D");
		result.put(208, car); /* '\u00D0' � alt-0208 */
		car = new java.lang.String("N");
		result.put(209, car); /* '\u00D1' � alt-0209 */
		car = new java.lang.String("O");
		result.put(210, car); /* '\u00D2' � alt-0210 */
		result.put(211, car); /* '\u00D3' � alt-0211 */
		result.put(212, car); /* '\u00D4' � alt-0212 */
		result.put(213, car); /* '\u00D5' � alt-0213 */
		result.put(214, car); /* '\u00D6' � alt-0214 */
		car = new java.lang.String("*");
		result.put(215, car); /* '\u00D7' � alt-0215 */
		car = new java.lang.String("0");
		result.put(216, car); /* '\u00D8' � alt-0216 */
		car = new java.lang.String("U");
		result.put(217, car); /* '\u00D9' � alt-0217 */
		result.put(218, car); /* '\u00DA' � alt-0218 */
		result.put(219, car); /* '\u00DB' � alt-0219 */
		result.put(220, car); /* '\u00DC' � alt-0220 */
		car = new java.lang.String("Y");
		result.put(221, car); /* '\u00DD' � alt-0221 */
		car = new java.lang.String("�");
		result.put(222, car); /* '\u00DE' � alt-0222 */
		car = new java.lang.String("B");
		result.put(223, car); /* '\u00DF' � alt-0223 */
		car = new java.lang.String("a");
		result.put(224, car); /* '\u00E0' � alt-0224 */
		result.put(225, car); /* '\u00E1' � alt-0225 */
		result.put(226, car); /* '\u00E2' � alt-0226 */
		result.put(227, car); /* '\u00E3' � alt-0227 */
		result.put(228, car); /* '\u00E4' � alt-0228 */
		result.put(229, car); /* '\u00E5' � alt-0229 */
		car = new java.lang.String("ae");
		result.put(230, car); /* '\u00E6' � alt-0230 */
		car = new java.lang.String("c");
		result.put(231, car); /* '\u00E7' � alt-0231 */
		car = new java.lang.String("e");
		result.put(232, car); /* '\u00E8' � alt-0232 */
		result.put(233, car); /* '\u00E9' � alt-0233 */
		result.put(234, car); /* '\u00EA' � alt-0234 */
		result.put(235, car); /* '\u00EB' � alt-0235 */
		car = new java.lang.String("i");
		result.put(236, car); /* '\u00EC' � alt-0236 */
		result.put(237, car); /* '\u00ED' � alt-0237 */
		result.put(238, car); /* '\u00EE' � alt-0238 */
		result.put(239, car); /* '\u00EF' � alt-0239 */
		car = new java.lang.String("d");
		result.put(240, car); /* '\u00F0' � alt-0240 */
		car = new java.lang.String("n");
		result.put(241, car); /* '\u00F1' � alt-0241 */
		car = new java.lang.String("o");
		result.put(242, car); /* '\u00F2' � alt-0242 */
		result.put(243, car); /* '\u00F3' � alt-0243 */
		result.put(244, car); /* '\u00F4' � alt-0244 */
		result.put(245, car); /* '\u00F5' � alt-0245 */
		result.put(246, car); /* '\u00F6' � alt-0246 */
		car = new java.lang.String("/");
		result.put(247, car); /* '\u00F7' � alt-0247 */
		car = new java.lang.String("0");
		result.put(248, car); /* '\u00F8' � alt-0248 */
		car = new java.lang.String("u");
		result.put(249, car); /* '\u00F9' � alt-0249 */
		result.put(250, car); /* '\u00FA' � alt-0250 */
		result.put(251, car); /* '\u00FB' � alt-0251 */
		result.put(252, car); /* '\u00FC' � alt-0252 */
		car = new java.lang.String("y");
		result.put(253, car); /* '\u00FD' � alt-0253 */
		car = new java.lang.String("�");
		result.put(254, car); /* '\u00FE' � alt-0254 */
		car = new java.lang.String("y");
		result.put(255, car); /* '\u00FF' � alt-0255 */
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
		/** Index du dernier caractere accentu� **/

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

		String result = cleanAccent("[�{'g���     ��fdgdf�e$e��()");
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