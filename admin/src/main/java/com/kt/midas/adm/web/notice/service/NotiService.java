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

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.notice.vo.NotiSearchTO;
import com.kt.midas.adm.web.notice.vo.NotiTO;
import com.kt.midas.adm.web.notice.vo.NotiVO;

/**
 *
 * @author hwang
 */
public interface NotiService {
    public List<NotiVO> getlistData(NotiSearchTO notiSearchTO);
    public int getlistDataCnt(NotiSearchTO notiSearchTO);
    public JsonVO addRow(NotiTO to);
    public JsonVO modRow(NotiTO to);
    public NotiVO getNotiDetail(String id);
}
