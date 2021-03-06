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

package com.kt.midas.adm.common;

public enum RoleEnum {
	
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_SUPER("ROLE_SUPER");
	
	
	public final String role;

	RoleEnum(String role) {
		this.role = role;
	}
}
