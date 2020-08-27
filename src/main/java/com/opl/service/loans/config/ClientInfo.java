package com.opl.service.loans.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author akash.jain
 */

@Component
public class ClientInfo {
	
	private static final Logger logger =LoggerFactory.getLogger(ClientInfo.class.getName());
	
	private static final String UNKNOWN="unknown";
	private static final String USER_AGENT="User-Agent";
	private static final String REFERER="referer";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	 public Map<String ,Object> printClientInfo(HttpServletRequest request) throws UnknownHostException {
		 /**Map<String, Object> result = new HashMap<String, Object>();
	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            result.put(key, value);
	            System.out.println(result);
	        }

	        return result;*/
	        final String referer = getReferer(request);
	        final String fullURL = getFullURL(request);
	        final String clientIpAddr = getClientIpAddr(request);
	        final String clientOS = getClientOS(request);
	        final String clientBrowser = getClientBrowser(request);
	        final String userAgent = getUserAgent(request);
	        final String exactDateTime = sdf.format(new Date());
	        
	        logger.info("\n User Agent==>{} \n Operating System==>{} \n Browser Name==>{} \n IP Address==>{} \n Full URL==>{} \n Referrer==>{} \n ExactDateTime==>{}",
	        		userAgent, clientOS, clientBrowser ,clientIpAddr ,fullURL ,referer , exactDateTime);
	        Map<String, Object> userDetail = new HashMap<String ,Object>();
	        userDetail.put("Referer", referer);
	        userDetail.put("URL", fullURL);
	        userDetail.put("IpAddress", clientIpAddr);
	        userDetail.put("OS", clientOS);
	        userDetail.put("Browser", clientBrowser);
	        userDetail.put("UserAgent", userAgent);
	        userDetail.put("ExactTime", exactDateTime);
			return userDetail;
	        
	    }

	    public String getReferer(HttpServletRequest request) {
	        return request.getHeader(REFERER);
	        /**return referer;*/
	    }

	    public String getFullURL(HttpServletRequest request) {
	        final StringBuffer requestURL = request.getRequestURL();
	        final String queryString = request.getQueryString();

	        return queryString == null ? requestURL.toString() : requestURL.append('?').append(queryString).toString();
	    }

	    public String getClientIpAddr(HttpServletRequest request) throws UnknownHostException {
	        String ip = request.getHeader("X-Forwarded-For");
	        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
	        	ip = request.getHeader("HTTP_CLIENT_IP");
	        }
	        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
	        	ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
	        	ip = request.getRemoteAddr();
	        }
	        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();
            }
	        return ip;
	    }

	    public String getClientOS(HttpServletRequest request) {
	        final String browserDetails = request.getHeader(USER_AGENT);

	        //=================OS=======================
	        final String lowerCaseBrowser = browserDetails.toLowerCase();
	        if (lowerCaseBrowser.contains("windows")) {
	            return "Windows";
	        } else if (lowerCaseBrowser.contains("mac")) {
	            return "Mac";
	        } else if (lowerCaseBrowser.contains("x11")) {
	            return "Unix";
	        } else if (lowerCaseBrowser.contains("android")) {
	            return "Android";
	        } else if (lowerCaseBrowser.contains("iphone")) {
	            return "IPhone";
	        } else {
	            return "UnKnown, More-Info: " + browserDetails;
	        }
	    }

	    public String getClientBrowser(HttpServletRequest request) {
	        final String browserDetails = request.getHeader(USER_AGENT);
	        final String user = browserDetails.toLowerCase();

	        String browser = "";

	        //===============Browser===========================
	        if (user.contains("msie")) {
	            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
	            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
	        } else if (user.contains("safari") && user.contains("version")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
	                    "/")[0] + "-" + (browserDetails.substring(
	                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
	        } else if (user.contains("opr") || user.contains("opera")) {
	            if (user.contains("opera"))
	                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
	                        "/")[0] + "-" + (browserDetails.substring(
	                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
	            else if (user.contains("opr"))
	                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
	                                                                                                           "-")).replace(
	                        "OPR", "Opera");
	        } else if (user.contains("chrome")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf(
	                "mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf(
	                "mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
	            /**browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");*/
	            browser = "Netscape-?";

	        } else if (user.contains("firefox")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	        } else if (user.contains("rv")) {
	            browser = "IE";
	        } else {
	            browser = "UnKnown, More-Info: " + browserDetails;
	        }

	        return browser;
	    }

	    public String getUserAgent(HttpServletRequest request) {
	        return request.getHeader(USER_AGENT);
	    }
	    
	   /** private static final String[] HEADERS_TO_TRY = {
	            "X-Forwarded-For",
	            "Proxy-Client-IP",
	            "WL-Proxy-Client-IP",
	            "HTTP_X_FORWARDED_FOR",
	            "HTTP_X_FORWARDED",
	            "HTTP_X_CLUSTER_CLIENT_IP",
	            "HTTP_CLIENT_IP",
	            "HTTP_FORWARDED_FOR",
	            "HTTP_FORWARDED",
	            "HTTP_VIA",
	            "REMOTE_ADDR"};

	    public String getClientIpAddress(HttpServletRequest request) {
	        for (String header : HEADERS_TO_TRY) {
	            String ip = request.getHeader(header);
	            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	                return ip;
	            }
	        }
	       return request.getRemoteAddr();
	    }*/
	    

}
