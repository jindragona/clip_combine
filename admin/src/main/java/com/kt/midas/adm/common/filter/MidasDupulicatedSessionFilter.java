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
package com.kt.midas.adm.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.kt.midas.adm.common.util.CommonUtils;

public class MidasDupulicatedSessionFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(MidasDupulicatedSessionFilter.class);
	
    private final SessionRegistry sessionRegistry;
    private final UserDetailsService userDetailsService;
    
    public MidasDupulicatedSessionFilter(SessionRegistry sessionRegistry, UserDetailsService userDetailsService) {
        this.sessionRegistry = sessionRegistry;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws SessionAuthenticationException,
            ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            String userid = CommonUtils.fixNull(httpServletRequest.getParameter("login_id"));
            String pass = CommonUtils.fixNull(httpServletRequest.getParameter("login_pass"));
            String ignoreDuplicated = CommonUtils.fixNull(httpServletRequest.getParameter("ignoreDuplicated"));

            if (!"".equals(userid) && !"".equals(pass)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userid);

                if(userDetails != null){
                	
                	try{
                		String encPwd = pass;
                		
                		if(encPwd.equals(userDetails.getPassword())){
                			List<SessionInformation> list = sessionRegistry.getAllSessions(userDetails, false);
                			
                			if (list.size() > 0 && "Y".equals(ignoreDuplicated)) {
                                for (SessionInformation sessionInformation : list) {
                                    sessionInformation.expireNow();
                                }
                            }
                		}
                	}catch (Exception e) {
                		if(LOG.isErrorEnabled()){
                			LOG.error("MidasDupulicatedSessionFilter error", e); 
            			}
					}
                }
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

}
