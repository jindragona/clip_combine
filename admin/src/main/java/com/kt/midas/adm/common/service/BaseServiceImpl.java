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
package com.kt.midas.adm.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.login.service.LoginSecurityService;

public class BaseServiceImpl {

	@Autowired
    private LoginSecurityService loginSecurityService;
	
	protected UserInfoVO getUserName() {
		UserInfoVO info = null;
        if (loginSecurityService.getAuthUser() != null) {
        	info = new UserInfoVO();
        	info.setUserId(loginSecurityService.getAuthUser().getUsername());
        	info.setUserName(loginSecurityService.getAuthUser().getManagerName());
        }
        return info;
    }
}
