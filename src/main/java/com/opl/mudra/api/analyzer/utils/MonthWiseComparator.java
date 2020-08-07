package com.opl.mudra.api.analyzer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
  * @auther : Maaz Shaikh
  * @Time : 07-Aug-2019 
  */
public class MonthWiseComparator implements Comparator{

	final SimpleDateFormat displayFormate=new SimpleDateFormat("MMM yy");
	final SimpleDateFormat monthFormat=new SimpleDateFormat("MM");
	
	public int compare(Object o1, Object o2) {

        try {
            Date d1 = convertToDate(o1);
            Date d2 = convertToDate(o2);
            if (d1.after(d2))
                return 1;
            else if (d1.before(d2))
                return -1;
            else
                return 0;
        } catch (Exception e) {
        }
        return 0;
    }

    Date convertToDate(Object o1) throws ParseException {
        String dateStr = "";
        String date = "01";
        
        Date parse = displayFormate.parse(o1.toString());
        dateStr = date + "/" + parse.getMonth()+ "/" + parse.getYear();
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
    }


	
}
