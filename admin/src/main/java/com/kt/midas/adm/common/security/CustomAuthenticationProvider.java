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
package com.kt.midas.adm.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kt.midas.adm.web.login.service.LoginDetailService;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	private LoginDetailService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = (String) authentication.getCredentials();
		
		LoginDetailVO vo = null;
		try{
			vo = userDetailsService.loadUserByUsername(username);
//			if(vo == null){
//				throw new UsernameNotFoundException("not exist user");
//			}
//			
//			String encPwd = pwd;
//    		
//    		if(!encPwd.equals(vo.getPassword())){
//    			throw new BadCredentialsException("not equals password");
//    		}
			
		} catch(UsernameNotFoundException e) { 
			throw new UsernameNotFoundException(e.getMessage()); 
		} catch(BadCredentialsException e) { 
			throw new BadCredentialsException(e.getMessage()); 
		} catch(Exception e) { 
			if(LOG.isErrorEnabled()){
    			LOG.error("CustomAuthenticationProvider.authenticate", e); 
			}
		}
		
		return new UsernamePasswordAuthenticationToken(vo, pwd, vo.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public LoginDetailService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(LoginDetailService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
