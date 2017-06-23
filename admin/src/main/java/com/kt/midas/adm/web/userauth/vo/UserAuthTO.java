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
package com.kt.midas.adm.web.userauth.vo;

/**
 *
 * @author hwang
 */
public class UserAuthTO{
	private String mode;
	private String rolName;
	private String useYn;
	private String rolDesc;
	private String insUser;
	private String insUserName;
	private int rolId;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRolName() {
		return rolName;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRolDesc() {
		return rolDesc;
	}
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getInsUserName() {
		return insUserName;
	}
	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	
}
