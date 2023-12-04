package com.ex.befinal.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class HttpLogger extends OncePerRequestFilter {
  private final List<String> excludeAntPaths = Arrays.asList(
      "/v1/swagger-ui/**", "/v1/docs/**", "/v1/api-docs/**"
  );
  MultiReadableRequestBodyHttpServletRequest reqWrapper;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String requestUri = request.getRequestURI();
    AntPathMatcher matcher = new AntPathMatcher();

    for (var path : excludeAntPaths) {
      boolean match = matcher.match(path, requestUri);
      if (match) {
        return true;
      }
    }

    return false;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    try {
      reqWrapper = new MultiReadableRequestBodyHttpServletRequest(request);
      String traceId = RandomStringUtils.randomAlphabetic(8);
      MDC.put("traceId", traceId);
      printLog();
      response.setHeader("X-Trace-Id", traceId);
      MDC.clear();
    } catch (Exception ex) {
      log.error("error: {}\n{}", ex.getMessage(), ex.getStackTrace());
    } finally {
      filterChain.doFilter(reqWrapper, response);
    }
  }

  public static String getBody(ServletRequest request) throws IOException {

    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;
    InputStream inputStream = request.getInputStream();

    if (inputStream != null) {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      char[] charBuffer = new char[128];
      int bytesRead = -1;
      while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
        stringBuilder.append(charBuffer, 0, bytesRead);
      }
    }
    if (bufferedReader != null) {
      bufferedReader.close();
    }

    body = stringBuilder.toString();
    return body;
  }

  private void printLog() {
    log.debug("====================== HTTP Request ======================");
    printUriAndParams();
    printSessionDetails();
    log.debug("========================================================");
  }

  private void printUriAndParams() {
    String queryString = reqWrapper.getQueryString();
    String method = reqWrapper.getMethod();
    String requestUri = reqWrapper.getRequestURI();
    String contentType = reqWrapper.getContentType();
    log.debug("[{}] {}", method, requestUri);
    if (contentType != null) {
      log.debug("Content-Type: {}", contentType);
    }

    if (queryString != null) {
      log.debug("URL Params: {}", queryString);
    }

    if (method.equalsIgnoreCase("POST")) {
      if (contentType.startsWith("multipart/form-data")) {
        return;
      }
      try {
        String body = getBody(reqWrapper);
        log.debug("Body: {}", body);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  private void printSessionDetails() {
    Enumeration<String> sessionAttrs = reqWrapper.getSession().getAttributeNames();
    log.info("Session Attributes");
    if (!sessionAttrs.hasMoreElements()) {
      log.debug(" --> AnonymousUser");
    } else {
      while (sessionAttrs.hasMoreElements()) {
        String attr = sessionAttrs.nextElement();
        log.info(" --> {}: {}", attr, reqWrapper.getSession().getAttribute(attr));
      }
    }
  }

}
