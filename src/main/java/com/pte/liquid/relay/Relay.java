//Copyright 2014 Paul Tegelaar

//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
package com.pte.liquid.relay;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Relay implements Daemon{
	
	private final static Logger logger = Logger.getLogger(Relay.class);
	private static ClassPathXmlApplicationContext applicationContext;
	
	public static void main(String args[]){		
		buildUp(args);		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(DaemonContext context) throws DaemonInitException, Exception {
		buildUp(context.getArguments());
	}

	@Override
	public void start() throws Exception {
		
	}

	@Override
	public void stop() throws Exception {
		tearDown();
		
	}
	
	public static void startService(String[] args) throws Exception {
		buildUp(args);	
	}

	
	public static void stopService(String[] args) throws Exception {
		tearDown();
	}
	
	private static void tearDown() {
		logger.warn("Stopping context...");
		applicationContext.stop();
		applicationContext.close();
		applicationContext.destroy();
		logger.warn("Context stopped...");
	}
	
	private static void buildUp(String args[]) {
		logger.info("Starting context...");
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		applicationContext.registerShutdownHook();
		logger.info("Done starting context...");
	}
	
}
