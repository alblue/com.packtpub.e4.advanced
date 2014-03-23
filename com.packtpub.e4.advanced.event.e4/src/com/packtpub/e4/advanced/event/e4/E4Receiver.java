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
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.osgi.service.event.Event;
import org.osgi.service.log.LogService;
@SuppressWarnings("restriction")
public class E4Receiver {
	@Inject
	LogService log;
	@Inject
	@Optional
	void receive(@EventTopic("smtp/*") Event event) {
		log.log(LogService.LOG_INFO,
				"Received e-mail to " + event.getProperty("To"));
	}
}
