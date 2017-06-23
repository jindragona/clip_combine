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
package com.kt.midas.adm.web.user.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.user.dao.UserDao;
import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserSearchTO;
import com.kt.midas.adm.web.user.vo.UserTO;
import com.kt.midas.adm.web.user.vo.UserVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class UserServiceImpl extends BaseServiceImpl implements UserService{
    
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserDao userDao;
    
    @Value("${pwd.mdf.date}")
    private String pwdMdfDate;
    
    @Value("${init.pwd}")
    private String initPwd;
    
    @Override
    public List<UserVO> getlistData(UserSearchTO userSearchTO) {
    	List<UserVO> list = userDao.findBySearch(userSearchTO);
    	
    	try{
    		for(UserVO vo : list){
        		String name = vo.getUserName();
        		vo.setUserName(name);
        		
        		String phoneNum = vo.getPhoneNum();
        		vo.setPhoneNum(phoneNum);
        	}
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService pwdInit error", e);
			}
		}
    	
    	return list;
    }
    
    @Override
    public int getlistDataCnt(UserSearchTO userSearchTO) {
        return userDao.findTotalRecordsBySearch(userSearchTO);
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(UserTO to) {
    	JsonVO jsonVO = new JsonVO();
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    	
    	UserInfoVO info = getUserName();
    	to.setInsUser(info.getUserId());
    	to.setInsUserName(info.getUserName());
    	
    	try{
    		String encPwd = to.getUserPwd();
    		to.setUserPwd(encPwd);
    		
    		cal.add(Calendar.DATE, Integer.parseInt(pwdMdfDate));
    		String pwdMdfDt = sdf.format(cal.getTime());
    		to.setPwdMdfDt(pwdMdfDt);
    		
    		String encPhoneNum = to.getPhoneNum();
    		to.setPhoneNum(encPhoneNum);
    		
    		String encUserName = to.getUserName();
    		to.setUserName(encUserName);
    		
    		userDao.insert(to);
    		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	}catch(Exception e){
    		if(LOG.isErrorEnabled()){
    			LOG.error("[user add Exception] {}", e.getMessage(), e);
			}
    	}
    	
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(UserTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	try{
    		UserVO userVO = userDao.getUserDetail(String.valueOf(to.getUserSeq()));
    		
    		String encPwd = to.getUserOldPwd();
    		
    		to.setEncUserOldPwd(encPwd);
    		to.setUserId(userVO.getUserId());
    		
    		int cnt = userDao.userIdPwdCheck(to);
    		
    		if(cnt < 1){
    			jsonVO.setCode(ResultEnum.LOGIN_OLD_PWD.result);
    			return jsonVO;
    		}
    		
    		String newPasswd = to.getUserNewPwd();
    		to.setEncUserNewPwd(newPasswd);
    		
    		String encPhoneNum = to.getPhoneNum();
    		to.setPhoneNum(encPhoneNum);
    		
    		userDao.update(to);
    		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("[user mod Exception] {}", e.getMessage(), e);
			}
    		
    		jsonVO.setCode(ResultEnum.FAIL.result);
		}
		
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modAdmRow(UserTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	try{
    		UserVO userVO = userDao.getUserDetail(String.valueOf(to.getUserSeq()));
    		to.setUserId(userVO.getUserId());
    		
	    	String encUserName = to.getUserName();
			to.setUserName(encUserName);
			userDao.modAdmRow(to);
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("[user mod Exception] {}", e.getMessage(), e);
			}
    		
    		jsonVO.setCode(ResultEnum.FAIL.result);
		}
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public UserVO getUserDetail(String id){
    	
    	UserVO vo = userDao.getUserDetail(id);
    	
    	try{
    		String name = vo.getUserName();
    		vo.setUserName(name);
    		
    		String phoneNum = vo.getPhoneNum();
    		vo.setPhoneNum(phoneNum);
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService pwdInit error", e);
			}
		}
    	
    	return vo;
    }
    
    @Override
    @TxRequired
    public JsonVO pwdInit(String id){
    	JsonVO jsonVO = new JsonVO();
    	UserTO userTO = new UserTO();
    	try{
    		UserVO vo = userDao.getUserDetail(id);
    		userTO.setUserId(vo.getUserId());
    		String encPwd = initPwd;
    		userTO.setUserPwd(encPwd);
    		userDao.pwdInit(userTO);
    		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	}catch(Exception e){
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService pwdInit error", e);
			}
    		jsonVO.setCode(ResultEnum.FAIL.result);
    		jsonVO.setMsg(e.getMessage());
    	}
    	
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO pwdErrorCntInit(String id){
    	JsonVO jsonVO = new JsonVO();
    	UserVO vo = userDao.getUserDetail(id);
    	userDao.pwdErrorCntInit(vo.getUserId());
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public JsonVO checkLogingId(String id){
    	JsonVO jsonVO = new JsonVO();
    	int cnt = userDao.checkLogingId(id);
		if(cnt > 0){
			jsonVO.setCode(ResultEnum.DUPLICATED.result);
		}else{
			jsonVO.setCode(ResultEnum.SUCCESS.result);    			
		}
    	return jsonVO;
    }
    
    @Override
    public List<UserVO> getUserList(String rolId){
    	List<UserVO> userList = userDao.getUserList(Integer.parseInt(rolId));
    	
    	try{
    		for(UserVO vo : userList){
        		String name = vo.getUserName();
        		String encName = name;
        		vo.setUserName(encName);
        	}
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService pwdInit error", e);
			}
		}
    	
    	return userList;
    }
    
    @Override
    public JsonVO notMappingUser(String rolId){
    	JsonVO jsonVO = new JsonVO();
    	List<UserVO> vo = null;
    	
    	try{
    		vo = userDao.notMappingUser(Integer.parseInt(rolId));
    		
    		for(UserVO user : vo){
        		String name = user.getUserName();
        		String encName = name;
        		user.setUserName(encName);
        	}
    		jsonVO.setCode(ResultEnum.SUCCESS.result);
    		jsonVO.setData(vo);
    	}catch (Exception e) {
    		if(LOG.isErrorEnabled()){
    			LOG.error("UserService pwdInit error", e);
			}
    		jsonVO.setCode(ResultEnum.FAIL.result);
		}
    	
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO mappingUser(UserMenuAuthTO userMenuAuthTO){
    	JsonVO jsonVO = new JsonVO();
    	UserVO vo = null;
    	
    	String[] tempUserId = null;
    	
    	if(userMenuAuthTO.getUserId().indexOf(",") != -1){
    		tempUserId = userMenuAuthTO.getUserId().split(",");
    		
    		for(int i=0;i<tempUserId.length;i++){
    			UserMenuAuthTO to = new UserMenuAuthTO();
    			vo = userDao.getUserDetail(tempUserId[i]);
    			to.setUserId(vo.getUserId());
    			to.setRolId(userMenuAuthTO.getRolId());
    			userDao.mappingUser(to);
    			vo = null;
    		}
    		
    	}else{
    		vo = userDao.getUserDetail(userMenuAuthTO.getUserId());
    		userMenuAuthTO.setUserId(vo.getUserId());
    		userDao.mappingUser(userMenuAuthTO);
    	}
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO mappingCloseUser(UserMenuAuthTO userMenuAuthTO){
    	JsonVO jsonVO = new JsonVO();
    	UserVO vo = null;
    	
    	String[] tempUserId = null;
    	
    	if(userMenuAuthTO.getUserId().indexOf(",") != -1){
    		tempUserId = userMenuAuthTO.getUserId().split(",");
    		
    		for(int i=0;i<tempUserId.length;i++){
    			UserMenuAuthTO to = new UserMenuAuthTO();
    			vo = userDao.getUserDetail(tempUserId[i]);
    			to.setUserId(vo.getUserId());
    			to.setRolId(userMenuAuthTO.getRolId());
    			userDao.mappingCloseUser(to);
    			vo = null;
    		}
    		
    	}else{
    		vo = userDao.getUserDetail(userMenuAuthTO.getUserId());
    		userMenuAuthTO.setUserId(vo.getUserId());
    		userDao.mappingCloseUser(userMenuAuthTO);
    	}
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO stopUser(String userId){
    	JsonVO jsonVO = new JsonVO();
    	UserVO vo = userDao.getUserDetail(userId);
    	
    	userDao.stopUser(vo.getUserId());
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO useUser(String userId){
    	JsonVO jsonVO = new JsonVO();
    	UserVO vo = userDao.getUserDetail(userId);
    	
    	userDao.useUser(vo.getUserId());
    	
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public List<AuthIpVO> getAuthIpList(UserTO to){
    	if(to.getUserId() == null || "".equals(to.getUserId())){
    		UserVO userVO = userDao.getUserDetail(String.valueOf(to.getUserSeq()));
        	to.setUserId(userVO.getUserId());
    	}
    	
    	List<AuthIpVO> ipVO = userDao.getAuthIpList(to);
    	
    	for(AuthIpVO vo : ipVO){
    		if(vo.getIpAdr().indexOf("~") != -1){
    			String[] temp = vo.getIpAdr().split("~");
    			String[] firstTemp = temp[0].split("\\.");
    			String[] secondTemp = temp[1].split("\\.");
    			
    			String addr1 = firstTemp[0];
    			String addr3 = firstTemp[2];
    			StringBuilder sb = new StringBuilder();
    			for(int i=0;i<addr1.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr1(sb.toString());
    			sb = null;
    			sb = new StringBuilder();
    			for(int i=0;i<addr3.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr3(sb.toString());
    			sb = null;
    			
    			vo.setAddr2(firstTemp[1]);
    			vo.setAddr4(firstTemp[3]);
    			
    			String addr5 = secondTemp[0];
    			String addr7 = secondTemp[2];
    			sb = new StringBuilder();
    			for(int i=0;i<addr5.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr5(sb.toString());
    			sb = null;
    			sb = new StringBuilder();
    			for(int i=0;i<addr7.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr7(sb.toString());
    			sb = null;
    			
    			vo.setAddr6(secondTemp[1]);
    			vo.setAddr8(secondTemp[3]);
    		}else{
    			String[] firstTemp = vo.getIpAdr().split("\\.");
    			String addr1 = firstTemp[0];
    			String addr3 = firstTemp[2];
    			StringBuilder sb = new StringBuilder();
    			for(int i=0;i<addr1.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr1(sb.toString());
    			sb = null;
    			sb = new StringBuilder();
    			for(int i=0;i<addr3.length();i++){
    				sb.append("*");
    			}
    			vo.setAddr3(sb.toString());
    			sb = null;
    			
    			vo.setAddr2(firstTemp[1]);
    			vo.setAddr4(firstTemp[3]);
    		}
    	}
    			
    	return ipVO;
    }
    
    @Override
    @TxRequired
    public JsonVO userIpAuthAdd(UserAuthIpTO to){
    	JsonVO jsonVO = new JsonVO();
    	
    	UserVO userVO = userDao.getUserDetail(String.valueOf(to.getUserSeq()));
    	to.setUserId(userVO.getUserId());
    	
    	UserInfoVO info = getUserName();
    	
    	to.setInsUser(info.getUserId());
    	to.setInsUSerName(info.getUserName());
    	
    	if(to.getIpType() == 1){
    		to.setIpAdr(to.getIpAdr1() + "." + to.getIpAdr2() + "." + to.getIpAdr3() + "." + to.getIpAdr4());
    	}else{
    		to.setIpAdr(to.getIpAdr5() + "." + to.getIpAdr6() + "." + to.getIpAdr7() + "." + to.getIpAdr8() + "~" + to.getIpAdr9() + "." + to.getIpAdr10() + "." + to.getIpAdr11() + "." + to.getIpAdr12());
    	}
    	userDao.userIpAuthAdd(to);
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public void userIpAuthAddLdap(UserAuthIpTO to){
    	userDao.userIpAuthAdd(to);
    }
    
    @Override
    @TxRequired
    public JsonVO userIpAuthRemove(UserAuthIpTO to){
    	JsonVO jsonVO = new JsonVO();
    	userDao.userIpAuthRemove(to);
    	jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
}
