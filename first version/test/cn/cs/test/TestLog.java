package cn.cs.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	
	@Test
	public void test(){
		Log log = LogFactory.getLog(getClass());
		log.debug("debug 日志级别");
		log.info("info 日志级别");
		log.warn("warn 日志级别");
		log.error("error 日志级别");
		log.fatal("fatal 日志级别");
	}
}
