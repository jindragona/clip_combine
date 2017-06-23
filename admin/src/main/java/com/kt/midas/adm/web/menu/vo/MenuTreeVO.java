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

import java.util.ArrayList;
import java.util.List;

public class MenuTreeVO {

	private String menuId;
	private String label;
	private String upperMenuId; 
	private String menuName;
	private String menuUrl;
	
	private List<MenuTreeVO> children = new ArrayList<MenuTreeVO>();
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public List<MenuTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeVO> children) {
		this.children = children;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	
}
