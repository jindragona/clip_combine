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
package com.kt.midas.adm.web.userauth.service;

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.userauth.vo.UserAuthSearchTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthTO;
import com.kt.midas.adm.web.userauth.vo.UserAuthVO;

/**
 *
 * @author hwang
 */
public interface UserAuthService {
    public List<UserAuthVO> getlistData(UserAuthSearchTO userAuthSearchTO);
    public int getlistDataCnt(UserAuthSearchTO userAuthSearchTO);
    public UserAuthVO getUserAuthDetail(String rolId);
    
    public JsonVO addRow(UserAuthTO to);
    public JsonVO modRow(UserAuthTO to);
    
    public JsonVO removeAuthMenu(String rolId);
}
