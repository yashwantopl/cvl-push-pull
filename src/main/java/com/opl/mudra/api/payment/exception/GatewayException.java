package com.opl.mudra.api.payment.exception;

public class GatewayException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1608172077633284309L;

	
	//Parameterless Constructor
	public GatewayException() {
		
		
		
	}
	
	//Constructor that accepts a message
	public GatewayException(String message) {
		
		super(message);
		
	}
	

}
