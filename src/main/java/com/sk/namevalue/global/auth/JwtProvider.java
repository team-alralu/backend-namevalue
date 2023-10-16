package com.sk.namevalue.global.auth;

import com.sk.namevalue.domain.model.enums.Token;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.JwtTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * title        : JWT Provider
 * author       : sim
 * date         : 2023-09-08
 * description  : JWT 기능 제공 클래스
 */

@Component
public class JwtProvider {

    private static final String JWT_TYPE = "Bearer ";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";

    public JwtProvider(@Value("${jwt.secret-key}") String secretKey){
        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private final Key key;

    private final static Long MINUTE = 1000L*60;

    /**
     * 액세스 토큰 생성
     * @param claims 클레임
     * @return accessToken
     */
    public String generateAccessToken(Map<String, Object> claims){

        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setSubject(Token.ACCESS_TOKEN.getKey())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+MINUTE*Token.ACCESS_TOKEN.getMaxAge()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 리프레시 토큰 생성
     * @param claims 클레임
     * @return refreshToken
     */
    public String generateRefreshToken(Map<String, Object> claims){

        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setSubject(Token.REFRESH_TOKEN.getKey())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+MINUTE*Token.REFRESH_TOKEN.getMaxAge()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Jwt 토큰 파싱
     * @param token - JWT 토큰
     * @return Claims
     */
    public Claims parseJwtToken(String token){
        try {

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e) {
            throw new JwtTokenException("토큰이 만료되었습니다. 다시 로그인하세요.");
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenException("지원하지 않는 토큰입니다. 다시 로그인하세요.");
        } catch (MalformedJwtException e) {
            throw new JwtTokenException("잘못된 형식의 토큰입니다. 다시 로그인하세요.");
        } catch (SignatureException e) {
            throw new JwtTokenException("위조된 요청입니다. 다시 로그인하세요.");
        } catch (IllegalArgumentException e) {
            throw new JwtTokenException("토큰 파싱 중 에러가 발생했습니다. 관리자에게 문의해주세요.");
        }
    }

    /**
     * Authorization Header 의 JWT 토큰 추출
     * @param authorization - Authorization Header
     * @return 완전한 JWT 토큰
     */
    public String extractJwtToken(String authorization){
        if(authorization == null){
            throw new JwtTokenException("Authorization 헤더가 없습니다. 관리자에게 문의해주세요.");
        } else if(!authorization.startsWith(JWT_TYPE)){
            throw new JwtTokenException("잘못된 인증 헤더입니다. 다시 로그인해주세요");
        }
        return authorization.substring(JWT_TYPE.length());
    }

    /**
     * Claims 생성
     * @param userEntity User 엔티티
     * @return Claims
     */
    public Map<String, Object> generateClaims(UserEntity userEntity){
        Map<String, Object> claims = new HashMap<>();
        claims.put(KEY_ID, userEntity.getId());
        claims.put(KEY_EMAIL, userEntity.getEmail());
        claims.put(KEY_NAME, userEntity.getName());

        return claims;
    }

    /**
     * login ID 추출
     * @param claims - 클레임
     * @return userId
     */
    public Long extractIdFromClaims(Claims claims){
        return claims.get(KEY_ID, Long.class);
    }
}
