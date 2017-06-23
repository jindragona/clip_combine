/*
 *  Midas BO version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actuserAgentl or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.auth;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.service.LoginService;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;
import com.kt.midas.adm.web.main.service.MainService;
import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.user.service.UserService;
import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserTO;

/**
 *
 * @author seulee
 * refactor wclee
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new ForwardStratergy();
    
    private RequestCache requestChche = new HttpSessionRequestCache();
    
    private String targetUrlParameter;
    private String defaultUrl;
    private boolean useReferer = false;
    
    private final String[] IP_HEADER_CANDIDATES = { 
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
		    "REMOTE_ADDR" };
        
    @Autowired
    private LoginService<CommonManagerVO, String> loginService;
    
    @Autowired
    private MainService mainService;
    
    @Autowired
    private UserService userService;
    
    @Value("${pwd.mdf.date}")
    private String pwdMdfDate;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	customClearAuthenticationAttributes(request);
    	
//    	Calendar cal = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    	
		String userAgent = request.getHeader("User-Agent").toLowerCase();
        
        String mobileGubun = mobileGubun(userAgent);
        String browserGubun = browserGubun(userAgent);
        
        String username = request.getParameter("login_id");
        String pwd = request.getParameter("login_pass");
        String remote = request.getRemoteAddr();
//        String authNo = request.getParameter("auth_no");
        CommonManagerVO commonVO = new CommonManagerVO();
        commonVO.setUserId(username);
        commonVO.setAccIp(remote);
        commonVO.setAccType(mobileGubun);
        commonVO.setAccBrw(browserGubun);
        
		CommonManagerVO vo = loginService.selectByLoginId(username);
		LoginDetailVO loginVO = (LoginDetailVO) authentication.getPrincipal();
		
		String ip = getHeaderIp(request);
		
		if(vo == null && loginVO != null){
			UserTO to = new UserTO();
			to.setDeptName(loginVO.getDeptName());
			to.setHofcName(loginVO.getAgencyName());
			to.setUserId(loginVO.getUsername());
			to.setUserName(loginVO.getManagerName());
			to.setPhoneNum(loginVO.getMobile());
			to.setInsUser("SYS");
			cal2.add(Calendar.DATE, Integer.parseInt(pwdMdfDate));
			String pwdMdfDt = sdf.format(cal2.getTime());
    		to.setPwdMdfDt(pwdMdfDt);
    		to.setInsUserName("시스템관리자");
    		to.setUserPwd(pwd);
    		to.setUserType("SA");
    		to.setEmail(loginVO.getEmail());
			userService.addRow(to);
			
			vo = new CommonManagerVO();
			vo.setPwdErrCnt(0);
			vo.setUserId(username);
			vo.setUsrStus("A");
			vo.setPwdMdfDt(pwdMdfDt);
			
			UserAuthIpTO ipTo = new UserAuthIpTO();
			ipTo.setUserId(username);
			ipTo.setSysAccIpNm("시스템 IP");
			ipTo.setIpType(1);
			ipTo.setIpAdr(ip);
			ipTo.setUseYn("Y");
			ipTo.setInsUser("SYS");
			ipTo.setInsUSerName("시스템관리자");
			
			userService.userIpAuthAddLdap(ipTo);
		}
		
		if(vo != null){
			if(vo.getPwdErrCnt() >= 5){
				userService.stopUser(username);
				redirectStrategy.sendRedirect(request, response, "/fiveLoginError");
			}else if(vo.getUsrStus().equals("T")){
				// 사용 중단 된 사용자의 의한 로그인 시 로그인 시키지 않는 정책
				redirectStrategy.sendRedirect(request, response, "/stopUserLogin");
			}else{
				if(LOG.isInfoEnabled()){
					LOG.info("return this ip"+ip);
				}
				
//				if(!isAuthoriezedIp(ip, username)){
//					redirectStrategy.sendRedirect(request, response, "/ipCheckError");
//				}else{
//					// 로그인 이력 저장
//			        loginService.insertLoginHist(commonVO);
//			        
//			        // 마지막 로그인 시간 저장
//			        loginService.updateByPK(commonVO);
//			        
//			        //인증번호 초기화
//			        AuthVO authVO = new AuthVO();
//			        authVO.setAuthStat("N");
//			        mainService.updateAuthNo(authVO);
//			        
//			        redirectStrategy.sendRedirect(request, response, "/logingSuccess");
//				}
				// 로그인 이력 저장
		        loginService.insertLoginHist(commonVO);
		        
		        // 마지막 로그인 시간 저장
		        loginService.updateByPK(commonVO);
		        
		        //인증번호 초기화
		        AuthVO authVO = new AuthVO();
		        authVO.setAuthStat("N");
		        mainService.updateAuthNo(authVO);
		        
		        redirectStrategy.sendRedirect(request, response, "/logingSuccess");
			}
		}else{
			redirectStrategy.sendRedirect(request, response, "/authenticationError");
		}
    }
    
    public String getHeaderIp(HttpServletRequest request){
    	String ip = "";
    	
    	for (String header : IP_HEADER_CANDIDATES) {
            ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
    	
    	return request.getRemoteAddr();
    }
    
    protected String browserGubun(String userAgent){
    	String browserGubun = "";
    	
    	if (userAgent != null) {
    		if (userAgent.indexOf("trident") > -1) {
    			browserGubun = "IE";
    		} else if (userAgent.indexOf("chrome") > -1) {
    			browserGubun = "CHROME";
    		} else if (userAgent.indexOf("opera") > -1) {
    			browserGubun = "OPERA";
    		} else if (userAgent.indexOf("safari") > -1) {
    			browserGubun = "SAFARI";
    		} else {
    			browserGubun = "FIREFOX";
    		}
    	}
    	return browserGubun;
    }
    
    protected String mobileGubun(String userAgent){
    	String mobileGubun = "";
    	String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE", "Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
        
        int j = -1;
        for(int i=0;i<mobileos.length;i++){
             j=userAgent.indexOf(mobileos[i]);
             if(j>-1){
            	 break;
             }
        }
        
        if(j > -1){
        	mobileGubun = "MBL"; // 모바일
        }else{
        	mobileGubun = "PC"; // PC
        }
    	
    	return mobileGubun;
    }

    protected String getRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if (savedRequest != null) {
                session.setAttribute("targetURL", savedRequest.getRedirectUrl());
                return savedRequest.getRedirectUrl();
            }
        }
        return request.getContextPath() + "/";
    }

    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        logger.debug("@[[start CustomAuthenticationSuccessHandler]]@");
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     */
    protected String determineTargetUrl(Authentication authentication) {
        return "/office/main";
    }

    protected void customClearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

	public RequestCache getRequestChche() {
		return requestChche;
	}

	public void setRequestChche(RequestCache requestChche) {
		this.requestChche = requestChche;
	}

	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}

	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public boolean isUseReferer() {
		return useReferer;
	}

	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}
    
	public boolean isAuthoriezedIp(String fromIp, String userId) {
		
		boolean isTrue = false;
		
		String strIpAdr = "";
		String strIpStart = "";
		String strIpEnd = "";
		String[] arrIpAdr = null;
		
		UserTO to = new UserTO();
		to.setUserId(userId);
		
		List<AuthIpVO> ipList = userService.getAuthIpList(to);
		
		for(AuthIpVO ipVo : ipList) {
			
			strIpAdr = ipVo.getIpAdr();
			arrIpAdr = strIpAdr.split("~");

			strIpStart = arrIpAdr[0];
			strIpEnd = "";
			
			
			if(arrIpAdr.length > 1) {
				
				strIpEnd   = arrIpAdr[1];
				
				BigDecimal a = ipToLongnum(fromIp);
				BigDecimal b = ipToLongnum(strIpStart);
				BigDecimal c = ipToLongnum(strIpEnd);
				
				if(a.compareTo(b) >= 0 	&& a.compareTo(c) <= 0) {
					isTrue = true;
				}
			} else if(fromIp.equals(arrIpAdr[0])) {
				isTrue = true;
			}
			
			if(isTrue){
				return isTrue;
			}
		}
		
		return false;
	}
	
	private BigDecimal ipToLongnum(String ipStr) {
		
		BigDecimal ipLongNum = new BigDecimal("255255255255");
		
		String strFrag1 = "";
		String strFrag2 = "";
		String strFrag3 = "";
		String strFrag4 = "";
		
		String[] ipFrag = ipStr.split("\\.");
		
		if(ipFrag.length > 3) {
			
			try {
				
				strFrag1 = ipFrag[0];
				strFrag2 = String.format("%03d", Integer.parseInt(ipFrag[1], 10));
				strFrag3 = String.format("%03d", Integer.parseInt(ipFrag[2], 10));
				strFrag4 = String.format("%03d", Integer.parseInt(ipFrag[3], 10));
				
				ipLongNum = new BigDecimal(strFrag1 + strFrag2 + strFrag3 + strFrag4);
				
			} catch (NumberFormatException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("ipToLongnum", e);
				}
			}
		}
		
		return ipLongNum;
	}
	
	
}
