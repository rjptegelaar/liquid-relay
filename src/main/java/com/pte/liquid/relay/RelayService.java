package com.pte.liquid.relay;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RelayService {
	
	private final static Logger logger = Logger.getLogger(RelayService.class);
	private static ClassPathXmlApplicationContext applicationContext;
	public RelayService(){
	}
	
	public static void start(String[] args) throws Exception {
		logger.info("Starting context...");
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		applicationContext.registerShutdownHook();
		logger.info("Done starting context...");
	}

	
	public static void stop(String[] args) throws Exception {
		logger.info("Stopping context...");
		applicationContext.stop();
		applicationContext.close();
		applicationContext.destroy();
		logger.info("Context stopped...");
	}
	
	
	

}
