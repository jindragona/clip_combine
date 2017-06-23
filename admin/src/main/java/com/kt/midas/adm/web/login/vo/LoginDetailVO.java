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
package com.kt.midas.adm.web.login.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kt.midas.adm.web.menu.vo.MenuTreeVO;

@SuppressWarnings("serial")
public class LoginDetailVO extends User {
	private String managerName;
	private String userType;
	private String jsonStr;
	private String pageUri;
	private String upperMenuId;
	private String menuId;
	private String pageNav;
	private String logingDt;
    private String lastLoginDtt;
    private String userNameTemp;
    private String deptName;
    private String agencyName;
    private String mobile;
    private String email;
    private int userseq;
	private List<MenuTreeVO> authMenuList = new ArrayList<MenuTreeVO>();
	
	public LoginDetailVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<MenuTreeVO> getAuthMenuList() {
		return authMenuList;
	}

	public void setAuthMenuList(List<MenuTreeVO> authMenuList) {
		this.authMenuList = authMenuList;
	}

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPageNav() {
		return pageNav;
	}

	public void setPageNav(String pageNav) {
		this.pageNav = pageNav;
	}

	public String getLogingDt() {
		return logingDt;
	}

	public void setLogingDt(String logingDt) {
		this.logingDt = logingDt;
	}
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getLastLoginDtt() {
		return lastLoginDtt;
	}

	public void setLastLoginDtt(String lastLoginDtt) {
		this.lastLoginDtt = lastLoginDtt;
	}

	public String getUserNameTemp() {
		String username = getUsername();
		
		String tempUserId = "";
		StringBuilder sb = new StringBuilder();
		
		if(username.length() > 3){
			tempUserId = username.substring(0, username.length()-3);
			sb.append(tempUserId);
			sb.append("***");
			userNameTemp = sb.toString();
		}
		
		return userNameTemp;
	}

	public void setUserNameTemp(String userNameTemp) {
		this.userNameTemp = userNameTemp;
	}

	public int getUserseq() {
		return userseq;
	}

	public void setUserseq(int userseq) {
		this.userseq = userseq;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
