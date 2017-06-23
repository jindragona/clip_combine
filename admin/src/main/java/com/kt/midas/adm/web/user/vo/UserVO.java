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

public class UserVO {
	
	private int userSeq;
	private String userId;
	private String userName;
	private String userPwd;
	private String phoneNum;
	private String contNum;
	private String userType;
	private String hofcName;
	private String teamName;
	private String position;
	private String insDt;
	private String insUser;
	private String insUserName;
	private String insUserNameTemp;
	private String pwdMdfDt;
	private String lastLoginDt;
	private String pwdErrCnt;
	private String userNameTemp;
	private String userIdTemp;
	private String phoneNumTemp;
	private String cellphone1;
	private String cellphone2;
	private String cellphone3;
	private String usrStus;
	private String usrStusName;
	private String phone1;
	private String phone2;
	private String phone3;
	
	
	public String getUserNameTemp() {
		String tempUserName = "";
		StringBuilder sb = new StringBuilder();
		
		if(userName != null && userName.length() > 2){
			tempUserName = userName.substring(0, userName.length()-1);
			sb.append(tempUserName);
			sb.append("*");
			userNameTemp = sb.toString();
		}else if(userName != null && userName.length() == 2){
			tempUserName = userName.substring(0, 1);
			sb.append(tempUserName);
			sb.append("*");
			userNameTemp = sb.toString();
		}
		return userNameTemp;
	}
	public void setUserNameTemp(String userNameTemp) {
		this.userNameTemp = userNameTemp;
	}
	public String getUserIdTemp() {
		String tempUserId = "";
		StringBuilder sb = new StringBuilder();
		
		if(userId.length() > 3){
			tempUserId = userId.substring(0, userId.length()-3);
			sb.append(tempUserId);
			sb.append("***");
			userIdTemp = sb.toString();
		}
		
		return userIdTemp;
	}
	public void setUserIdTemp(String userIdTemp) {
		this.userIdTemp = userIdTemp;
	}
	public String getPhoneNumTemp() {
		String phone1 = "";
		String phone2 = "";
		
		if(phoneNum != null && !phoneNum.equals("") && phoneNum.indexOf("-") != -1){
			String[] phone = phoneNum.split("-");
			
			if(phone[1].length() > 3){
				phone1 = phone[1].substring(0, 2) + "**";
			}
			
			if(phone[2].length() > 1){
				phone2 = "*"+phone[2].substring(1, phone[2].length());
			}
			
			phoneNumTemp = phone[0] +"-"+ phone1 +"-"+ phone2;
		}
		
		return phoneNumTemp;
	}
	public void setPhoneNumTemp(String phoneNumTemp) {
		this.phoneNumTemp = phoneNumTemp;
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
	public String getCellphone1() {
		if(contNum != null && !contNum.equals("") && contNum.indexOf("-") != -1){
			String[] cellPhoneTemp = contNum.split("-");
			cellphone1 = cellPhoneTemp[0];
		}
		return cellphone1;
	}
	public void setCellphone1(String cellphone1) {
		this.cellphone1 = cellphone1;
	}
	public String getCellphone2() {
		if(contNum != null && !contNum.equals("") && contNum.indexOf("-") != -1){
			String[] cellPhoneTemp = contNum.split("-");
			if(cellPhoneTemp.length == 1){
				cellphone2 = "";
			}else{
				cellphone2 = cellPhoneTemp[1];
			}
		}
		return cellphone2;
	}
	public void setCellphone2(String cellphone2) {
		this.cellphone2 = cellphone2;
	}
	public String getCellphone3() {
		if(contNum != null && !contNum.equals("") && contNum.indexOf("-") != -1){
			String[] cellPhoneTemp = contNum.split("-");
			if(cellPhoneTemp.length == 1){
				cellphone3 = "";
			}else{
				cellphone3 = cellPhoneTemp[2];					
			}
		}
		return cellphone3;
	}
	public void setCellphone3(String cellphone3) {
		this.cellphone3 = cellphone3;
	}
	public String getHofcName() {
		return hofcName;
	}
	public void setHofcName(String hofcName) {
		this.hofcName = hofcName;
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
	public String getUsrStus() {
		return usrStus;
	}
	public void setUsrStus(String usrStus) {
		this.usrStus = usrStus;
	}
	public String getUsrStusName() {
		return usrStusName;
	}
	public void setUsrStusName(String usrStusName) {
		this.usrStusName = usrStusName;
	}
	public String getPhone1() {
		if(phoneNum != null && !phoneNum.equals("") && phoneNum.indexOf("-") != -1){
			String[] phoneTemp = phoneNum.split("-");
			phone1 = phoneTemp[0];
		}
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		if(phoneNum != null && !phoneNum.equals("") && phoneNum.indexOf("-") != -1){
			String[] phoneTemp = phoneNum.split("-");
			if(phoneTemp.length == 1){
				phone2 = "";
			}else{
				phone2 = phoneTemp[1];
			}
		}
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		if(phoneNum != null && !phoneNum.equals("") && phoneNum.indexOf("-") != -1){
			String[] phoneTemp = phoneNum.split("-");
			if(phoneTemp.length == 1){
				phone3 = "";
			}else{
				phone3 = phoneTemp[2];					
			}
		}
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
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
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
}
