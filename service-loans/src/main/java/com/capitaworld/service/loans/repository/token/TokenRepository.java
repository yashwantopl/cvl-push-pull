package com.capitaworld.service.loans.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.token.TokenDetail;

public interface TokenRepository extends JpaRepository<TokenDetail,Long>{ 

	@Query(value = "select * from token_detail td WHERE td.token =:token AND td.is_expired = false AND td.created_date = date_sub( NOW() , INTERVAL :time MINUTE )   AND td.is_active = true", nativeQuery = true)
	public TokenDetail getTokenByApplicationId(@Param("token") String tokenString, @Param("time") Long  time);

	@Modifying
	@Query("UPDATE TokenDetail td SET td.isExpired = true , td.isActive = false , td.modifiedDate = now() WHERE td.applicationId =:applicationId AND td.isActive = true ")
	public void inActive(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Query("UPDATE TokenDetail td SET td.isExpired = true , td.isActive = false ,  td.modifiedDate = now()  WHERE td.token =:token  AND  td.isActive = true  ") //AND isActive = false ")
	public void updateTokenAsExpired(@Param("token") String tokenString);

}
