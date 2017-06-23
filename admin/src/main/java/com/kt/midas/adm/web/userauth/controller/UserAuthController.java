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
package com.kt.midas.adm.web.userauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.util.Pager;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.menu.service.MenuService;
import com.kt.midas.adm.web.user.service.UserService;
import com.kt.midas.adm.web.userauth.service.UserAuthService;
import com.kt.midas.adm.web.userauth.vo.UserAuthSearchTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;

@Controller
@RequestMapping(value = "/office/auth/*")
public class UserAuthController {
	
    @Autowired
    private UserAuthService userAuthService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public String list(@ModelAttribute("userAuthSearchTO") UserAuthSearchTO userAuthSearchTO, Model model) {
    	Pager pager = new Pager();
    	int totalCnt = userAuthService.getlistDataCnt(userAuthSearchTO);
    	userAuthSearchTO.setTotalPageByTotalRecords(totalCnt);
    	List<UserAuthVO> notiList = userAuthService.getlistData(userAuthSearchTO);
    	
    	pager.setAllRowCount(totalCnt);
    	pager.setCurrentPage(userAuthSearchTO.getPageNo());
    	
    	model.addAttribute("paginationInfo", pager);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("list", notiList);
    	
        return "userauth/list";
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(Model model)  {
    	return "userauth/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(@RequestParam(value = "rolId", required = true) String rolId, Model model)  {
    	model.addAttribute("detail", userAuthService.getUserAuthDetail(rolId));
    	model.addAttribute("menuList", menuService.getMenuList(rolId));
    	model.addAttribute("userList", userService.getUserList(rolId));
    	return "userauth/modForm";
    }
    
    @RequestMapping(value = "notMappingUser", method = RequestMethod.POST)
    public @ResponseBody JsonVO notMappingUser(@RequestParam(value = "rolId", required = true) String rolId)  {
    	return userService.notMappingUser(rolId);
    }
    
    @RequestMapping(value = "mappingUser", method = RequestMethod.POST)
    public @ResponseBody JsonVO mappingUser(@ModelAttribute("userMenuAuthTO") UserMenuAuthTO userMenuAuthTO)  {
    	return userService.mappingUser(userMenuAuthTO);
    }
    
    @RequestMapping(value = "mappingCloseUser", method = RequestMethod.POST)
    public @ResponseBody JsonVO mappingCloseUser(@ModelAttribute("userMenuAuthTO") UserMenuAuthTO userMenuAuthTO)  {
    	return userService.mappingCloseUser(userMenuAuthTO);
    }
    
    @RequestMapping(value = "notMappingMenu", method = RequestMethod.POST)
    public @ResponseBody JsonVO notMappingMenu(@RequestParam(value = "rolId", required = true) String rolId)  {
    	JsonVO json = menuService.notMappingMenu(rolId);
    	return json;
    }
    
    @RequestMapping(value = "mappingMenu", method = RequestMethod.POST)
    public @ResponseBody JsonVO mappingMenu(@ModelAttribute("userMenuAuthTO") UserMenuAuthTO userMenuAuthTO)  {
    	return menuService.mappingMenu(userMenuAuthTO);
    }
    
    @RequestMapping(value = "mappingCloseMenu", method = RequestMethod.POST)
    public @ResponseBody JsonVO mappingCloseMenu(@ModelAttribute("userMenuAuthTO") UserMenuAuthTO userMenuAuthTO)  {
    	return menuService.mappingCloseMenu(userMenuAuthTO);
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("userAuthTO") UserAuthTO userAuthTO, BindingResult bindingResult) {
    	String mode = userAuthTO.getMode();
    	
    	if(mode.equals("addRow")){
    		return userAuthService.addRow(userAuthTO);
    	}else{
    		return userAuthService.modRow(userAuthTO);
    	}
    }
    
    @RequestMapping(value = "removeAuthMenu", method = RequestMethod.POST)
    public @ResponseBody JsonVO removeAuthMenu(@RequestParam(value = "rolId", required = true) String rolId)  {
    	return userAuthService.removeAuthMenu(rolId);
    }
}
