package com.ssafy.enjoytrip.global.util;

import com.ssafy.enjoytrip.domain.user.dto.UserDto;

public interface TokenUtil {
	
	String createAccessToken(UserDto.AuthInfo dto);
	
	UserDto.AuthInfo getClaims(String token);
	
	boolean isValid(String token);
}
