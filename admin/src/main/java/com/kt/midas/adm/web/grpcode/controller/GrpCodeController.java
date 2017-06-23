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
package com.kt.midas.adm.web.grpcode.controller;

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
import com.kt.midas.adm.web.grpcode.service.GrpCodeService;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeSearchTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;

@Controller
@RequestMapping(value = "/office/grpcode/*")
public class GrpCodeController {
    
    @Autowired
    private GrpCodeService grpCodeService;
    
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@ModelAttribute("grpCodeSearchTO") GrpCodeSearchTO grpCodeSearchTO, Model model)  {
    	Pager pager = new Pager();
    	int totalCnt = grpCodeService.getlistDataCnt(grpCodeSearchTO);
    	List<GrpCodeVO> list = grpCodeService.getlistData(grpCodeSearchTO);
    	
    	grpCodeSearchTO.setTotalPageByTotalRecords(totalCnt);
    	
    	pager.setAllRowCount(totalCnt);
    	pager.setCurrentPage(grpCodeSearchTO.getPageNo());
    	
    	model.addAttribute("paginationInfo", pager);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("list", list);
    	
        return "grpcode/list";
    }
    
    @RequestMapping(value = "addRow", method = RequestMethod.POST)
    public String addRow(Model model)  {
    	return "grpcode/addForm";
    }
    
    @RequestMapping(value = "modRow", method = RequestMethod.POST)
    public String modRow(@RequestParam(value = "grpCodeId", required = true) String grpCodeId, Model model)  {
    	model.addAttribute("detail", grpCodeService.getGrpCodeDetail(grpCodeId));
    	return "grpcode/modForm";
    }
    
    @RequestMapping(value = "proc", method = RequestMethod.POST)
    public @ResponseBody JsonVO proc(@ModelAttribute("grpCodeTO") GrpCodeTO grpCodeTO, BindingResult bindingResult) {
    	String mode = grpCodeTO.getMode();
        
        if(mode.equals("addRow")){
    		return grpCodeService.addRow(grpCodeTO);
    	}else{
    		return grpCodeService.modRow(grpCodeTO);
    	}
    }
}
