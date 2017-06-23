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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.RoleEnum;
import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;
import com.kt.midas.adm.web.user.service.UserServiceImpl;

@Service
public class LoginDetailService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Autowired
    private LoginService<CommonManagerVO, String> loginService;

    @Override
    public LoginDetailVO loadUserByUsername(String username) throws UsernameNotFoundException {
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    	LoginDetailVO userDetails = null;
        CommonManagerVO commonManager = loginService.selectByLoginId(username);
        
        if(commonManager == null){
        	return userDetails;
        }
        
        String password = commonManager.getLoginPass();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        if(commonManager.getUserType().equals("SA")){
        	authorities.add(new SimpleGrantedAuthority(RoleEnum.ROLE_SUPER.role));        	
        }else{
        	authorities.add(new SimpleGrantedAuthority(RoleEnum.ROLE_ADMIN.role));
        }
        
        userDetails = new LoginDetailVO(username, password, authorities);
        String name = "";
        
        try{
        	name = commonManager.getUserName();
        	userDetails.setManagerName(name);
        }catch (Exception e) {
        	if(LOG.isErrorEnabled()){
    			LOG.error("LoginDetailService loadUserByUsername error", e);
			}
        	userDetails.setManagerName(commonManager.getUserName());
		}        
        
        userDetails.setUserType(commonManager.getUserType());
        userDetails.setAuthMenuList(loginService.selectAuthMenuList(username));
        userDetails.setLogingDt(sdf.format(cal.getTime()));
        userDetails.setLastLoginDtt(commonManager.getLastLoginDt());
        userDetails.setUserseq(commonManager.getUserSeq());
        return userDetails;
    }

    public LoginDetailVO createUserByUsername(Map<String, Object> map, String username, String pwd){
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    	
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new SimpleGrantedAuthority(RoleEnum.ROLE_ADMIN.role));
    	
    	LoginDetailVO userDetails = new LoginDetailVO(username, pwd, authorities);
    	
    	userDetails.setUserType("SA");
    	userDetails.setAuthMenuList(loginService.selectAuthMenuList(username));
    	userDetails.setLogingDt(sdf.format(cal.getTime()));
    	
    	String managerName = (String)map.get("userName");
    	String mobile = (String)map.get("mobile");
        
    	LOG.error("managerName : [{}]", managerName);
    	LOG.error("mobile : [{}]", mobile);
    	
    	userDetails.setManagerName(managerName);
    	userDetails.setMobile(mobile);
    	
		String deptName = (String)map.get("deptName");
		String agencyName = (String)map.get("agencyName");
		String email = (String)map.get("email");
		
		userDetails.setDeptName(deptName);
		userDetails.setAgencyName(agencyName);
		userDetails.setEmail(email);
		userDetails.setAuthMenuList(loginService.selectAuthMenuList(username));
		userDetails.setLogingDt(sdf.format(cal.getTime()));
    	
    	return userDetails;
    }
}
