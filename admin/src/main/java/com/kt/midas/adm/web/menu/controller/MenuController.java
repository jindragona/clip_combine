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
package com.kt.midas.adm.web.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.menu.service.MenuService;
import com.kt.midas.adm.web.menu.vo.MenuSearchTO;
import com.kt.midas.adm.web.menu.vo.MenuTO;

@Controller
@RequestMapping(value = "/office/menu/*")
public class MenuController {
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@ModelAttribute("menuSearchTO") MenuSearchTO menuSearchTO, Model model)  {
        return "menu/list";
    }
    
    @RequestMapping(value = "data", method = RequestMethod.POST)
    public @ResponseBody JsonVO data()  {
        return menuService.getlistData();
    }
    
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public @ResponseBody JsonVO detail(@ModelAttribute("menuSearchTO") MenuSearchTO menuSearchTO)  {
        return menuService.getMenuDetail(menuSearchTO.getMenuId());
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(@RequestParam(value = "menuId", required = true) String menuId, Model model)  {
    	return "menu/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(@RequestParam(value = "menuId", required = true) String menuId, Model model)  {
    	model.addAttribute("detail", menuService.getMenuDetail(menuId));
    	return "menu/modForm";
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("menuTO") MenuTO menuTO, BindingResult bindingResult) {
    	String mode = menuTO.getMode();
    	
    	if(mode.equals("addRow")){
    		return menuService.addRow(menuTO);
    	}else{
    		return menuService.modRow(menuTO);
    	}
    	
    }
    
    @RequestMapping(value = "removeMenun", method = RequestMethod.POST)
    public @ResponseBody JsonVO removeMenun(@RequestParam(value = "menuId", required = true) String menuId, Model model)  {
    	return menuService.removeMenun(menuId);
    }
    
}
