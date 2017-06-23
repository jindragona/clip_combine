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

public class UserAuthVO {
	private String rolId;
	private String rolName;
	private String rolDesc;
	private String useYn;
	private String ueYnName;
	private String insDt;
	private String insUser;
	private String insUserName;
	private String insUserNameTemp;
	
	public String getRolId() {
		return rolId;
	}
	public void setRolId(String rolId) {
		this.rolId = rolId;
	}
	public String getRolName() {
		return rolName;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public String getRolDesc() {
		return rolDesc;
	}
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getInsUserName() {
		return insUserName;
	}
	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}
	public String getUeYnName() {
		return ueYnName;
	}
	public void setUeYnName(String ueYnName) {
		this.ueYnName = ueYnName;
	}
	public String getInsUserNameTemp() {
		String tempUserName = "";
		StringBuilder sb = new StringBuilder();
		
		if(insUserName != null && insUserName.length() > 2){
			tempUserName = insUserName.substring(0, insUserName.length()-1);
			sb.append(tempUserName);
			sb.append("*");
			insUserNameTemp = sb.toString();
		}else if(insUserName != null && insUserName.length() == 2){
			tempUserName = insUserName.substring(0, 1);
			sb.append(tempUserName);
			sb.append("*");
			insUserNameTemp = sb.toString();
		}
		return insUserNameTemp;
	}
	public void setInsUserNameTemp(String insUserNameTemp) {
		this.insUserNameTemp = insUserNameTemp;
	}
	
}
