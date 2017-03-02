package cn.trouts.framework.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import cn.trouts.framework.context.conf.TroutsframeworkConfig;
import cn.trouts.framework.utils.TroutsLogUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
		@ContextConfiguration(name = "parent", classes = { TroutsframeworkConfig.class })})
public class TroutsTest {
	
	private static Logger logger = TroutsLogUtils.getLogger(TroutsTest.class);
	
	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		//打印一个空行，容易区分
		System.out.println("");
		TroutsLogUtils.printLog(logger, "test start->{}", this.getClass().getSimpleName());
	}

	@After
	public void end() {
		TroutsLogUtils.printLog(logger, "test end->{}", this.getClass().getSimpleName());
	}

}
