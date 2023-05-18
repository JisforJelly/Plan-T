package com.ssafy.enjoytrip.global.interceptor;

import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.enjoytrip.global.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);
	private final TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isGetMethodWithPostsUri(request)) {
            return true;
        }
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }
        if (isGetMethod(request)) {
            return true;
        }

        if (notExistHeader(request)) {
            LOGGER.info("no header",request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        String token = extractAccessToken(request.getHeaders(HttpHeaders.AUTHORIZATION));
        System.out.println(token);
        if (isInvalidToken(token)) {
            LOGGER.info("no token",  request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    private boolean isGetMethod(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET");
    }

    private boolean isGetMethodWithPostsUri(HttpServletRequest request) {
        return request.getRequestURI().contains("/posts") && request.getMethod().equalsIgnoreCase("GET");
    }

    private boolean notExistHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return Objects.isNull(authorizationHeader);
    }

    private boolean isInvalidToken(String token) {
        return !tokenUtil.isValid(token);
    }
    
    private static String extractAccessToken(Enumeration<String> headers) {
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith("Bearer".toLowerCase()))) {
                String authHeaderValue = value.substring("Bearer".length()).trim();

                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }
}
