package com.capitaworld.service.loans.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.capitaworld.service.auth.client.AuthClient;
import com.capitaworld.service.auth.model.AuthClientResponse;
import com.capitaworld.service.auth.model.AuthRequest;
import com.capitaworld.service.auth.utils.AuthCredentialUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private Environment environment;

	public static final String AUTH_URL = "capitaworld.service.auth.url";
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		logger.info("Loan Request URI------------------------------> " + requestURI);
		if(CommonUtils.urlsBrforeLogin.contains(requestURI)){
			return true;
		}
		
		
		
		// for only client call
		String reqAuth = request.getHeader(AuthCredentialUtils.REQUEST_HEADER_AUTHENTICATE);
		logger.info("URI----->"+requestURI);
		logger.info("Client Call----------------->" + reqAuth);
		if (reqAuth != null && reqAuth != "") {
			if ("true".equals(reqAuth)) {
				return true;
			}
		}

		String accessToken = request.getHeader(AuthCredentialUtils.REQUEST_HEADER_ACCESS_TOKEN);
		String refreshToken = request.getHeader(AuthCredentialUtils.REQUEST_HEADER_REFRESH_TOKEN);
		String username = request.getHeader(AuthCredentialUtils.REQUEST_HEADER_USERNAME);
		String loginToken = request.getHeader(AuthCredentialUtils.REQUEST_HEADER_LOGIN_TOKEN);

		if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(username) || StringUtils.isEmpty(refreshToken)
				|| StringUtils.isEmpty(loginToken)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			logger.warn("Access Token ----------> " + accessToken);
			logger.warn("UserName --------------> " + username);
			logger.warn("Refresh Token  --------> " + refreshToken);
			logger.warn("Login Token -----------> " + loginToken);
			logger.warn("Bad Request, If any one of from the above four is null or empty");
			return false;
		}

		AuthClient client = new AuthClient(environment.getRequiredProperty(AUTH_URL));
		AuthRequest authRequest = new AuthRequest(username, accessToken, refreshToken);
		authRequest.setLoginToken(Integer.valueOf(loginToken));
		AuthClientResponse authResponse = client.isAccessTokenValidOrNot(authRequest);
		if (!authResponse.isAuthenticate()) {
			logger.warn("Unauthorized Request, Access token expire or invalid");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		request.setAttribute(CommonUtils.USER_ID, authResponse.getUserId());
		request.setAttribute(CommonUtils.USER_TYPE, authResponse.getUserType().intValue());
		request.setAttribute(CommonUtils.USER_ORG_ID, authResponse.getUserOrgId());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
