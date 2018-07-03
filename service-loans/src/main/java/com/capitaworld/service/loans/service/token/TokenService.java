package com.capitaworld.service.loans.service.token;

import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;

public interface TokenService {
	public void saveToken(GenerateTokenRequest generateTokenRequest);
	 
}
