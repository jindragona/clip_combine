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


public enum ResultEnum {

	SUCCESS("0000"),
	FAIL("9999"),
	FILE_FORMAT_ERROR("FILE_FORMAT_ERROR"),
	DUPLICATED("9000"),
	LOGIN_FAIL("8000"), // 로그인 실패
	LOGIN_FIVE_FAIL("8001"), // 5번 비밀 번호 틀린 경우
	LOGIN_OLD_PWD("8002"), // 비밀 번호 변경 시 기존 비밀번호 틀렸을 시
	STOP_USER_LOGIN("8003"), // 사용 중단 된 사용자의 의한 로그인 시
	IP_CHECK_ERROR("8004"), // 등록되지 않은 IP 사용자
	SMSAUTH_CHECK_ERROR("8005"), // SMS인증 실패
	ACCOUNT_EXPIRED_ERROR("8006"), // 비밀번호 만료일이 지났을 경우
	AUTHENTICATION_ERROR("8007"), // LDAP 인증에 실패
	LOGIN_PWD_MDF("0100"),
	LOGIN_ALREADY("0001"),
	CTN_FORMAT_ERROR("9001"), // 전화번호 포맷 에러
	FILE_PUSH_EXT_CHECK("3001"), // PUSH 파일 체크 실패
	FILE_MMS_EXT_CHECK("3002"), // MMS 파일 체크 실패
	FILE_EXT_CHECK("3003"), // 일반 이미지
	FILE_EXCEL_CHECK("3004");
	
	
	public final String result;

	ResultEnum(String result) {
		this.result = result;
	}
}
