package com.opl.mudra.api.gst.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opl.mudra.api.gst.model.karza.Gstr3Details;

public class DateComparator3 implements Comparator<Gstr3Details>{

    private final Logger logger = LoggerFactory.getLogger(DateComparator3.class);

    Date convertToDate(Object o1) throws ParseException {
        String dateStr = "";
        String mainStr = String.valueOf(o1);
        String date = "01";
        String month = "";
        String year = "";
        if (mainStr.length() == 6) {
            month = mainStr.substring(0, 2);
            year = mainStr.substring(2, mainStr.length());
        } else {
            month = mainStr.substring(0, 1);
            year = mainStr.substring(1, mainStr.length());
        }
        dateStr = date + "/" + month + "/" + year;
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
    }

	@Override
	public int compare(Gstr3Details o1, Gstr3Details o2) {


        try {
        	
            Date d1 = convertToDate(o1.getRetPeriod());
            Date d2 = convertToDate(o2.getRetPeriod());
            if (d1.after(d2))
                return 1;
            else if (d1.before(d2))
                return -1;
            else
                return 0;
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION ,e);
        }
        return 0;
    
	}
}
