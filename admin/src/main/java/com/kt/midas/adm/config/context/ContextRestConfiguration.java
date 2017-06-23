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
package com.kt.midas.adm.config.context;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * context-rest.xml을 java config로 전환 config class.
 * 
 * @author kh74.jeong
 *
 */
@Configuration
public class ContextRestConfiguration {

	@Autowired
	private HttpClientBuilder httpClientBuilder;
	
	@Bean(destroyMethod="close")
    public CloseableHttpClient httpClient() throws KeyManagementException, NoSuchAlgorithmException {
        return httpClientBuilder.build();
    }
	
	@Bean
	public RequestConfig requestConfig(){
		Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setSocketTimeout(300000);
		requestConfigBuilder.setConnectTimeout(3000);
		
		return requestConfigBuilder.build();
	}
	
	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException{
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
	}
}