package com.opl.service.loans.utils;

public class RATE {
    /**
     * calculateRate: The RATE function in the class excel, the calculated result value is the monthly interest rate, and the annual interest rate is required to be *12.
     * rate = calculateRate(periods, payment, present_val, future_val, type,
     * estimate) ;
     *
     * @author guooo July 11, 2018 11:13:55 AM
     * @param nper
     * is the total investment period, which is the total payment period of the investment.
     * @param pmt
     * For each period of payment, the value will remain the same throughout the investment period. Usually pmt includes principal and interest, but does not include other fees or taxes. If ignored
     * pmt, you must include the fv parameter.
     * @param pv
     * is the present value, which is the sum of the accounts that have been credited since the beginning of the investment, or the cumulative sum of the current values ​​of a series of future payments, also known as the principal.
     * @param fv
     * For future values, or the cash balance you want after the last payment, if you omit fv, assume that the value is zero, that is, the future value of a loan is zero.
     * @param type
     * The number 0 or 1 is used to specify whether the payment period for each period is at the beginning or end of the period. 0 or omitted - end of period ||
     * @param guess
     * Expected interest rate. If the expected interest rate is omitted, the value is assumed to be 10%.
     * @return
     * @since JDK 1.6
     */
    public static double calculateRate(double nper, double pmt, double pv, double fv, double type, double guess) {
        //FROM MS http://office.microsoft.com/en-us/excel-help/rate-HP005209232.aspx
        int FINANCIAL_MAX_ITERATIONS = 20;//Bet accuracy with 128
        double FINANCIAL_PRECISION = 0.0000001;//1.0e-8

        double y, y0, y1, x0, x1 = 0, f = 0, i = 0;
        double rate = guess;
        if (Math.abs(rate) < FINANCIAL_PRECISION) {
            y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
        } else {
            f = Math.exp(nper * Math.log(1 + rate));
            y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
        }
        y0 = pv + pmt * nper + fv;
        y1 = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;

        // find root by Newton secant method
        i = x0 = 0.0;
        x1 = rate;
        while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS)) {
            rate = (y1 * x0 - y0 * x1) / (y1 - y0);
            x0 = x1;
            x1 = rate;

            if (Math.abs(rate) < FINANCIAL_PRECISION) {
                y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
            } else {
                f = Math.exp(nper * Math.log(1 + rate));
                y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
            }

            y0 = y1;
            y1 = y;
            ++i;
        }
        return rate;
    }

    /**
     * simpleCalculateRate: (here the role of this method is described in one sentence).
     *
     * @author guooo July 12, 2018 11:19:24 AM
     * @param nper
     * is the total investment period, which is the total payment period of the investment.
     * @param pmt
     * For each period of payment, the value will remain the same throughout the investment period. Usually pmt includes principal and interest, but does not include other fees or taxes. If ignored
     * pmt, you must include the fv parameter.
     * @param pv
     * is the present value, which is the sum of the accounts that have been credited since the beginning of the investment, or the cumulative sum of the current values ​​of a series of future payments, also known as the principal.
     * @return
     * @since JDK 1.6
     */
    public static double simpleCalculateRate(double nper, double pmt, double pv) {

        double fv = 0;

        //0 or omitted - end of payment
        double type = 0;

        // If the expected interest rate is omitted, the value is assumed to be 10%.
        double guess = 0.1;

        return ((calculateRate(nper, pmt, pv, fv, type, guess)*12)*100);
    }

    public static void main(String[] args) {
        System.out.println((simpleCalculateRate(36, 177569, -5000000)));
    }

}