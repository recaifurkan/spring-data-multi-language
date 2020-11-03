package com.rfbsoft.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LocaleConfiguration implements WebMvcConfigurer {

	private static final List<Locale> SUPPORTED_LOCALES = Arrays.asList(new Locale("sr"), new Locale("es"), new Locale("en"));



	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public AcceptHeaderLocaleResolver localeResolver(WebMvcProperties mvcProperties) {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver() {
			@Override
			public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
				LocaleContextHolder.setLocale(locale);
			}
		};

		localeResolver.setDefaultLocale(mvcProperties.getLocale());
		return localeResolver;
	}

}
