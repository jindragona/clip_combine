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
package com.kt.midas.adm.common.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Cipher {
	/** 
   * SHA256 암호화 문자열로 변환
   * @param value
   * @return
   * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException 
   */
	
   public static String encryptSHA256(String value) throws NoSuchAlgorithmException{
      StringBuilder sb = new StringBuilder();
      
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      sha.update(value.getBytes());

      byte[] digest = sha.digest();
      for (int i=0; i<digest.length; i++) {
            //  encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
    	  sb.append(Integer.toHexString(digest[i] & 0xFF).toUpperCase());
      }
      
      return sb.toString();
   }
   
	public static String encryptSHA256UPDATE(String value, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		StringBuilder sb = new StringBuilder();
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		
		sha.reset();
		sha.update(salt);
		byte[] digest = sha.digest(value.getBytes("UTF-8"));
		for (int i=0; i<digest.length; i++) {
			sb.append(Integer.toHexString(digest[i] & 0xFF).toUpperCase());
		}
		return sb.toString();
 }
}
