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
package com.kt.midas.adm.web.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.main.service.MainService;
import com.kt.midas.adm.web.main.vo.AuthVO;
import com.kt.midas.adm.web.main.vo.MainVO;
import com.kt.midas.adm.web.main.vo.PwdModTO;

@Controller
public class MainController {
	
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/office/main", method = RequestMethod.POST)
    public String main(@ModelAttribute("mainVO") MainVO mainVO, Model model)  {
    	List<MainVO> lftAgeList = mainService.getLftAge(mainVO);
    	model.addAttribute("lftAgeList", lftAgeList);
    	
		List<MainVO> lftGdrList = mainService.getLftGdr(mainVO);
		model.addAttribute("lftGdrList", lftGdrList);
		
		List<MainVO> lftMonthList = mainService.getLftSndMonthStat(mainVO);
		model.addAttribute("lftMonthList", lftMonthList);
		
		List<MainVO> bannAgeList = mainService.getBannAge(mainVO);
		model.addAttribute("bannAgeList", bannAgeList);
		
		List<MainVO> bannGdrList = mainService.getBannGdr(mainVO);
		model.addAttribute("bannGdrList", bannGdrList);
		
		List<MainVO> bannMonthList = mainService.getBannSndMonthStat(mainVO);
		model.addAttribute("bannMonthList", bannMonthList);
		
		List<MainVO> mcrdAgeList = mainService.getMcrdAge(mainVO);
		model.addAttribute("mcrdAgeList", mcrdAgeList);
		
		List<MainVO> mcrdGdrList = mainService.getMcrdGdr(mainVO);
		model.addAttribute("mcrdGdrList", mcrdGdrList);
    	
		List<MainVO> mcrdAreaList = mainService.getMcrdArea(mainVO);
		model.addAttribute("mcrdAreaList", mcrdAreaList);
		
        return "main/main";
    }
    
    @RequestMapping(value = "/office/pwdMdf", method = RequestMethod.POST)
    public String pwdMdf(Model model)  {
        return "main/pwdForm";
    }
    
    @RequestMapping(value = "/office/pwdMod", method = RequestMethod.POST)
    public @ResponseBody JsonVO pwdMod(@ModelAttribute("pwdModTO") PwdModTO pwdModTO) {
    	return mainService.pwdMod(pwdModTO);
    }
    
    @RequestMapping(value = "updateAuthNo", method = RequestMethod.POST)
    public @ResponseBody JsonVO updateAuthNo(@ModelAttribute("authVO") AuthVO authVO) {
        return mainService.updateAuthNo(authVO);
    }
}
