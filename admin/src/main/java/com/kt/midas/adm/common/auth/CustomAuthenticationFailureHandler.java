/*
 *  Midas BO version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.service.LoginService;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
    private final RedirectStrategy redirectStrategy = new ForwardStratergy();
    
    @Autowired
    private LoginService<CommonManagerVO, String> loginService;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
    	if(LOG.isDebugEnabled()){
			LOG.debug("onAuthenticationFailure start");
		}
    	
        if (exception instanceof SessionAuthenticationException) {
        	if(LOG.isDebugEnabled()){
        		LOG.debug("duplicated start");
    		}
            redirectStrategy.sendRedirect(request, response, "/duplicated");
        } else if(exception instanceof UsernameNotFoundException) {
        	if(LOG.isDebugEnabled()){
        		LOG.debug("authFailure start");
    		}
            redirectStrategy.sendRedirect(request, response, "/authFailure");
        } else if(exception instanceof BadCredentialsException){
        	if(LOG.isDebugEnabled()){
        		LOG.debug("bad password start");
    		}
        	
        	String username = request.getParameter("login_id");
        	
        	CommonManagerVO commonVO = new CommonManagerVO();
            commonVO.setUserId(username);
        	
        	loginService.updateLoginHist(commonVO);
        	
        	redirectStrategy.sendRedirect(request, response, "/badCredentials");
        }else if(exception instanceof AccountExpiredException){
        	if(LOG.isDebugEnabled()){
        		LOG.debug("account expired start");
    		}
        	redirectStrategy.sendRedirect(request, response, "/accountExpired");
        }else if(exception instanceof AuthenticationServiceException){
        	if(LOG.isDebugEnabled()){
        		LOG.debug("authentication service exception start");
    		}
        	redirectStrategy.sendRedirect(request, response, "/authenticationError");
        }
    }

}
