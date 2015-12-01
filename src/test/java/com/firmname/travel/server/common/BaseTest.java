package com.firmname.travel.server.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/firmname/travel/server/config/applicationContext.xml")
public abstract class BaseTest extends AbstractJUnit4SpringContextTests{
	
	protected <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}
}
