package cn.trouts.components.framework.services;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.trouts.components.services.framework.DateTimeService;
import cn.trouts.framework.test.TroutsTest;

public class DateTimeServiceImplTest extends TroutsTest{
	
	@Autowired
	private DateTimeService dateTimeService;

	@Test
	public void testGetCurrentDateAndTime() {
		System.out.println(dateTimeService.getCurrentDateAndTime());
	}

	@Test
	public void testGetNow() {
		fail("Not yet implemented");
	}

}
