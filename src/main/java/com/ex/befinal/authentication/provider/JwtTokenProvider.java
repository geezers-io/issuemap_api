package com.ex.befinal.authentication.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
  @Value("${jwt.access-token.expire-length}")
  private long accessTokenValidityInMilliseconds;

  @Value("${jwt.token.secret-key}")
  private String secretKey;

  public String createAccessToken(String payload) {
    return createToken(payload, accessTokenValidityInMilliseconds);
  }

  private String createToken(String payload, long accessTokenValidityInMilliseconds) {
    Claims claims = Jwts.claims().setSubject(payload);
    Date now = new Date();
    Date validity = new Date(now.getTime() + accessTokenValidityInMilliseconds);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public String getPayload(String token) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
    } catch (ExpiredJwtException e) {
      return e.getClaims().getSubject();
    } catch (JwtException e) {
      throw new RuntimeException("유효하지 않은 토큰 입니다.");
    }
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException exception) {
      return false;
    }
  }
}
