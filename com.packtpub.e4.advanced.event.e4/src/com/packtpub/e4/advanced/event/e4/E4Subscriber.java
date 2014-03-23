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
import javax.inject.Inject;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.EventHandler;
public class E4Subscriber {
	@Inject
	IEventBroker broker;
	public void subscribeUI(EventHandler handler) {
		// will be called on the UI thread
		broker.subscribe("smtp/*", handler);
	}
	public void subscribe(EventHandler handler) {
		broker.subscribe("smtp/*", "(Subject=Hello World)", handler, true);
	}
	public void unsubscribe(EventHandler handler) {
		broker.unsubscribe(handler);
	}
	
}
