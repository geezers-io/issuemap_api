package com.ex.befinal.utils;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TimeFilterRange {
  public Date startTime(Date date) {
    if (date != null) {
      Calendar calendar = Calendar.getInstance();

      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      date = calendar.getTime();
    }
    return date;
  }

  public Date endTime(Date date) {
    if (date != null) {
      Calendar calendar = Calendar.getInstance();


      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, 23);
      calendar.set(Calendar.MINUTE, 59);
      calendar.set(Calendar.SECOND, 59);

      calendar.set(Calendar.MILLISECOND, 999);
      date = calendar.getTime();
    }

    return date;
  }
}
