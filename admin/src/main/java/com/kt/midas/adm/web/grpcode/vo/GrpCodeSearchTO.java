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
package com.kt.midas.adm.web.grpcode.vo;

import com.kt.midas.adm.common.vo.SearchTO;

public class GrpCodeSearchTO extends SearchTO{

	private String grpCodeName;
	private String useYn;
	
	public String getGrpCodeName() {
		return grpCodeName;
	}
	public void setGrpCodeName(String grpCodeName) {
		this.grpCodeName = grpCodeName;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	
	
}
