package com.rgp.de;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication // This annotation boostraps and auto-configure the application.

public class ESignatureClientApplication extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		RedirectViewControllerRegistration r = registry.addRedirectViewController("/", "/index");
	}

	public static void main(String[] args) {
		SpringApplication.run(ESignatureClientApplication.class, args);
	}

}
