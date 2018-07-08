package com.capitaworld.service.loans.service.token;

import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;

public interface TokenService {
	public String getToken(GenerateTokenRequest generateTokenRequest);
	
	public String checkTokenExpiration(String tokenString); 
	
	public Boolean setTokenAsExpired(GenerateTokenRequest generateTokenRequest) ;
	 
}
