package com.commerce.service;

import javax.jms.Destination;

public interface SendMsgManager {

	public void putMessage(Destination destination);
}
