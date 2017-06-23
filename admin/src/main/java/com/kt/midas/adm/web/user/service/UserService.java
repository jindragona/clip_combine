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

import java.util.List;

import com.kt.midas.adm.common.vo.JsonVO;
import com.kt.midas.adm.web.user.vo.AuthIpVO;
import com.kt.midas.adm.web.user.vo.UserAuthIpTO;
import com.kt.midas.adm.web.user.vo.UserSearchTO;
import com.kt.midas.adm.web.user.vo.UserTO;
import com.kt.midas.adm.web.user.vo.UserVO;
import com.kt.midas.adm.web.userauth.vo.UserMenuAuthTO;

/**
 *
 * @author hwang
 */
public interface UserService {
    public List<UserVO> getlistData(UserSearchTO userSearchTO);
    public int getlistDataCnt(UserSearchTO userSearchTO);
    
    public List<AuthIpVO> getAuthIpList(UserTO userTO);
    
    public JsonVO addRow(UserTO to);
    public JsonVO modRow(UserTO to);
    public JsonVO modAdmRow(UserTO to);
    public UserVO getUserDetail(String id);
    public JsonVO pwdInit(String id);
    public JsonVO pwdErrorCntInit(String id);
    public JsonVO checkLogingId(String id);
    
    public List<UserVO> getUserList(String rolId);
    public JsonVO notMappingUser(String rolId);
    public JsonVO mappingUser(UserMenuAuthTO userMenuAuthTO);
    public JsonVO mappingCloseUser(UserMenuAuthTO userMenuAuthTO);
    
    public JsonVO stopUser(String userId);
    public JsonVO useUser(String userId);
    public JsonVO userIpAuthAdd(UserAuthIpTO to);
    public JsonVO userIpAuthRemove(UserAuthIpTO to);
    public void userIpAuthAddLdap(UserAuthIpTO to);
}
