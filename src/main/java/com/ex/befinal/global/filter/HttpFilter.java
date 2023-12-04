package com.ex.befinal.global.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class HttpFilter extends Filter<ILoggingEvent> {
  @Override
  public FilterReply decide(ILoggingEvent event) {
    if (event.getLoggerName().equals(HttpLogger.class.getName())) {
      return FilterReply.ACCEPT;
    }
    return FilterReply.DENY;
  }
}
