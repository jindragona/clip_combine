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
package com.kt.midas.adm.common.util;

/**
 *
 * @author seulee
 */
public class CommonUtils {

    public static String fixNull(String str){
		String rtn = "";
		if(str != null){
			rtn = str;
		}
		return rtn;
	}
    
}
