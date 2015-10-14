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
package com.pte.liquid.relay.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.oxm.XmlMappingException;

import com.pte.liquid.relay.Converter;
import com.pte.liquid.relay.Marshaller;
import com.pte.liquid.relay.RelayService;
import com.pte.liquid.relay.exception.RelayException;

public class JmsConverter implements Converter<Message> {

	private Marshaller unmarshaller;
	private final static Logger logger = Logger.getLogger(JmsConverter.class);

	@Override
	public synchronized com.pte.liquid.relay.model.Message convert(Message msg)
			throws RelayException {

		if (msg instanceof TextMessage) {
			
			try {
				String content = ((TextMessage) msg).getText();					
				logger.info("Received textmessage.");
				logger.info(content);
				return (com.pte.liquid.relay.model.Message) unmarshaller
						.unmarshal(content);
			} catch (JMSException e) {
				throw new RelayException(e);
			} catch (XmlMappingException e) {
				throw new RelayException(e);
			}
			
		} else if (msg instanceof BytesMessage) {
			BytesMessage bm = (BytesMessage) msg;
			
			try {
				byte[] content = new byte[(int) bm.getBodyLength()];
								
				bm.readBytes(content);
				logger.info("Received bytesmessage.");
				logger.info(new String(content));
				return (com.pte.liquid.relay.model.Message) unmarshaller
						.unmarshal(new String(content));
			} catch (JMSException e) {
				throw new RelayException(e);
			}

		} else {
			return null;
		}

	}

	public Marshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Marshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}





}
