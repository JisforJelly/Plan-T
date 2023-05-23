package com.ssafy.enjoytrip.global.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.enjoytrip.domain.user.dto.UserDto.AuthInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements TokenUtil {
	
    public final Key SECRET_KEY;
 
    public final int EXPIRATION_TIME;
    
    public JwtTokenUtil(@Value("${jwt.token-expiration-period}") int validTime) {
    	this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    	this.EXPIRATION_TIME = validTime;
    }
    
	@Override
	public String createAccessToken(AuthInfo dto) {
	   	Date issuedDate = new Date();
    	Date expiration = new Date(System.currentTimeMillis()+EXPIRATION_TIME);
    	return Jwts.builder()
    			.claim("userId", dto.getUserId())
    			.claim("loginId", dto.getLoginId())
    			.claim("role", dto.getUserRole())
    			.claim("name", dto.getName())
    			.claim("profileImgPath", dto.getProfileImgPath())
    			.setIssuedAt(issuedDate)
    			.setExpiration(expiration)
    			.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	@Override
	public AuthInfo getClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			claims = e.getClaims();
		}
		
		return new AuthInfo(claims.get("userId", Integer.class),
				claims.get("loginId", String.class), 
				claims.get("name", String.class), 
				claims.get("role", String.class),
				claims.get("profileImgPath", String.class));
	}

	@Override
	public boolean isValid(String token) {
		try {
	        Jws<Claims> claims = Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token);
	        return !claims.getBody().getExpiration().before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
            return false;
        }
	}
}
