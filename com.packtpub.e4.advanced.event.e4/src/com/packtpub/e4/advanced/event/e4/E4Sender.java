/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.event.e4;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.e4.core.services.events.IEventBroker;
public class E4Sender {
	private String address = System.getProperty("user.name") + "@localhost";
	@Inject
	IEventBroker broker;
	public void send() {
		String topic = "smtp/high";
		String body = "Sample email sent via event at "
				+ System.currentTimeMillis();
		Map<String, String> email = new HashMap<String, String>();
		email.put("Subject", "Hello World");
		email.put("From", address);
		email.put("To", address);
		email.put("DATA", body);
		broker.post(topic, email);
	}
}
