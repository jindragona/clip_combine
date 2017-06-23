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

/**
 *
 * @author hwang
 */
public class CodeTO{
	private String mode;
	
	private String codeId;
	private String codeName;
	private String codeGrpId;
	private String useYn;
	private int codeOdr;
	
	private String insUser;
	private String insUserName;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getInsUser() {
		return insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getInsUserName() {
		return insUserName;
	}

	public void setInsUserName(String insUserName) {
		this.insUserName = insUserName;
	}

	public int getCodeOdr() {
		return codeOdr;
	}

	public void setCodeOdr(int codeOdr) {
		this.codeOdr = codeOdr;
	}
	
	
}
