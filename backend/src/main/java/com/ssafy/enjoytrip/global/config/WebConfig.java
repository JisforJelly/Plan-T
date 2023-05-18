package com.ssafy.enjoytrip.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.enjoytrip.global.interceptor.TokenInterceptor;
import com.ssafy.enjoytrip.global.resolver.TokenResolver;
import com.ssafy.enjoytrip.global.util.TokenUtil;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final TokenInterceptor tokenInterceptor;
	private final TokenUtil tokenUtil;
	
	public WebConfig(TokenInterceptor tokenInterceptor, TokenUtil tokenUtil) {
		this.tokenInterceptor = tokenInterceptor;
		this.tokenUtil = tokenUtil;
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(tokenInterceptor)
    		.addPathPatterns("/**")
    		.excludePathPatterns("/comments")
    		.excludePathPatterns("/auth/**")
    		.excludePathPatterns("/image/**");
    }

    @Override
	public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
    		.allowedOrigins("*").exposedHeaders("Authorization", "Cache-Control", "Content-Type").allowedMethods("GET", "POST","PUT", "DELETE");
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(tokenResolver());
	}
	
	@Bean
	public TokenResolver tokenResolver() {
		return new TokenResolver(tokenUtil);
	}
}