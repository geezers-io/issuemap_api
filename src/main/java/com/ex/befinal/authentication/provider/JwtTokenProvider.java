package com.ex.befinal.authentication.provider;


import com.ex.befinal.global.exception.GlobalException;
import com.ex.befinal.models.User;
import com.ex.befinal.repository.UserJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
  private final UserJpaRepository userJpaRepository;
  private final UserDetailsService userDetailsService;

  @Value("${jwt.access-token.expire-length}")
  private long accessTokenValidityInMilliseconds;

  @Value("${jwt.token.secret-key}")
  private String secretKey;


  public String createAccessToken(String nickName) {
    return createToken(nickName, accessTokenValidityInMilliseconds);
  }

  private String createToken(String nickName, long accessTokenValidityInMilliseconds) {
    Claims claims = Jwts.claims();
    claims.put("nickName", nickName);
    Date now = new Date();
    Date validity = new Date(now.getTime() + accessTokenValidityInMilliseconds);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      // "Bearer " 이후의 토큰 부분만 추출
      return bearerToken.substring(7);
    }
    return null;
  }

  // 토큰 유효성 검사
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }

  public String getNickName(String token) {
    return Jwts
        .parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .get("nickName", String.class);
  }


  public Authentication getAuthentication(String token) {
    String nickName = getNickName(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(nickName);
    if (!userDetails.isEnabled()) {
      throw new DisabledException("비활성화 회원입니다. 관리자에게 문의하세요");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }
}




