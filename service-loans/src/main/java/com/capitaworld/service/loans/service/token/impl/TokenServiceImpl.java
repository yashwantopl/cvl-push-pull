package com.capitaworld.service.loans.service.token.impl;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capitaworld.service.loans.domain.token.TokenDetail;
import com.capitaworld.service.loans.repository.token.TokenRepository;
import com.capitaworld.service.loans.service.token.TokenService;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;
import com.capitaworld.sidbi.integration.util.CommonUtils;

@Service
@Transactional
public class TokenServiceImpl implements TokenService{
	
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private TokenRepository tokenRepository ;
	
	@Value("${capitaworld.sidbi.integration.token.expire.time}")
	private Long  tokenExpireTime;
	
	@Override
	public String getToken(GenerateTokenRequest generateTokenRequest) {
		logger.info("=================Enter in getToken() {} ======================== GenerateTokenRequest "+ generateTokenRequest);
		String token =null;
		logger.info("-------------------Start Generating token {} --------------------");
		token = CommonUtils.generateToken(generateTokenRequest.getApplicationId(), generateTokenRequest.getPassword());
		logger.info("------------------End Generating token {} ---------------------");
		if(! CommonUtils.isObjectNullOrEmpty(token)) {
			generateTokenRequest.setToken(token);
			logger.info("--------------------- Saving... Generated token {} ----------------------");	
			saveToken(generateTokenRequest);
				logger.info("-------------------- Successfully saved Generated token {} ------------------");
			}	
		logger.info("=================Exist from  getToken() {} ======================");
		return token;
	}
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
	
	@Override
	public String checkTokenExpiration(String tokenString) {
		logger.info("=================Enter in checkTokenExpiration() {} ====================== ");
		
		System.out.println(tokenExpireTime);
		TokenDetail tokenDetail =tokenRepository.getTokenByApplicationId(tokenString , tokenExpireTime);
		if(CommonUtils.isObjectNullOrEmpty(tokenDetail)) {
			logger.info("-------------------token is expired . Start saving... checkTokenExpiration() {} ------------------------");
			tokenRepository.updateTokenAsExpired(tokenString);
			logger.info("-------------------End Successfully  update expired token... checkTokenExpiration() {} ------------------------");
			return null;
		}
		logger.info("=================Exist from checkTokenExpiration() {} ====================== ");
		return tokenDetail.getToken();
	}

	@Override
	public Boolean setTokenAsExpired(GenerateTokenRequest generateTokenRequest) {
		logger.info("=================Enter in setTokenAsExpired {} ====================== ");
		
		logger.info("-------------------token is expired . Start saving... checkTokenExpiration() {} ------------------------");
		tokenRepository.inActive(generateTokenRequest.getApplicationId());
		logger.info("-------------------End Successfully  update expired token... checkTokenExpiration() {} ------------------------");
		
		logger.info("=================Exist from setTokenAsExpired () {} ====================== ");
		return true;
	}

	
}
