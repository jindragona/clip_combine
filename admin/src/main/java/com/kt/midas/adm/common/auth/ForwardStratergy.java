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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.RedirectStrategy;

/**
 * Performs a forward to the supplied URL.
 * 
 * @author kh74.jeong
 *
 */
public class ForwardStratergy implements RedirectStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(ForwardStratergy.class);
			
	/* (non-Javadoc)
	 * @see org.springframework.security.web.RedirectStrategy#sendRedirect(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
            String url) throws IOException {
         
        if(LOG.isDebugEnabled()){
        	LOG.debug(">> in forward URL : [{}]", url);
        }

        try {
        	request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			throw new IOException(e);
		}
    }
}