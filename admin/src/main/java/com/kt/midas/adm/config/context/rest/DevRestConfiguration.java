/*
 * MIDAS version 1.0
 *
 *  Copyright ⓒ 2017 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.config.context.rest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kt.midas.frw.security.ClipHostnameVerifier;
import com.kt.midas.frw.security.SSLContextFactory;

/**
 * local, dev, tb 환경에 적용되는 Bean 설정
 *
 * @author kh74.jeong
 *
 */
@Configuration
@Profile(value={"local", "dev", "tb"})
public class DevRestConfiguration {

	@Autowired
	private RequestConfig httpRequestConfig;
	
	@Bean
	public ClipHostnameVerifier hostnameVerifier(){
		return new ClipHostnameVerifier();
	}
	
	@Bean
	public SSLContext sslcontext() throws KeyManagementException, NoSuchAlgorithmException{
		return SSLContextFactory.getObject();
	}
	
	@Bean
	public HttpClientBuilder httpClientBuilder() throws KeyManagementException, NoSuchAlgorithmException {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setDefaultRequestConfig(httpRequestConfig);
		httpClientBuilder.setMaxConnPerRoute(200);
		httpClientBuilder.setMaxConnTotal(200);
		
		/** local, dev, tb 환경에만 적용 */
		httpClientBuilder.setSSLHostnameVerifier(hostnameVerifier());
		httpClientBuilder.setSslcontext(sslcontext());
        return httpClientBuilder;
    }
}
