package com.faire.marcus.exercise.util;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PriceCentsUtils {

	private static final Character DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance(Locale.US).getDecimalSeparator();

	private PriceCentsUtils() {
	}

	public static BigDecimal toBigDecimal(String priceCents) {
		if (priceCents != null && priceCents.length() > 2) {
			String left = priceCents.substring(0, priceCents.length() - 2);
			String right = priceCents.substring(priceCents.length() - 2);
			return new BigDecimal(left.concat(DECIMAL_SEPARATOR.toString()).concat(right));
		}
		return null;
	}

}
