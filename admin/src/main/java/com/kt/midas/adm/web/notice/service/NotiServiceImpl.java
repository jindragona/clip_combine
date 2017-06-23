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
package com.kt.midas.adm.web.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.midas.adm.common.ResultEnum;
import com.kt.midas.adm.common.service.BaseServiceImpl;
import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.common.vo.UserInfoVO;
import com.kt.midas.adm.web.notice.dao.NoticeDao;
import com.kt.midas.adm.web.notice.vo.NotiSearchTO;
import com.kt.midas.adm.web.notice.vo.NotiTO;
import com.kt.midas.adm.web.notice.vo.NotiVO;
import com.kt.midas.frw.transaction.TxMain;
import com.kt.midas.frw.transaction.TxRequired;

/**
 *
 * @author hwang
 */
@Service
@TxMain
public class NotiServiceImpl extends BaseServiceImpl implements NotiService{
    
    @Autowired
    private NoticeDao noticeDao;
    
    @Override
    public List<NotiVO> getlistData(NotiSearchTO notiSearchTO) {
    	List<NotiVO> notiList = noticeDao.findBySearch(notiSearchTO);
        return notiList;
    }
    
    @Override
    public int getlistDataCnt(NotiSearchTO notiSearchTO) {
    	int cnt = noticeDao.findTotalRecordsBySearch(notiSearchTO);
        return cnt;
    }
    
    @Override
    @TxRequired
    public JsonVO addRow(NotiTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
		to.setInsUser(info.getUserId());
		to.setInsUserName(info.getUserName());
		
		noticeDao.insert(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    @TxRequired
    public JsonVO modRow(NotiTO to) {
    	JsonVO jsonVO = new JsonVO();
    	
    	UserInfoVO info = getUserName();
		to.setInsUser(info.getUserId());
		to.setInsUserName(info.getUserName());
    	
		noticeDao.update(to);
		jsonVO.setCode(ResultEnum.SUCCESS.result);
    	return jsonVO;
    }
    
    @Override
    public NotiVO getNotiDetail(String id){
    	return noticeDao.getNotiDetail(Integer.parseInt(id));
    }
}
