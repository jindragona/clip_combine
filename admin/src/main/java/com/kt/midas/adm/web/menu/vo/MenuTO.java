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

/**
 *
 * @author hwang
 */
public class MenuTO{
	private String mode;
	private int menuId;
	private String menuName;
	private int upperMenuId;
	private String upperMenuName;
	private int menuLevel;
	private int menuOrder;
	private String menuType;
	private String useYn;
	private String useYnName;
	private String menuUrl;
	private String menuDesc;
	private String insDt;
	private String insUser;
	private String insUserName;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(int upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public String getUpperMenuName() {
		return upperMenuName;
	}

	public void setUpperMenuName(String upperMenuName) {
		this.upperMenuName = upperMenuName;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
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

	public String getUseYnName() {
		return useYnName;
	}

	public void setUseYnName(String useYnName) {
		this.useYnName = useYnName;
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
	
	
}
