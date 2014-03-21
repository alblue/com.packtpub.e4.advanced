/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.event;
import java.util.HashMap;
import java.util.Map;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;
public class EventGenerator implements Runnable {
	private String address = System.getProperty("user.name") + "@localhost";
	private EventAdmin eventAdmin;
	private LogService log;
	private Thread thread;
	public void activate() {
		thread = new Thread(this, "event-generator");
		thread.setDaemon(true);
		thread.start();
	}
	public void deactivate() {
		if (thread != null) {
			thread.interrupt();
		}
		thread = null;
	}
	private void log(int level, String message) {
		LogService log = this.log;
		if (log != null) {
			log.log(level, message);
		}
	}
	@Override
	public void run() {
		log(LogService.LOG_INFO, "Event generator starting up");
		while (thread != null && !Thread.currentThread().isInterrupted()) {
			try {
				String topic = "smtp/high";
				String body = "Sample email sent via event at "
						+ System.currentTimeMillis();
				Map<String, String> email = new HashMap<String, String>();
				email.put("Subject", "Hello World");
				email.put("From", address);
				email.put("To", address);
				email.put("DATA", body);
				Event event = new Event(topic, email);
				log(LogService.LOG_INFO, "Posting event to " + topic);
				eventAdmin.postEvent(event);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				return;
			}
		}
		log(LogService.LOG_INFO, "Event generator shutting down");
	}
	public void setEmail(String address) {
		this.address = address;
	}
	public void setEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}
	public void setLogService(LogService log) {
		this.log = log;
	}
}
