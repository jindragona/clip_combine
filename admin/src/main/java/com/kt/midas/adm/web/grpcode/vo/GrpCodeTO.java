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

/**
 *
 * @author hwang
 */
public class GrpCodeTO{
	private String mode;
	private String grpCodeName;
	private String grpCodeId;
	private String useYn;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getGrpCodeName() {
		return grpCodeName;
	}

	public void setGrpCodeName(String grpCodeName) {
		this.grpCodeName = grpCodeName;
	}

	public String getGrpCodeId() {
		return grpCodeId;
	}

	public void setGrpCodeId(String grpCodeId) {
		this.grpCodeId = grpCodeId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	
}
