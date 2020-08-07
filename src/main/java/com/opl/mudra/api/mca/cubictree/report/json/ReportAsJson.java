package com.opl.mudra.api.mca.cubictree.report.json;
/**
 * @Author : Maaz Shaikh
 * Time :  2:23:30 PM
 **/
public class ReportAsJson {

	Report report;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "JsonToReport [report=" + report + "]";
	}
	
}
