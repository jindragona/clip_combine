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
package com.kt.midas.adm.web.user.vo;

/**
 *
 * @author hwang
 */
public class UserTO{
	private int userSeq = 0;
	private String mode;
	private String userId;
	private String userName;
	private String userPwd;
	private String phoneNum;
	private String contNum;
	private String userType;
	private String hofcName;
	private String deptName;
	private String teamName;
	private String position;
	private String insDt;
	private String insNum;
	private String pwdMdfDt;
	private String lastLoginDt;
	private String pwdErrCnt;
	private String insUser;
	private String insUserName;
	private String userOldPwd;
	private String userNewPwd;
	private String encUserOldPwd;
	private String encUserNewPwd;
	private String email;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getInsDt() {
		return insDt;
	}

	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}

	public String getInsNum() {
		return insNum;
	}

	public void setInsNum(String insNum) {
		this.insNum = insNum;
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

	public String getPwdErrCnt() {
		return pwdErrCnt;
	}

	public void setPwdErrCnt(String pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}

	public String getHofcName() {
		return hofcName;
	}

	public void setHofcName(String hofcName) {
		this.hofcName = hofcName;
	}

	public String getInsUserName() {
		return insUserName;
	}

	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}

	public String getInsUser() {
		return insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}

	public String getUserOldPwd() {
		return userOldPwd;
	}

	public void setUserOldPwd(String userOldPwd) {
		this.userOldPwd = userOldPwd;
	}

	public String getUserNewPwd() {
		return userNewPwd;
	}

	public void setUserNewPwd(String userNewPwd) {
		this.userNewPwd = userNewPwd;
	}

	public String getEncUserOldPwd() {
		return encUserOldPwd;
	}

	public void setEncUserOldPwd(String encUserOldPwd) {
		this.encUserOldPwd = encUserOldPwd;
	}

	public String getEncUserNewPwd() {
		return encUserNewPwd;
	}

	public void setEncUserNewPwd(String encUserNewPwd) {
		this.encUserNewPwd = encUserNewPwd;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
