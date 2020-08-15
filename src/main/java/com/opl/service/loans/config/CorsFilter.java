package com.opl.service.loans.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "-1");
		response.setHeader("Access-Control-Allow-Headers",
			    "Content-Type, Access-Control-Allow-Headers, X-Requested-With,tk_ac,ur_cu,tk_rc,tk_lg,req_auth,*");
		response.setHeader("X-Frame-Options","ALLOW-FROM *.capitaworld.com");
		HttpServletRequest request = ((HttpServletRequest) req);
		String origin = request.getHeader("origin");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			if((origin.contains("http://localhost:") || origin.contains("https://localhost:"))){
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig filterConfig) {
		// Do nothing because of X and Y.
	}

	public void destroy() {
		// Do nothing because of X and Y.
	}

}
