package com.pte.liquid.relay;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RelayDaemon implements Daemon{

	private final static Logger logger = Logger.getLogger(RelayDaemon.class);	

	private ClassPathXmlApplicationContext applicationContext;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(DaemonContext context) throws DaemonInitException, Exception {
	
	}

	@Override
	public void start() throws Exception {
		logger.info("Starting context...");
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		applicationContext.registerShutdownHook();
		logger.info("Done starting context...");
		
	}

	@Override
	public void stop() throws Exception {
		logger.info("Stopping context...");
		applicationContext.stop();
		applicationContext.close();
		applicationContext.destroy();
		logger.info("Context stopped...");
		
	}
	
	
	


}
