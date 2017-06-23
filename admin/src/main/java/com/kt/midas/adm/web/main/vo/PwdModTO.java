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
package com.kt.midas.adm.web.main.vo;

public class PwdModTO {

	private String userId;
	private String oldPwd;
	private String newPwd;
	private String encOldPwd;
	private String encNewPwd;
	private String pwdModDate;
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEncOldPwd() {
		return encOldPwd;
	}
	public void setEncOldPwd(String encOldPwd) {
		this.encOldPwd = encOldPwd;
	}
	public String getEncNewPwd() {
		return encNewPwd;
	}
	public void setEncNewPwd(String encNewPwd) {
		this.encNewPwd = encNewPwd;
	}
	public String getPwdModDate() {
		return pwdModDate;
	}
	public void setPwdModDate(String pwdModDate) {
		this.pwdModDate = pwdModDate;
	}
	
	
}
