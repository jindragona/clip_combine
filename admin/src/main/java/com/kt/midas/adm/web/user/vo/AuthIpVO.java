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

public class AuthIpVO {
	
	private int sysAccIpId;
	private String sysAccIpNm;
    private String ipType;                                        
    private String ipAdr ;
    private String useYn;
    private String inpDtt;
    private String inprId;
    private String inprNm;
    private String inprNmTemp;
    private String useYnName;
    private String ipTypeName;
    private String addr1;
    private String addr2;
    private String addr3;
    private String addr4;
    private String addr5;
    private String addr6;
    private String addr7;
    private String addr8;

	public int getSysAccIpId() {
		return sysAccIpId;
	}

	public void setSysAccIpId(int sysAccIpId) {
		this.sysAccIpId = sysAccIpId;
	}

	public String getSysAccIpNm() {
		return sysAccIpNm;
	}

	public void setSysAccIpNm(String sysAccIpNm) {
		this.sysAccIpNm = sysAccIpNm;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public String getIpAdr() {
		return ipAdr;
	}

	public void setIpAdr(String ipAdr) {
		this.ipAdr = ipAdr;
	}

	public String getInpDtt() {
		return inpDtt;
	}

	public void setInpDtt(String inpDtt) {
		this.inpDtt = inpDtt;
	}

	public String getInprId() {
		return inprId;
	}

	public void setInprId(String inprId) {
		this.inprId = inprId;
	}

	public String getInprNm() {
		return inprNm;
	}

	public void setInprNm(String inprNm) {
		this.inprNm = inprNm;
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

	public String getIpTypeName() {
		return ipTypeName;
	}

	public void setIpTypeName(String ipTypeName) {
		this.ipTypeName = ipTypeName;
	}

	public String getInprNmTemp() {
		String tempUserName = "";
		StringBuilder sb = new StringBuilder();
		
		if(inprNm != null && inprNm.length() > 2){
			tempUserName = inprNm.substring(0, inprNm.length() -1);
			sb.append(tempUserName);
			sb.append("*");
			inprNmTemp = sb.toString();
		}else if(inprNm != null && inprNm.length() == 2){
			tempUserName = inprNm.substring(0, 1);
			sb.append(tempUserName);
			sb.append("*");
			inprNmTemp = sb.toString();
		}
		return inprNmTemp;
	}

	public void setInprNmTemp(String inprNmTemp) {
		this.inprNmTemp = inprNmTemp;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getAddr4() {
		return addr4;
	}

	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}

	public String getAddr5() {
		return addr5;
	}

	public void setAddr5(String addr5) {
		this.addr5 = addr5;
	}

	public String getAddr6() {
		return addr6;
	}

	public void setAddr6(String addr6) {
		this.addr6 = addr6;
	}

	public String getAddr7() {
		return addr7;
	}

	public void setAddr7(String addr7) {
		this.addr7 = addr7;
	}

	public String getAddr8() {
		return addr8;
	}

	public void setAddr8(String addr8) {
		this.addr8 = addr8;
	}

}
