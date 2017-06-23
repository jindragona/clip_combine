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
package com.kt.midas.adm.web.grpcode.service;

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeSearchTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeTO;
import com.kt.midas.adm.web.grpcode.vo.GrpCodeVO;

/**
 *
 * @author hwang
 */
public interface GrpCodeService {
    public int getlistDataCnt(GrpCodeSearchTO to);
    public List<GrpCodeVO> getlistData(GrpCodeSearchTO to);
    
    public JsonVO addRow(GrpCodeTO to);
    public JsonVO modRow(GrpCodeTO to);
    public GrpCodeVO getGrpCodeDetail(String id);
    
    public List<GrpCodeVO> getGrpCodeList();
}
