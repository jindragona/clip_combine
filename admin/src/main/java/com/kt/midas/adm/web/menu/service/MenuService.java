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
package com.kt.midas.adm.web.menu.service;

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.menu.vo.MenuTO;
import com.kt.midas.adm.web.menu.vo.MenuVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;

/**
 *
 * @author hwang
 */
public interface MenuService {
    public JsonVO getlistData();
    
    public JsonVO addRow(MenuTO to);
    public JsonVO modRow(MenuTO to);
    public JsonVO getMenuDetail(String id);
    public MenuVO getMenuIdOrUpperMenuId(String url);
    
    public List<MenuVO> getMenuList(String rolId);
    public JsonVO notMappingMenu(String rolId);
    public JsonVO mappingMenu(UserMenuAuthTO userMenuAuthTO);
    public JsonVO mappingCloseMenu(UserMenuAuthTO userMenuAuthTO);
    public JsonVO removeMenun(String menuId);
}
