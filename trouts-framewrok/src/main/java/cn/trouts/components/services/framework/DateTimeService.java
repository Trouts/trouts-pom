package cn.trouts.components.services.framework;



import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.auditing.DateTimeProvider;

public interface DateTimeService extends DateTimeProvider{
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";


	public ZonedDateTime getCurrentDateAndTime();

	public Calendar getNow();
	public String getFormatDate(Date date);


}
