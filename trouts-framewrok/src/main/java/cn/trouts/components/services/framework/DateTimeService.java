package cn.trouts.components.services.framework;



import java.time.ZonedDateTime;
import java.util.Calendar;

import org.springframework.data.auditing.DateTimeProvider;

public interface DateTimeService extends DateTimeProvider{

	public ZonedDateTime getCurrentDateAndTime();

	public Calendar getNow();

}
