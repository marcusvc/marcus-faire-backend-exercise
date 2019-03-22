package com.faire.marcus.exercise.util;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PriceCentsUtils {

	private static final Character DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance(Locale.US).getDecimalSeparator();

	private PriceCentsUtils() {
	}

	public static BigDecimal toBigDecimal(String priceCents) {
		if (priceCents != null) {
			String left;
			String right;
			switch (priceCents.length()) {
			case 0:
				left = "0";
				right = "00";
				break;
			case 1:
				left = "0";
				right = "0" + priceCents;
				break;
			case 2:
				left = "0";
				right = priceCents;
				break;
			default:
				left = priceCents.substring(0, priceCents.length() - 2);
				right = priceCents.substring(priceCents.length() - 2);
				break;
			}
			return new BigDecimal(left.concat(DECIMAL_SEPARATOR.toString()).concat(right));
		}
		return null;
	}

}
