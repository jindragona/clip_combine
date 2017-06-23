/*
 *  Midas version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.web.login.service;

import java.util.List;

import com.kt.midas.adm.common.service.BaseService;
import com.kt.midas.adm.common.vo.CommonBaseVo;
import com.kt.midas.adm.common.vo.CommonManagerVO;
import com.kt.midas.adm.web.menu.vo.MenuTreeVO;

public interface LoginService<T extends CommonBaseVo, PK> extends BaseService<T, PK> {
    
    public CommonManagerVO selectByLoginId(String loginId);
    
    /**
     * 전달된 객체의 value 값을 쿼리 조건에 맞게 변경한다.<br/>
     * 예를 들어 name필드의 경우 LIKE를 사용하기 때문에 name 필드의 해당하는 값에 %를 붙여서 리턴한다.
     *
     * @param commonManager
     * @return
     *
     */
    public CommonManagerVO getWhereCondition(CommonManagerVO commonManager);

    /**
     * @param commonManager
     */
    public void insertLoginHist(CommonManagerVO commonManager);
    
    /**
     * @param commonManager
     */
    public void updateLoginHist(CommonManagerVO commonManager);
    
    public List<MenuTreeVO> selectAuthMenuList(String pk);
}
