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
package com.kt.midas.adm.web.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.vo.CommonResult;
import com.kt.midas.adm.web.login.service.LoginSecurityService;
import com.kt.midas.adm.web.login.vo.LoginDetailVO;
/**
 * 사용자 인증(로그인/로그아웃) 처리관련 컨트롤러
 *
 */
@Controller
public class LoginController {

	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private LoginSecurityService loginSecurityService;
	
	@Value("${isReal.server}")
	private String isRealServer;
	
    /**
     * 로그인 페이지로 이동 
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "login.do")
    public String login(Model model) {
    	model.addAttribute("isReal",isRealServer);
        return "login";
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "checkSession")
    public @ResponseBody CommonResult checkSession() {
    	CommonResult result = new CommonResult();
    	
    	LoginDetailVO info = loginSecurityService.getAuthUser();
    	
    	if(info != null){
    		result.setResult(ResultEnum.LOGIN_ALREADY.result);
    	}else{
    		result.setResult(ResultEnum.LOGIN_FAIL.result);
    	}
    	
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.GET}, value = "logoutCustom")
    public String logoutSample() {
    	
    	LoginDetailVO info = loginSecurityService.getAuthUser();
    	
    	if(info != null){
    		UserDetails detail = userDetailsService.loadUserByUsername(info.getUsername());
        	
        	List<SessionInformation> list = sessionRegistry.getAllSessions(detail, false);
        	
        	for(SessionInformation session : list){
        		session.expireNow();
        	}
    	}
    	
        return "redirect:/logout";
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "logingSuccess")
    public @ResponseBody CommonResult logingSuccess() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.SUCCESS.result);
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "pwdMdf")
    public @ResponseBody CommonResult pwdMdf() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.LOGIN_PWD_MDF.result);
        return result;
    }
    
    
    @RequestMapping(method = {RequestMethod.POST}, value = "duplicated")
    public @ResponseBody CommonResult duplicated() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.DUPLICATED.result);
        result.setMessage("이미 다른 PC에서 사용중입니다.");
        return result;
    }

    /**
     * ID 값 없는 로그인 실채
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "authFailure")
    public @ResponseBody CommonResult authFailure() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.LOGIN_FAIL.result);
    	result.setMessage("ID 나 PW 가 정상적이지 않습니다.");
        return result;
    }
    
    /**
     * 패스워드 실패로 인한 로그인 실패
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "badCredentials")
    public @ResponseBody CommonResult badCredentials() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.LOGIN_FAIL.result);
    	result.setMessage("ID 나 PW 가 정상적이지 않습니다.");
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "fiveLoginError")
    public @ResponseBody CommonResult fiveLoginError() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.LOGIN_FIVE_FAIL.result);
    	result.setMessage("비밀번호 5회 실패 하였습니다.");
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "stopUserLogin")
    public @ResponseBody CommonResult stopUserLogin() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.STOP_USER_LOGIN.result);
    	result.setMessage("사용이 중단 된 사용자입니다.");
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "ipCheckError")
    public @ResponseBody CommonResult ipCheckError() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.IP_CHECK_ERROR.result);
    	result.setMessage("등록되지 않은 IP 사용자입니다."); 
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "accountExpired")
    public @ResponseBody CommonResult accountExpired() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.ACCOUNT_EXPIRED_ERROR.result);
    	result.setMessage("비밀번호 만료일이 지났습니다.");
        return result;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = "authenticationError")
    public @ResponseBody CommonResult authenticationError() {
    	CommonResult result = new CommonResult();
    	result.setResult(ResultEnum.AUTHENTICATION_ERROR.result);
    	result.setMessage("LDAP 인증에 실패 하였습니다.");
        return result;
    }
    
}
