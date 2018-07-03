package com.capitaworld.service.loans.service.token.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capitaworld.service.loans.domain.token.TokenDetail;
import com.capitaworld.service.loans.repository.token.TokenRepository;
import com.capitaworld.service.loans.service.token.TokenService;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;

@Service
@Transactional
public class TokenServiceImpl implements TokenService{
	
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private TokenRepository tokenRepository ;
	
	public void saveToken(GenerateTokenRequest generateTokenRequest ) {
		logger.info("=================Enter in saveToken() {} ======================");
		tokenRepository.inActive(generateTokenRequest.getApplicationId());
		TokenDetail tokenDetail =new TokenDetail();
		tokenDetail = new  TokenDetail();
		tokenDetail.setApplicationId(generateTokenRequest.getApplicationId() );
		tokenDetail.setCreatedDate(new Date());
		tokenDetail.setIsExpired(false); 
		tokenDetail.setToken(generateTokenRequest.getToken());
		tokenDetail.setIsActive(true);
		tokenRepository.save(tokenDetail);
		logger.info("=================Exist from saveToken() {} ====================== + tokenDetail ==> " + tokenDetail );
		
	}	
	
}
