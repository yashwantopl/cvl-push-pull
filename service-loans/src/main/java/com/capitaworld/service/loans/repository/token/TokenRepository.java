package com.capitaworld.service.loans.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.token.TokenDetail;

public interface TokenRepository extends JpaRepository<TokenDetail,Long>{

	/*@Query("select td FROM TokenDetail td WHERE td.token =:token AND td.isExpired = false AND  (time(now())- time(createdDate)) <= time( :time ) AND td.isActive = true")
	public TokenDetail getTokenByApplicationId(@Param("token") String tokenString, @Param("time") Date time);*/

	@Modifying
	@Query("UPDATE TokenDetail td SET td.isExpired = true , td.isActive = false WHERE td.applicationId =:applicationId  ") //AND isActive = false ")
	public void inActive(@Param("applicationId") Long applicationId);
	
	/*@Modifying
	@Query("UPDATE TokenDetail td SET td.isExpired = true , td.isActive = false  WHERE td.token =:token AND td.modifiedDate = now() AND  td.isActive = true  ") //AND isActive = false ")
	public void updateExpiredToken(@Param("token") String tokenString);*/
}
