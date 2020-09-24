package com.rgp.de.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	/*
	 * @Bean public HttpComponentsClientHttpRequestFactory secureRest() throws
	 * KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
	 * 
	 * TrustStrategy acceptingTrustStrategy = (cert, authType) -> true; SSLContext
	 * sslContext = SSLContexts.custom().loadTrustMaterial(null,
	 * acceptingTrustStrategy).build(); SSLConnectionSocketFactory sslsf = new
	 * SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	 * 
	 * Registry<ConnectionSocketFactory> socketFactoryRegistry =
	 * RegistryBuilder.<ConnectionSocketFactory> create() .register("https",
	 * sslsf).register("http", new PlainConnectionSocketFactory()).build();
	 * BasicHttpClientConnectionManager connectionManager = new
	 * BasicHttpClientConnectionManager( socketFactoryRegistry); CloseableHttpClient
	 * httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
	 * .setConnectionManager(connectionManager).build();
	 * 
	 * HttpComponentsClientHttpRequestFactory requestFactory = new
	 * HttpComponentsClientHttpRequestFactory(httpClient); return requestFactory;
	 * 
	 * }
	 */
}
