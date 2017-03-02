package cn.trouts.components.services.framework;



import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

@Service
public class DateTimeServiceImpl implements DateTimeService {

	@Override
	public ZonedDateTime getCurrentDateAndTime() {
		// TODO Auto-generated method stub
		return ZonedDateTime.now();
	}

	@Override
	public Calendar getNow() {
		// TODO Auto-generated method stub
		return GregorianCalendar.from(getCurrentDateAndTime());
	}

}
