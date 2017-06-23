/*
 *  MIDAS version 1.0
 *
 *  Copyright ⓒ 2017 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.kt.midas.adm.web.login.service.LoginSecurityService;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
    private LoginSecurityService loginSecurityService;
	
	private final SessionRegistry sessionRegistry;
	private final UserDetailsService userDetailsService;

    public CustomLogoutSuccessHandler(SessionRegistry sessionRegistry, UserDetailsService userDetailsService) {
        this.sessionRegistry = sessionRegistry;
        this.userDetailsService = userDetailsService;
    }
	
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginDetailVO info = loginSecurityService.getAuthUser();
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(info.getUsername());
        
        if(userDetails != null){
        	List<SessionInformation> list = sessionRegistry.getAllSessions(userDetails, false);
        	
        	if (list.size() > 0) {
                for (SessionInformation sessionInformation : list) {
                    sessionInformation.expireNow();
                }
            }
        }
        
        super.onLogoutSuccess(request, response, authentication);
    }
}
