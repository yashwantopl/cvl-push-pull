package com.opl.mudra.api.gst.model.util;

public final class GstApiErrorConstants {

	private GstApiErrorConstants() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static final String AUTH153 = "AUTH153";
	public static final String AUTH4037 = "AUTH4037";
    public static final String RET11407 = "RET11407";
    public static final String RET11409 = "RET11409";

    public static final String RET11400 = "RET11400";	// API Header Value Missing
    public static final String RET11401 = "RET11401";	// API Authentication Failed
    public static final String RET11402 = "RET11402";	// API Authorization Failed
    public static final String RET11403 = "RET11403";	// Invalid API Request
    public static final String RET11404 = "RET11404";	// Invalid API Header value
    public static final String RET11405 = "RET11405";	// Invalid API Request Parameters
    public static final String RET11406 = "RET11406";	// Malformed API Request
    public static final String RET12501 = "RET12501";	// System Failure
    public static final String RET12502 = "RET12502";	// System Failure
    public static final String RET12503 = "RET12503";	// System Failure
    public static final String RET12504 = "RET12504";	// System Failure
    public static final String RET13500 = "RET13500";	// Invalid Return Type
    public static final String RET13502 = "RET13502";	// System Failure
    public static final String RET13504 = "RET13504";	// System Failure
    public static final String RET13505 = "RET13505";	// System Failure
    public static final String RET13508 = "RET13508";	// No Details Found for the Provided Inputs
    public static final String RET13509 = "RET13509";	// No Invoices found for the provided Inputs
    public static final String RET13510 = "RET13510";	// No Record found for the provided Inputs
    public static final String RET13511 = "RET13511";	// Date format entered is Invalid
    
}
