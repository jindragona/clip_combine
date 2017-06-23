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
package com.kt.midas.adm.web.code.vo;

public class CodeVO {

	private int codeOrd;
	private String codeId;
	private String codeName;
	private String codeGrpId;
	private String useYn;
	private String useYnName;
	private String insDt;
	private String insUser;
	private String insUserName;
	private String insUserNameTemp;
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeGrpId() {
		return codeGrpId;
	}
	public void setCodeGrpId(String codeGrpId) {
		this.codeGrpId = codeGrpId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnName() {
		return useYnName;
	}
	public void setUseYnName(String useYnName) {
		this.useYnName = useYnName;
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
	public int getCodeOrd() {
		return codeOrd;
	}
	public void setCodeOrd(int codeOrd) {
		this.codeOrd = codeOrd;
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
