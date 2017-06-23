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
package com.kt.midas.adm.common;

public class Constants {

	// 메인
	public static final String MAIN = "메인";
	
	// 비밀 번호 변경
	public static final String PWDMOD = "비밀번호 변경";
	
	// 시스템 관리 > 사용자 관리
	public static final String USER_LIST = "시스템 관리 > 사용자 관리 > 사용자 리스트";
	public static final String USER_ADD = "시스템 관리 > 사용자 관리 > 사용자 등록";
	public static final String USER_MOD = "시스템 관리 > 사용자 관리 > 사용자 수정";
	
	// 시스템 관리  > 권한 관리
	public static final String AUTH_LIST = "시스템 관리 > 권한 관리 > 권한 리스트";
	public static final String AUTH_ADD = "시스템 관리 > 권한 관리 > 권한 등록";
	public static final String AUTH_MOD = "시스템 관리 > 권한 관리 > 권한 수정";
	
	// 시스템 관리 > 메뉴 관리
	public static final String MENU_LIST = "시스템 관리 > 메뉴 관리 > 메뉴 리스트";
	
	// 시스템관리 > 접속 관리
	public static final String CONN_LIST = "시스템 관리 > 사용자관리 > 접속IP 관리";
	
	// 시스템관리 > MC 연동 이력
	public static final String MC_HIST_LIST = "시스템 관리 > MC 연동 이력 > MC 연동 이력 리스트";
	
	// 시스템관리 > 배치 수행 이력
	public static final String BATCH_HIST_LIST = "시스템 관리 > 배치 수행 이력 > 배치 수행 이력 리스트";
	
	//설정 관리 > 코드 관리
	public static final String CODE_LIST = "설정 관리 > 코드 관리 > 코드 리스트";
	public static final String CODE_ADD = "설정 관리 > 코드 관리 > 코드 등록";
	public static final String CODE_MOD = "설정 관리 > 코드 관리 > 코드 수정";
	
	//설정 관리 > 코드 그룹 관리
	public static final String CGRP_LIST = "설정 관리 > 코드 그룹 관리 > 코드 그룹 리스트";
	public static final String CGRP_ADD = "설정 관리 > 코드 그룹 관리 > 코드 그룹 등록";
	public static final String CGRP_MOD = "설정 관리 > 코드 그룹 관리 > 코드 그룹 수정";
	
	//설정 관리 > 공지 관리
	public static final String NOTI_LIST = "설정 관리 > 공지 관리 > 공지 리스트";
	public static final String NOTI_ADD = "설정 관리 > 공지 관리 > 공지 등록";
	public static final String NOTI_MOD = "설정 관리 > 공지 관리 > 공지 수정";
	
	//설정관리 > 카드/브랜드 관리
	public static final String BRAN_CARD_LIST = "설정 관리 > 카드/브랜드 관리 > 카드/브랜드 리스트";
	public static final String BRAN_CARD_MOD = "설정 관리 > 카드/브랜드 관리 > 카드/브랜드 수정";
	
	// 상품 등록 > 모바일 전단
	public static final String LFT_LIST = "상품 등록 > 모바일 전단 > 전단 리스트";
	public static final String LFT_BASE = "상품 등록 > 모바일 전단 > 전단 기본 정보";
	public static final String LFT_IMG = "상품 등록 > 모바일 전단 > 전단 이미지 정보";
	public static final String LFT_COMPLATE = "상품 등록 > 모바일 전단 > 전단 상세 정보";
	public static final String LFT_SEND_WAY = "상품 등록 > 모바일 전단 > 전단 발송 방법";
	public static final String LFT_TARGET_METHOD = "상품 등록 > 모바일 전단 > 전단 타켓팅";
	
	// 상품 등록 > 타켓팅 배너
	public static final String BANNER_LIST = "상품 등록 > 타겟팅 배너 > 배너 리스트";
	public static final String BANNER_TARGET = "상품 등록 > 타겟팅 배너 > 배너 타켓";
	
	// 상품 등록 > 모바일 카드
	public static final String MOBILE_CARD_LIST = "상품 등록 > 모바일 카드 > 모바일 카드 리스트";
	public static final String MOBILE_CARD_ADD = "상품 등록 > 모바일 카드 > 모바일 카드 등록";
	public static final String MOBILE_CARD_MOD = "상품 등록 > 모바일 카드 > 모바일 카드 수정";
	
	// 상품 등록 > 약관 관리
	public static final String TERM_LIST = "설정 관리 > 약관 관리 > 약관 관리 리스트";
	public static final String TERM_ADD = "설정 관리 > 약관 관리 > 약관 관리 등록";
	public static final String TERM_MOD = "설정 관리 > 약관 관리 > 약관 관리 수정";
	
	public static final String TERM_ITEM_LIST = "설정 관리 > 약관 관리 > 약관 ITEM 리스트";
	public static final String TERM_ITEM_FORM = "설정 관리 > 약관 관리 > 약관 ITEM 등록";
	public static final String TERM_ITEM_VERSION = "설정 관리 > 약관 관리 > 약관 ITEM 버전이력";
	
	// 제휴사 성과
	public static final String ARCH_LFT_LIST = "제휴사 성과 > 모바일 전단 > 모바일 전단 리스트";
	public static final String ARCH_LFT_DETAL = "제휴사 성과 > 모바일 전단 > 모바일 전단 상세";
	
	public static final String ARCH_BANNER_LIST = "제휴사 성과 > 타켓팅 배너 > 타켓팅 배너 리스트";
	public static final String ARCH_BANNER_DETAL = "제휴사 성과 > 타켓팅 배너 > 타켓팅 배너 상세";
	
	public static final String ARCH_CARD_LIST = "제휴사 성과 > 모바일 카드 > 모바일 카드 리스트";
	public static final String ARCH_CARD_DETAL = "제휴사 성과 > 모바일 카드 > 모바일 카드 상세";
	
	// 정산
	public static final String CAL_LFT_LIST = "정산 > 모바일 전단 > 모바일 전단 리스트";
	public static final String CAL_LFT_DETAL = "정산 > 모바일 전단 > 모바일 전단 상세";
	
	public static final String CAL_BANNER_LIST = "정산 > 타켓팅 배너 > 타켓팅 배너 리스트";
	public static final String CAL_BANNER_DETAL = "정산 > 타켓팅 배너 > 타켓팅 배너 상세";
	
	public static final String CAL_CARD_LIST = "정산 > 모바일 카드 > 모바일 카드 리스트";
	public static final String CAL_CARD_DETAL = "정산 > 모바일 카드 > 모바일 카드 상세";
}
