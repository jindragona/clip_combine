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
package com.kt.midas.adm.web.code.vo;

import com.kt.midas.adm.common.vo.SearchTO;

public class CodeSearchTO extends SearchTO{

	private String codeName;
	private String codeGrpId;
	
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeGrpId() {
		return codeGrpId;
	}

	public void setCodeGrpId(String codeGrpId) {
		this.codeGrpId = codeGrpId;
	}
	
	
}
