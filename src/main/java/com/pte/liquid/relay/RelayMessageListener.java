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

import javax.jms.JMSException;


import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.pte.liquid.relay.Converter;
import com.pte.liquid.relay.Transport;
import com.pte.liquid.relay.exception.RelayException;

public class RelayMessageListener implements MessageListener {

	private final static Logger logger = Logger.getLogger(Relay.class);
	private Transport transport;
	private Converter<Message> converter;
	
	
	public RelayMessageListener(){
		logger.info("Initializing RelayMessageListener...");
	}
		
	
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@Override
	public void onMessage(Message msg) {
		logger.info("Received message.");
		try {
			logger.info("Incoming message: " + msg);
			com.pte.liquid.relay.model.Message m = converter.convert(msg);
			logger.info("Converted message: " + m);
			transport.send(m);
			logger.info("Message sent");
			msg.acknowledge();
			logger.info("Message ack sent");
		} catch (RelayException e) {
			logger.error("Dumping incoming message: " + e.getMessage());
			if(logger.isDebugEnabled()){
				e.printStackTrace();				
			}			
		} catch (JMSException e) {
			logger.error("Dumping incoming message: " + e.getMessage());
			if(logger.isDebugEnabled()){
				e.printStackTrace();				
			}
		}
		
	}


	public Converter<Message> getConverter() {
		return converter;
	}


	public void setConverter(Converter<Message> converter) {
		this.converter = converter;
	}


	

	
	
}
