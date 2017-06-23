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

public class AuthVO {
	private String userId;
	private String authNo;
	private String authPhone;
	private String authStat;
	public String getAuthStat() {
		return authStat;
	}
	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthNo() {
		return authNo;
	}
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	public String getAuthPhone() {
		return authPhone;
	}
	public void setAuthPhone(String authPhone) {
		this.authPhone = authPhone;
	}
}
