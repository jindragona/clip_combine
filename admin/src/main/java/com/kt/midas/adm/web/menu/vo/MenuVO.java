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
package com.kt.midas.adm.web.menu.vo;

public class MenuVO {
	
	private String menuId;
	private String menuName;
	private String upperMenuId;
	private String upperMenuName;
	private String menuLevel;
	private String menuOrder;
	private String menuType;
	private String useYn;
	private String useYnName;
	private String menuUrl;
	private String menuDesc;
	private String insDt;
	private String insUser;
	private String insUserName;
	private String insUserNameTemp;
	private String menuNav;
	private String pathName;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUpperMenuId() {
		return upperMenuId;
	}
	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
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
	public String getUseYnName() {
		return useYnName;
	}
	public void setUseYnName(String useYnName) {
		this.useYnName = useYnName;
	}
	public String getUpperMenuName() {
		return upperMenuName;
	}
	public void setUpperMenuName(String upperMenuName) {
		this.upperMenuName = upperMenuName;
	}
	public String getMenuNav() {
		return menuNav;
	}
	public void setMenuNav(String menuNav) {
		this.menuNav = menuNav;
	}
	public String getPathName() {
		if(pathName != null && !pathName.equals("")){
			pathName = pathName.replace("ROOT > ", "");			
		}
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
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
