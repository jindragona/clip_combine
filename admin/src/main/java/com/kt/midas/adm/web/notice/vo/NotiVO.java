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
package com.kt.midas.adm.web.notice.vo;

public class NotiVO {

	private int notiId;
	private String notiName;
	private String notiContent;
	private String stime;
	private String etime;
	private String notiYn;
	private String insUser;
	private String insUserName;
	private String insUserNameTemp;
	private String insDt;
	private String usrType;
	private String usrTypeName;
	
	public int getNotiId() {
		return notiId;
	}
	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}
	public String getNotiName() {
		return notiName;
	}
	public void setNotiName(String notiName) {
		this.notiName = notiName;
	}
	public String getNotiContent() {
		return notiContent;
	}
	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getNotiYn() {
		return notiYn;
	}
	public void setNotiYn(String notiYn) {
		this.notiYn = notiYn;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getInsUserName() {
		return insUserName;
	}
	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}
	public String getUsrType() {
		return usrType;
	}
	public void setUsrType(String usrType) {
		this.usrType = usrType;
	}
	public String getUsrTypeName() {
		return usrTypeName;
	}
	public void setUsrTypeName(String usrTypeName) {
		this.usrTypeName = usrTypeName;
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
