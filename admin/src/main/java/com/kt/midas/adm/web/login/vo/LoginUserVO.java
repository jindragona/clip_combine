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
package com.kt.midas.adm.web.login.vo;

public class LoginUserVO {

	private String userId;
	private String userName;
	private String phoneNum;
	private String contNum;
	private String hofcName;
	private String teamName;
	private String insDt;
	private String insUser;
	private String pwdMdfDt;
	private String lastLoginDt;
	private int pwdErrCnt;
	private String userType;
	private String insUserName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getContNum() {
		return contNum;
	}
	public void setContNum(String contNum) {
		this.contNum = contNum;
	}
	public String getHofcName() {
		return hofcName;
	}
	public void setHofcName(String hofcName) {
		this.hofcName = hofcName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getPwdMdfDt() {
		return pwdMdfDt;
	}
	public void setPwdMdfDt(String pwdMdfDt) {
		this.pwdMdfDt = pwdMdfDt;
	}
	public String getLastLoginDt() {
		return lastLoginDt;
	}
	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}
	public int getPwdErrCnt() {
		return pwdErrCnt;
	}
	public void setPwdErrCnt(int pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getInsUserName() {
		return insUserName;
	}
	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}
	
}
