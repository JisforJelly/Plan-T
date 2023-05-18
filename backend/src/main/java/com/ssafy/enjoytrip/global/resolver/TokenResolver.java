package com.ssafy.enjoytrip.global.resolver;

import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ssafy.enjoytrip.domain.user.dto.UserDto;
import com.ssafy.enjoytrip.global.util.TokenUtil;
import com.ssafy.enjoytrip.global.validation.TokenVallidator;

public class TokenResolver implements HandlerMethodArgumentResolver {

	private final TokenUtil tokenUtil;
	
	public TokenResolver(TokenUtil tokenUtil) {
		this.tokenUtil = tokenUtil;
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		  return parameter.hasParameterAnnotation(TokenVallidator.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
        String token = extractAccessToken(Objects.requireNonNull(headers));
        if (token == null) {
            return new UserDto.AuthInfo(-1, null, null, null);
        }
        return tokenUtil.getClaims(token);
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
