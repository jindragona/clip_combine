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
package com.kt.midas.adm.web.menu.dao;

import java.util.List;

import com.kt.midas.adm.web.menu.vo.MenuTO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;
import com.kt.midas.adm.web.menu.vo.MenuVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;

public interface MenuDao {

	public List<MenuTreeVO> getUpperMenuTreeList();
	public MenuVO getMenuDetail(int menuId);
	public List<MenuVO> getMenuList(int rolId);
	public List<MenuVO> notMappingMenu(int rolId);
	public List<MenuTreeVO> getMenuTreeList(int menuId);
	public MenuVO getMenuIdOrUpperMenuId(String url);
	
	public void mappingMenu(UserMenuAuthTO to);
	public void mappingCloseMenu(UserMenuAuthTO to);
	public void insert(MenuTO to);
	public void update(MenuTO to);
	
	
	public void removeAuthMenu(String menuId);
	public void removeUpperMenu(String menuId);
	public void removeMenu(String menuId);
}
