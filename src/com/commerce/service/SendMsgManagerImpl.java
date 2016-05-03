package com.commerce.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.controller.MyException;

@Component
@Service
@Transactional
public class SendMsgManagerImpl implements SendMsgManager {

	private static final Logger LOG = LoggerFactory.getLogger(SendMsgManagerImpl.class);
	private int messageCount = 100;
	@Autowired(required = true)
	private JmsTemplate jmsTemplate;

	@Override
	public void putMessage(Destination destination) {
		for (int i = 0; i < messageCount; i++) {
			final String text = "Text for message: " + i;
			jmsTemplate.send(destination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					LOG.info("Sending message: " + text);
					TextMessage message = session.createTextMessage(text);
					message.setStringProperty("next", "foo");
					return message;
				}
			});
		}
		//if (true) {throw new MyException ("test");}

	}

}
