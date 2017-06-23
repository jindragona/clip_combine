/*
 *  Midas version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.web.login.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.web.login.vo.LoginDetailVO;

@Service
public class LoginSecurityService {

    public LoginDetailVO getAuthUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();
        LoginDetailVO userDetails = null;

        if (authentication != null && authentication.getPrincipal() instanceof LoginDetailVO) {
            userDetails = (LoginDetailVO) authentication.getPrincipal();
        }

        return userDetails;
    }

}
