package cn.trouts.components.services.framework;



import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.trouts.entitys.framework.TroutsDict;
import cn.trouts.framework.context.TroutsConstant;
import cn.trouts.framework.enums.TroutsDictType;
import cn.trouts.framework.utils.TroutsLogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateTimeServiceImpl implements DateTimeService {


	@Autowired
	private TroutsDictService troutsDictService;

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

	@Override
	public String getFormatDate(Date date) {
		if(date == null){
			return null;
		}

		TroutsDict troutsDict = troutsDictService.getDictValue(TroutsDictType.SYSTEM_PARAMETER, TroutsConstant.SP_DATE_PATTERN);
		String dateString = null;
		if(troutsDict!=null){
			try {
				dateString = new SimpleDateFormat(troutsDict.getValue()).format(date);
			}catch (Exception e) {
				TroutsLogUtils.printLog("{} 格式化日期错误，{},将使用默认格式:{}",
						date,
						troutsDict.getValue(),
						DEFAULT_DATE_PATTERN);
			}
		} else {
			dateString = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(date);
		}

		return dateString;
	}

}
