package com.ex.befinal.global.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;


public class MultiReadableRequestBodyHttpServletRequest extends HttpServletRequestWrapper {
  private ByteArrayOutputStream cachedBytes;

  public MultiReadableRequestBodyHttpServletRequest(HttpServletRequest request) {
    super(request);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (cachedBytes == null) {
      cachedInputStream();
    }
    return new CachedServletInputStream(cachedBytes.toByteArray());
  }

  private void cachedInputStream() throws IOException {
    cachedBytes = new ByteArrayOutputStream();
    IOUtils.copy(super.getInputStream(), cachedBytes);
  }

  public static class CachedServletInputStream extends ServletInputStream {
    private final ByteArrayInputStream bf;

    public CachedServletInputStream(byte[] bytes) {
      this.bf = new ByteArrayInputStream(bytes);
    }

    @Override
    public int read() throws IOException {
      return bf.read();
    }

    @Override
    public boolean isFinished() {
      return bf.available() == 0;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      throw new RuntimeException("Not implemented");
    }
  }

}
