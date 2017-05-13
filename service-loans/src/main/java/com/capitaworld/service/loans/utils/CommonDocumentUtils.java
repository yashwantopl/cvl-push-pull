package com.capitaworld.service.loans.utils;

import com.capitaworld.service.dms.util.DocumentAlias;

public class CommonDocumentUtils {

	
	public static Long getProductDocumentId(int productId)
	{
		switch (productId) {
		case 1:
			return DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE;
		case 2:
			return DocumentAlias.TERM_LOAN_PROFIEL_PICTURE;
		case 3:
			return DocumentAlias.HOME_LOAN_PROFIEL_PICTURE;
		case 12:
			return DocumentAlias.CAR_LOAN_PROFIEL_PICTURE;
		case 7:
			return DocumentAlias.PERSONAL_LOAN_PROFIEL_PICTURE;
		case 13:
			return DocumentAlias.LAP_LOAN_PROFIEL_PICTURE;
		case 14:
			return DocumentAlias.LAS_LOAN_PROFIEL_PICTURE;
		}
		return null;
	}
}
