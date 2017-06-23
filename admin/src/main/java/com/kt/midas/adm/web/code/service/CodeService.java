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
package com.kt.midas.adm.web.code.service;

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.code.vo.CodeSearchTO;
import com.kt.midas.adm.web.code.vo.CodeTO;
import com.kt.midas.adm.web.code.vo.CodeVO;
import com.kt.midas.adm.web.code.vo.CommonCodeVO;

/**
 *
 * @author hwang
 */
public interface CodeService {
    public List<CodeVO> getlistData(CodeSearchTO to);
    public int getlistDataCnt(CodeSearchTO to);
    public JsonVO addRow(CodeTO to);
    public JsonVO modRow(CodeTO to);
    public CodeVO getCodeDetail(String id);
    public List<CommonCodeVO> commonCodeList(CodeSearchTO to);
    public JsonVO checkCodeId(final String CodeId);
}
