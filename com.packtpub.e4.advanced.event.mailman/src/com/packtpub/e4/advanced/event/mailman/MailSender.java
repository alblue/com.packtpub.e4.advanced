/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.event.mailman;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

public class MailSender implements EventHandler {
	private String hostname = "localhost";
	private LogService log;
	private int port = 25;
	@Override
	public void handleEvent(Event event) {
		String topic = event.getTopic();
		if (topic.startsWith("smtp/")) {
			String importance = topic.substring("smtp/".length());
			String to = (String) event.getProperty("To");
			String from = (String) event.getProperty("From");
			String subject = (String) event.getProperty("Subject");
			String body = (String) event.getProperty("DATA");
			try {
				Email email = new SimpleEmail();
				email.setDebug(false);
				email.setHostName(hostname);
				email.setSmtpPort(port);
				email.setFrom(from);
				email.addTo(to);
				email.setSubject(subject);
				email.setMsg(body);
				email.addHeader("Importance", importance);
				email.send();
				log(LogService.LOG_INFO, "Message sent successfully to " + to);
			} catch (EmailException e) {
				log(LogService.LOG_ERROR, "Error occurred" + e);
			}
		}
	}
	private void log(int level, String message) {
		LogService log = this.log;
		if (log != null) {
			log.log(level, message);
		}
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public void setLogService(LogService log) {
		this.log = log;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
